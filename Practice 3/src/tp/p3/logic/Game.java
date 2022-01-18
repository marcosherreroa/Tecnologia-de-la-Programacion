//Flavius Ciapsa y Marcos Herrero

package tp.p3.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import tp.p3.Level;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.FileContentsException;
import tp.p3.exceptions.InvalidLevelException;
import tp.p3.exceptions.InvalidGameObjectException;
import tp.p3.exceptions.UnableToAddGameObjectException;
import tp.p3.logic.factories.PlantFactory;
import tp.p3.logic.factories.ZombieFactory;
import tp.p3.logic.list.GameObjectList;
import tp.p3.logic.list.Search;
import tp.p3.logic.list.SearchString;
import tp.p3.logic.objects.GameObject;
import tp.p3.logic.objects.Position;
import tp.p3.logic.objects.plants.Plant;
import tp.p3.logic.objects.zombies.Zombie;
import tp.p3.logic.printers.DebugPrinter;
import tp.p3.logic.printers.GamePrinter;
import tp.p3.logic.printers.ReleasePrinter;

public class Game {
	private GameObjectList plantList;
	private GameObjectList zombieList;
	private ZombieManager zombieManager;
	private SuncoinManager suncoinManager;
	private GamePrinter gamePrinter;
	private boolean active;
	private int cycleCount;
	private Level level;
	private final Random rand;
	private final int seed;

	public static final int NROWS = 4;
	public static final int NCOLUMNS = 8;
	public static final String wrongPrefixMsg = "unknown game attribute: ";
	public static final String lineTooLongMsg = "too many words on line commencing: ";
	public static final String lineTooShortMsg = "missing data on line commencing: ";

	public Game(Level level, Random rand, int seed) {
		this.plantList = new GameObjectList();
		this.zombieList = new GameObjectList();
		this.zombieManager = new ZombieManager(level, rand);
		this.suncoinManager = new SuncoinManager();
		this.gamePrinter = new ReleasePrinter();
		this.active = true;
		this.cycleCount = 0;
		this.level = level;
		this.rand = rand;
		this.seed = seed;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setDebugPrinter() {
		gamePrinter = new DebugPrinter();
	}

	public void setReleasePrinter() {
		gamePrinter = new ReleasePrinter();
	}

	public int getCycleCount() {
		return cycleCount;
	}

	public void setCycleCount(int cycleCount) {
		this.cycleCount = cycleCount;
	}

	public void addCycleCount(int cycleCount) {
		this.cycleCount += cycleCount;
	}

	public int getSeed() {
		return seed;
	}

	public int getNumberOfPlants() {
		return plantList.getCount();
	}

	public int getNumberOfZombies() {
		return zombieList.getCount();
	}

	public int getNumberOfObjects() {
		return plantList.getCount() + zombieList.getCount();
	}

	public String getRealeaseGameState() {
		StringBuilder str = new StringBuilder();
		String ln = System.getProperty("line.separator");

		str.append("Number of cycles: " + cycleCount).append(ln);
		str.append("Sun coins: " + suncoinManager.getSuncoins()).append(ln);
		str.append("Remaining zombies: " + zombieManager.getZombiesLeft())
				.append(ln);

		return str.toString();
	}

	public String getDebugGameState() {
		StringBuilder str = new StringBuilder();
		String ln = System.getProperty("line.separator");

		str.append("Number of cycles: " + cycleCount).append(ln);
		str.append("Sun coins: " + suncoinManager.getSuncoins()).append(ln);
		str.append("Remaining zombies: " + zombieManager.getZombiesLeft())
				.append(ln);
		str.append("Level: " + level.name()).append(ln);
		str.append("Seed: " + seed).append(ln);

		return str.toString();
	}

	public boolean validInitialPositionPlants(Position pos) {
		return (0 <= pos.getX() && pos.getX() < NROWS && 0 <= pos.getY() && pos
				.getY() < NCOLUMNS - 1);
	}

	public boolean validInitialPositionZombies(Position pos) {
		return (pos.getY() >= 0 && pos.getY() < NCOLUMNS && 0 <= pos.getX() && pos
				.getX() < NROWS);
	}

	public void checkInitialPositionPlants(Plant plant, Position pos)
			throws UnableToAddGameObjectException {
		if (!validInitialPositionPlants(pos)) {
			throw new UnableToAddGameObjectException(pos.toString()
					+ " is an invalid position");
		}
	}

	public void checkInitialPositionZombies(Zombie zombie, Position pos)
			throws UnableToAddGameObjectException {
		if (!validInitialPositionZombies(pos)) {
			throw new UnableToAddGameObjectException("Failed to add "
					+ zombie.getObjectText() + " : " + pos.toString()
					+ " is an invalid position");
		}
		;
	}

	public boolean somethingThere(Position pos) {
		Search search = plantList.search(pos);

		if (search.isFound())
			return true;
		else {
			search = zombieList.search(pos);

			if (search.isFound())
				return true;
			else
				return false;
		}
	}

	public void checkNothingThere(GameObject object, Position pos)
			throws UnableToAddGameObjectException {
		if (somethingThere(pos)) {
			throw new UnableToAddGameObjectException("position "+pos.toString()
					+ " is already occupied");
		}
	}
	
	public void checkEnoughSuncoins(Plant plant) throws UnableToAddGameObjectException {
																					
		if (suncoinManager.getSuncoins() < plant.getCost()) {
			throw new UnableToAddGameObjectException("not enough suncoins to buy it");
		}
	}

	public void checkZombiesLeft(Zombie zombie) throws CommandExecuteException {

		if (zombieManager.getZombiesLeft() == 0) {
			throw new CommandExecuteException("Failed to add "
					+ zombie.getObjectText() + " : no zombies left");
		}
	}

	public String toStringRelease(Position pos) {
		SearchString searchString = plantList.toStringRelease(pos);

		if (searchString.isFound())
			return searchString.getString();

		searchString = zombieList.toStringRelease(pos);
		if (searchString.isFound())
			return searchString.getString();
		else
			return " ";
	}

	public String PlantsToStringDebug(int index) {
		return plantList.toStringDebug(index);
	}

	public String ZombiesToStringDebug(int index) {
		return zombieList.toStringDebug(index);
	}

	public void addPlantToGame(Plant plant, Position pos)
			throws CommandExecuteException {
		try {
			checkInitialPositionPlants(plant, pos);
			checkNothingThere(plant, pos);
			checkEnoughSuncoins(plant);
		}

		catch (UnableToAddGameObjectException e) {
			throw new CommandExecuteException("Fail to add "
					+ plant.getObjectText() +" : "+ e.getMessage(), e);
		}

		plant.setPos(pos);
		plant.setGame(this);
		plantList.add(plant);
		suncoinManager.substractSuncoins(plant.getCost());
	}

	public void addZombieToGame(Zombie zombie, Position pos)
			throws UnableToAddGameObjectException {
		try {
			checkZombiesLeft(zombie);
			checkInitialPositionZombies(zombie, pos);
			checkNothingThere(zombie, pos);
		}

		catch (CommandExecuteException e) {
			throw new UnableToAddGameObjectException("Fail to add "
					+ zombie.getObjectText() +" : "+ e.getMessage(), e);
		}

		zombie.setPos(pos);
		zombie.setGame(this);
		zombieList.add(zombie);
		zombieManager.substractZombiesLeft(1);
	}

	public void computerAdd() {
		if (zombieManager.isZombieAdded()) {
			Zombie zombie = ZombieFactory.getRandomZombie(rand);
			Position pos = new Position(rand.nextInt(NROWS), NCOLUMNS - 1);

			if (!somethingThere(pos)) {
				zombie.setPos(pos);
				zombie.setGame(this);
				zombieList.add(zombie);
				zombieManager.substractZombiesLeft(1);
			}
		}
	}

	public void reset() {
		this.plantList = new GameObjectList();
		this.zombieList = new GameObjectList();
		this.zombieManager = new ZombieManager(level, rand);
		this.suncoinManager = new SuncoinManager();
		this.cycleCount = 0;

	}

	public void generateSun(int sunCoins) {
		suncoinManager.addSuncoins(sunCoins);
	}

	public void shoot(Position posIni, int damage) {
		Position posShot = new Position(posIni.getX(), posIni.getY());
		boolean intercept = false;

		for (int y = posIni.getY(); !intercept && y < NCOLUMNS; ++y) {
			posShot.setY(y);
			intercept = zombieList.attack(damage, posShot);
		}

	}

	public void explode(Position posCenter, int damage) {
		Position[] surroundingSquares = {
				new Position(posCenter.getX() - 1, posCenter.getY()),
				new Position(posCenter.getX() - 1, posCenter.getY() + 1),
				new Position(posCenter.getX(), posCenter.getY() + 1),
				new Position(posCenter.getX() + 1, posCenter.getY() + 1),
				new Position(posCenter.getX() + 1, posCenter.getY()),
				new Position(posCenter.getX() + 1, posCenter.getY() - 1),
				new Position(posCenter.getX(), posCenter.getY() - 1),
				new Position(posCenter.getX() - 1, posCenter.getY() - 1), };

		for (Position pos : surroundingSquares) {
			zombieList.attack(damage, pos);
		}
	}

	public boolean checkAdvance(Position posIni) {
		int column = posIni.getY();

		if (column > 0) {
			Position posFin = new Position(posIni.getX(), column - 1);

			if (somethingThere(posFin)) return false;
			else return true;
		}

		else return false;
	}

	public void attackPlants(Position posIni, int damage) {
		int column = posIni.getY();

		if (column > 0) {
			Position posFin = new Position(posIni.getX(), column - 1);
			plantList.attack(damage, posFin);
		}
	}

	public boolean playerWins() {
		return (zombieManager.getZombiesLeft() == 0 && zombieList.getCount() == 0);
	}

	public boolean zombiesWin() {
		boolean zombiesWin = false;
		Position pos = new Position(0, 0);
		Search search;
		for (int x = 0; !zombiesWin && x < NROWS; ++x) {
			pos.setX(x);
			search = zombieList.search(pos);
			zombiesWin = search.isFound();
		}

		return zombiesWin;
	}
    
	public void checkWinner(){
		if (playerWins()) {
			System.out.println("Game over. Player wins");
			active = false;
		} else if (zombiesWin()) {
			System.out.println("Game over. Zombies win");
			active = false;
		}
	}
	
	public void update() {
		computerAdd();
		plantList.update();
		zombieList.update();
		plantList.removeDead();
		zombieList.removeDead();
		++cycleCount;
	}

	public String toString() {
		return gamePrinter.printGame(this);
	}

	public void store(BufferedWriter bw) throws IOException { 
		bw.write("cycle: " + cycleCount + System.lineSeparator() + "sunCoins: "
				+ suncoinManager.getSuncoins() + System.lineSeparator()
				+ "level: " + level.name() + System.lineSeparator()
				+ "remzombies: " + zombieManager.getZombiesLeft()+ System.lineSeparator() +
				"plantList: " + plantList.externalise() + System.lineSeparator()
				+ "zombieList: " + zombieList.externalise() + System.lineSeparator());
	}

	public String[] loadLine(BufferedReader inStream, String prefix,
			boolean isList) throws IOException, FileContentsException {
		String line = inStream.readLine().trim();

		// absence of the prefix is invalid
		if (!line.startsWith(prefix + ":"))
			throw new FileContentsException(wrongPrefixMsg + prefix);

		String contentString = line.substring(prefix.length() + 1).trim();
		String[] words;
		// the attribute contents are not empty
		if (!contentString.equals("")) {
			if (!isList) {
				// split non−list attribute contents into words
				// using 1−or−more−white−spaces as separator
				words = contentString.split("\\s");
				// a non−list attribute with contents of more than one word is
				// invalid
				if (words.length != 1)
					throw new FileContentsException(lineTooLongMsg + prefix);
			} else
				// split list attribute contents into words
				// using comma+0−or−more−white−spaces as separator
				words = contentString.split(",\\s*");
			// the attribute contents are empty
		} else {
			// a non−list attribute with empty contents is invalid
			if (!isList)
				throw new FileContentsException(lineTooShortMsg + prefix);
			// a list attibute with empty contents is valid;
			// use a zero−length array to store its words
			words = new String[0];
		}
		return words;
	}
    
	public void loadPlantList(String[] line) throws FileContentsException{  
		String[] words;
		Plant plant;
	    Position pos;
		for (int i = 0; i < line.length; ++i) {
			try {
				words = line[i].split(":");
				if(words.length!= 5){
					throw new FileContentsException("invalid number of properties");
				}
				try {
					plant = PlantFactory.getPlant(words[0]);
				} catch (InvalidGameObjectException e) {
					throw new FileContentsException(e.getMessage(),e);
				}

				try {
					plant.setLife(Integer.parseInt(words[1]));
					if(plant.getLife()<= 0 || plant.getLife() > plant.getMaxLife()){
						throw new FileContentsException("");
					}
				} catch (NumberFormatException| FileContentsException e) {
					throw new FileContentsException(
							"invalid life, expected integer in range [1, maxLife] : " + words[1], e);
				}

				pos = new Position();
				try {
					pos.setX(Integer.parseInt(words[2]));
				} catch (NumberFormatException e) {
					throw new FileContentsException(
							"invalid first coordinate(x), expected number : "
									+ words[2],e);
				}

				try {
					pos.setY(Integer.parseInt(words[3]));
				} catch (NumberFormatException e) {
					throw new FileContentsException(
							"invalid second coordinate(y), expected number : "
									+ words[3],e);
				}

				try{
		               plant.setCyclesLeftToAction1(Integer.parseInt(words[4]));
		               if(plant.getCyclesLeftToAction1()< 0 || plant.getCyclesLeftToAction1()> plant.getCyclesAct1()){
		            	   throw new FileContentsException("");
		               };
					} catch(NumberFormatException| FileContentsException e){
						throw new FileContentsException("invalid cycles left to act, expected integer"
								+ " in range [0,maxCycles] : "+ words[4],e);
					}
				try{
				checkInitialPositionPlants(plant, pos);
				checkNothingThere(plant, pos);
				plant.setPos(pos);
				plant.setGame(this);
				plantList.add(plant);
				} catch(UnableToAddGameObjectException e){
					throw new FileContentsException(e.getMessage(), e);
				}
			}

			catch (FileContentsException e) {
				throw new FileContentsException("plant " + i + " : "+ e.getMessage());
			}
		}
	}
	
	public void loadZombieList(String[] line) throws FileContentsException{  
		String[] words;
		Zombie zombie;
		Position pos ;
		for (int i = 0; i < line.length; ++i) {
			try {
				words = line[i].split(":");
				if(words.length!= 5){
					throw new FileContentsException("invalid number of properties");
				}
				try {
					zombie = ZombieFactory.getZombie(words[0]);
				} catch (InvalidGameObjectException e) {
					throw new FileContentsException(e.getMessage(),e);
				}

				try {
					zombie.setLife(Integer.parseInt(words[1]));
					if(zombie.getLife()<= 0 || zombie.getLife() > zombie.getMaxLife()){
						throw new FileContentsException("");
					}
				} catch (NumberFormatException| FileContentsException e) {
					throw new FileContentsException(
							"invalid life, expected integer in range [1, maxLife] : " + words[1], e);
				}
                
				
				pos = new Position();
				try {
					pos.setX(Integer.parseInt(words[2]));
				} catch (NumberFormatException e) {
					throw new FileContentsException(
							"invalid first coordinate(x), expected integer : "
									+ words[2],e);
				}

				try {
					pos.setY(Integer.parseInt(words[3]));
				} catch (NumberFormatException e) {
					throw new FileContentsException(
							"invalid second coordinate(y), expected integer : "+ words[3],e);
				}
				
				try{
	               zombie.setCyclesLeftToAction1(Integer.parseInt(words[4]));
	               if(zombie.getCyclesLeftToAction1()< 0 || zombie.getCyclesLeftToAction1()> zombie.getCyclesAct1()){
	            	   throw new FileContentsException("");
	               };
				} catch(NumberFormatException e){
					throw new FileContentsException("invalid cycles left to act, expected integer in"
							+ " range [0, maxCycles] : "+ words[4],e);
				}
				
				try{
					checkInitialPositionZombies(zombie, pos);
					checkNothingThere(zombie, pos);
					zombie.setPos(pos);
					zombie.setGame(this);
					zombieList.add(zombie);
					} catch(UnableToAddGameObjectException e){
						throw new FileContentsException(e.getMessage(), e);
					}
			}

			catch (FileContentsException e) {
				throw new FileContentsException("zombie " + i + " : "+ e.getMessage(),e);
			}
		}
	}
	public void load(BufferedReader br) throws IOException,
			FileContentsException {
		int cycleCountBackup = cycleCount;
		int suncoinsBackup = suncoinManager.getSuncoins();        
		Level levelBackup = level;
		int zombiesLeftBackup = zombieManager.getZombiesLeft();
		GameObjectList plantListBackup = plantList;
		GameObjectList zombieListBackup = zombieList;
	
		plantList = new GameObjectList();
		zombieList = new GameObjectList();
		
		try{

		String[] line = loadLine(br, "cycle", false);
		try {
			cycleCount = Integer.parseInt(line[0]);
			if(cycleCount < 0){
				throw new FileContentsException("");
			}
		} catch (NumberFormatException|FileContentsException e) {
			throw new FileContentsException("invalid cycle, expected nonnegative integer : "
					+ line[0],e);
		}   

		line = loadLine(br, "sunCoins", false);
		try {
			suncoinManager.setSuncoins(Integer.parseInt(line[0]));
			if(suncoinManager.getSuncoins()<0){
				throw new FileContentsException("");
			}
		} catch (NumberFormatException| FileContentsException e) {
			throw new FileContentsException(
					"invalid suncoins, expected nonnegative integer : " + line[0],e);
		}

		line = loadLine(br, "level", false);
		try {
			level = Level.parse(line[0]);
		}

		catch (InvalidLevelException e) {
			throw new FileContentsException(e.getMessage(), e);
		}

		line = loadLine(br, "remzombies", false);
		try {
			zombieManager.setZombiesLeft(Integer.parseInt(line[0]));
			if(zombieManager.getZombiesLeft()<0 ||zombieManager.getZombiesLeft()> level.getZombiesNumber()){
				throw new FileContentsException("");
			}
		} catch (NumberFormatException e) {
			throw new FileContentsException(
					"invalid remzombies, expected nonnegative integer : " + line[0],e);
		}

		line = loadLine(br, "plantList", true);
		loadPlantList(line);
		
		line = loadLine(br, "zombieList", true);
		loadZombieList(line);
		
		setReleasePrinter();
		}
		catch(IOException | FileContentsException e){
			cycleCount = cycleCountBackup;
			suncoinManager.setSuncoins(suncoinsBackup);
			level = levelBackup;
			zombieManager.setZombiesLeft(zombiesLeftBackup);
			plantList = plantListBackup;
			zombieList = zombieListBackup;
			
			throw e;
		}
	}
}
