//  y  

package tp.p1.logic;

import java.util.Random;

import tp.p1.Level;
import tp.p1.logic.factories.ZombieFactory;
import tp.p1.logic.list.GameObjectList;
import tp.p1.logic.list.Search;
import tp.p1.logic.list.SearchString;
import tp.p1.logic.objects.Position;
import tp.p1.logic.objects.plants.Plant;
import tp.p1.logic.objects.zombies.Zombie;

public class Game {
     private GameObjectList plantList;
     private GameObjectList zombieList;
     private ZombieManager zombieManager;
     private SuncoinManager suncoinManager;
     private int cycleCount;
     private final Level level;
     private final Random rand;
     private final int seed;
          
     public static final int NROWS = 4;
     public static final int NCOLUMNS = 8;
     
     public Game(Level level, Random rand, int seed){
    	this.plantList= new GameObjectList();
    	this.zombieList= new GameObjectList();
    	this.zombieManager = new ZombieManager(level, rand);
    	this.suncoinManager = new SuncoinManager();
    	this.cycleCount= 0;
    	this.level= level;
    	this.rand= rand;
    	this.seed= seed;
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
	
	public int getSeed(){
		return seed;
	}
	public int getNumberOfPlants(){
		return plantList.getCount();
	}
	
	public int getNumberOfZombies(){
		return zombieList.getCount();
	}
	
	public int getNumberOfObjects(){
		return plantList.getCount() + zombieList.getCount();
	}
	
	public String getRealeaseGameState(){
		StringBuilder str= new StringBuilder();
		String ln = System.getProperty("line.separator");
		
		str.append("Number of cycles: "+ cycleCount).append(ln);
		str.append("Sun coins: "+ suncoinManager.getSuncoins()).append(ln);
		str.append("Remaining zombies: "+ zombieManager.getZombiesLeft()).append(ln);
		
		return str.toString();
	}
	
	public String getDebugGameState(){
		StringBuilder str= new StringBuilder();
		String ln = System.getProperty("line.separator");
		
		str.append("Number of cycles: "+ cycleCount).append(ln);
		str.append("Sun coins: "+ suncoinManager.getSuncoins()).append(ln);
		str.append("Remaining zombies: "+ zombieManager.getZombiesLeft()).append(ln);
		str.append("Level: "+level.getLevelName()).append(ln);
		str.append("Seed: "+seed).append(ln);
		
		return str.toString();
	}

	public boolean validInitialPositionPlants(Position pos){
		return(0 <= pos.getX() && pos.getX()<NROWS && 
				0<= pos.getY() && pos.getY() < NCOLUMNS-1);
	}
	
	public boolean validInitialPositionZombies(Position pos){
		return (pos.getY() >= 0 && pos.getY() < NCOLUMNS && 0 <= pos.getX() && pos.getX() < NROWS);
	}
	
	public boolean somethingThere(Position pos){
		Search search = plantList.search(pos);
		
		if(search.isFound()) return true;
		else{
			search = zombieList.search(pos);
			
			if(search.isFound()) return true;
			else return false;
		}
	}
	
	public String toStringRelease(Position pos){
		SearchString searchString = plantList.toStringRelease(pos);
		
		if(searchString.isFound()) return searchString.getString();
		
		searchString = zombieList.toStringRelease(pos);
		if(searchString.isFound()) return searchString.getString();
		else return " ";
	}
	
	public String PlantsToStringDebug(int index){
		return plantList.toStringDebug(index);
	}
	
	public String ZombiesToStringDebug(int index){
		return zombieList.toStringDebug(index);
	}
	
	public boolean addPlantToGame(Plant plant, Position pos){
		if(!validInitialPositionPlants(pos)){
			 System.out.println("Invalid position"+System.lineSeparator());
			 return false;
		}
	    else if(somethingThere(pos)){
			 System.out.println("There is something there!"+System.lineSeparator());
			 return false;
	    }
	    else if(suncoinManager.getSuncoins() < plant.getCost()){
	    	System.out.println("You don't have enough suncoins"+System.lineSeparator());
	    	return false;
	    }
		
	    else{
	    	plant.setPos(pos);
	        plant.setGame(this);
	    	plantList.add(plant);
	    	suncoinManager.substractSuncoins(plant.getCost());
	    	return true;
	    }
	}
	
	public boolean addZombieToGame(Zombie zombie, Position pos){
		if(zombieManager.getZombiesLeft()== 0){
			System.out.println("There are no zombies left"+System.lineSeparator());
			return false;
		}
		
		else if(!validInitialPositionZombies(pos)){
			 System.out.println("Invalid position"+System.lineSeparator());
			 return false;
		}
		
	    else if(somethingThere(pos)){
			 System.out.println("There is something there!"+System.lineSeparator());
			 return false;
	    }
		
	    else {
	    	zombie.setPos(pos);
	    	zombie.setGame(this);
	    	zombieList.add(zombie);
	    	zombieManager.substractZombiesLeft(1);
	    	return true;
	    }
	}
	
	public void computerAdd(){
		if(zombieManager.isZombieAdded()){
			Zombie zombie = ZombieFactory.getRandomZombie(rand);
			Position pos = new Position(rand.nextInt(NROWS), NCOLUMNS-1);
			
			if(!somethingThere(pos)){
			zombie.setPos(pos);
			zombie.setGame(this);
			zombieList.add(zombie);
			zombieManager.substractZombiesLeft(1);
			}
		}
	}
	
	public void reset(){
		this.plantList = new GameObjectList();
    	this.zombieList= new GameObjectList();
    	this.zombieManager = new ZombieManager(level, rand);
    	this.suncoinManager = new SuncoinManager();
    	this.cycleCount= 0;
    	
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
	
	public void explode(Position posCenter, int damage){
		Position[] surroundingSquares = {
				new Position(posCenter.getX()-1, posCenter.getY()),
				new Position(posCenter.getX()-1, posCenter.getY()+1),
				new Position(posCenter.getX(), posCenter.getY()+1),
				new Position(posCenter.getX()+1, posCenter.getY()+1),
				new Position(posCenter.getX()+1, posCenter.getY()),
				new Position(posCenter.getX()+1, posCenter.getY()-1),
				new Position(posCenter.getX(), posCenter.getY()-1),
				new Position(posCenter.getX()-1, posCenter.getY()-1),
		};
		
		for(Position pos: surroundingSquares){
			zombieList.attack(damage, pos);
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
            plantList.attack(damage, posFin);
		}
	}
	
	public boolean playerWins(){
		if(zombieManager.getZombiesLeft() == 0 && zombieList.getCount() == 0) {
			System.out.println("Game over");
			System.out.println("Player wins");
			return true;
		}
		else return false;
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
		
		if(zombiesWin){
			System.out.println("Game over");
			System.out.println("Zombies win");
			return true;
		}
		
		else return false;
	}
	
	public boolean checkWinner(){
		return playerWins() || zombiesWin();	
	}
	
	public void update(){
		plantList.update();
		zombieList.update();
		plantList.removeDead();
		zombieList.removeDead();
		computerAdd();
		++cycleCount;
	}
}
