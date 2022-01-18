//Flavius Ciapsa y Marcos Herrero

package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.exceptions.InvalidGameObjectException;
import tp.p3.exceptions.UnableToAddGameObjectException;
import tp.p3.logic.Game;
import tp.p3.logic.factories.PlantFactory;
import tp.p3.logic.objects.Position;
import tp.p3.logic.objects.plants.Plant;

public class AddCommand extends Command {
	private String plantName;
	private Position pos;
	
	public static final String[] COMMANDNAMES = {"add", "a"};
    public static final String COMMANDTEXT = "Add <plant> <x> <y>";
    public static final String HELPINFO = "Adds a plant in position x,y.";
    public static final int NUMBEROFWORDS = 4;
    
    public AddCommand(){
    	super(COMMANDNAMES, COMMANDTEXT, HELPINFO, NUMBEROFWORDS);
    	this.plantName = null;
    	this.pos = null;
    }
	
	public AddCommand parse(String[] commandWords) throws CommandParseException{
		if (!checkName(commandWords[0])){
			return null;
		}
		
		checkNumberOfWords(commandWords.length);
	
		plantName = commandWords[1];
		pos = new Position(Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]));
		
		return this;
		
	}
	
	public boolean execute(Game game) throws CommandExecuteException{
		Plant plant;
		try {
			plant = PlantFactory.getPlant(plantName);
		} catch (InvalidGameObjectException e) {
			throw new CommandExecuteException(e.getMessage(), e);
		}
		
	    game.addPlantToGame(plant, pos);
	
		
	    game.update();
		
		return true;
	}
}

