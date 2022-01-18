//Alumnos: Marcos Herrero Agustin
//         Flavius Abel Ciapsa
package tp.p1;

import java.util.Random;

public class Game {
     private SunflowerList sunflowerList;
     private PeashooterList peashooterList;
     private ZombieList zombieList;
     private ZombieManager zombieManager;
     private SuncoinManager suncoinManager;
     private int cycleCount;
     private final Level level;
     private final Random rand;
     
     
     public static final int NROWS = 4;
     public static final int NCOLUMNS = 8;
     
     public Game(Level level, Random rand){
    	this.sunflowerList= new SunflowerList();
    	this.peashooterList= new PeashooterList();
    	this.zombieList= new ZombieList();
    	this.zombieManager = new ZombieManager(level, rand);
    	this.suncoinManager = new SuncoinManager();
    	this.cycleCount= 0;
    	this.level= level;
    	this.rand= rand;
     }

	public int getCycleCount() {
		return cycleCount;
	}

	public void setCycleCount(int cycleCount) {
		this.cycleCount = cycleCount;
	}
	
	public void addCycleCount (int cycleCount){
		this.cycleCount += cycleCount;
	}
	
	public boolean validPosition(Position pos){
		int x = pos.getX();
		int y = pos.getY();
		
		return (0 <= x && x < NROWS && 0 <= y &&  y < NCOLUMNS);
	}
	
	public boolean somethingThere(Position pos){
		Search search = sunflowerList.search(pos);
		
		if(search.isFound()) return true;
		else{
			search = peashooterList.search(pos);
			
			if(search.isFound()) return true;
			else {
				search = zombieList.search(pos);
				
				if(search.isFound()) return true;
				else return false;
			}
		}
	}
	
	public String posToString(Position pos){
		SearchString searchString = sunflowerList.toString(pos);
		
		if(searchString.isFound())return searchString.getString();
		else{
			searchString = peashooterList.toString(pos);
			if(searchString.isFound()) return searchString.getString();
			else{
				searchString = zombieList.toString(pos);
				if(searchString.isFound()) return searchString.getString();
				else return " ";
			}
		}
		
	}
	
	public boolean addSunflower(Position pos) {
		 if(suncoinManager.getSuncoins() >= Sunflower.COST){
			 sunflowerList.add(pos, this);
			 suncoinManager.substractSuncoins(Sunflower.COST);
			 return true;
			 }
		 else return false;
	}
	
	public boolean addPeashooter(Position pos) {
		if(suncoinManager.getSuncoins() >= Peashooter.COST){
			 peashooterList.add(pos, this);
			 suncoinManager.substractSuncoins(Peashooter.COST);
			 return true; 
		 }
		else return false;
	}

	
	public void computerAdd(){
		if(zombieManager.isZombieAdded()){
			Position pos = new Position(rand.nextInt(NROWS), NCOLUMNS-1);
			if(!somethingThere(pos)){
			zombieList.add(pos, this);
			zombieManager.substractZombiesLeft(1);
			}
		}
	}
	
	public void reset(){
		this.sunflowerList= new SunflowerList();
    	this.peashooterList= new PeashooterList();
    	this.zombieList= new ZombieList();
    	this.zombieManager = new ZombieManager(level, rand);
    	this.suncoinManager = new SuncoinManager();
    	this.cycleCount= 0;
    	
	}
	
	public void showInfoPlants(){
		sunflowerList.showInfo();
		peashooterList.showInfo();
	}
	
	public String toString(){		
		GamePrinter gamePrinter = new GamePrinter(this, NROWS, NCOLUMNS);
		return ("Number of cycles: "+ cycleCount + System.getProperty("line.separator") +
				"Sun coins: "+ suncoinManager.getSuncoins() + System.getProperty("line.separator") +
				"Remaining zombies: "+ zombieManager.getZombiesLeft() + System.getProperty("line.separator") +
				gamePrinter.toString());
	}
	
	public void generateSun(int sunCoins){
		suncoinManager.addSuncoins(sunCoins);
	}
	
	public void shoot(Position posIni, int damage){
		Position posShot= new Position(posIni.getX(), posIni.getY());
    	boolean intercept= false;
    	
    	for(int y = posIni.getY(); !intercept && y< NCOLUMNS; ++y){
    		posShot.setY(y);
    		intercept = zombieList.attack(damage, posShot);
    	}
    	
	}
	
	public boolean checkAdvance(Position posIni){
		int column = posIni.getY();
		
		if(column > 0){
			Position posFin = new Position(posIni.getX(), column-1);
			
			if(somethingThere(posFin)) return false;
			else return true;
		}
		
		else return false;
	}
	
	public void attackPlants (Position posIni, int damage){
		int column = posIni.getY();
		
		if(column > 0){
			Position posFin = new Position(posIni.getX(), column-1);
            boolean attack = sunflowerList.attack(damage, posFin);
            if(!attack) attack = peashooterList.attack(damage, posFin);
		}
	}
	
	public boolean playerWins(){
		return (zombieManager.getZombiesLeft() == 0 && !zombieList.someoneAlive()); 
	}
	
	public boolean zombiesWin(){
		boolean zombiesWin = false;
		Position pos = new Position(0, 0);
		Search search;
		for(int x = 0; !zombiesWin && x < NROWS; ++x){
			pos.setX(x);
			search = zombieList.search(pos);
			zombiesWin = search.isFound();
		    }
		
		return zombiesWin;
	}
	
	public void update(){
		sunflowerList.update();
		peashooterList.update();
		zombieList.update();
		
	    computerAdd();
		addCycleCount(1);
	}
}
