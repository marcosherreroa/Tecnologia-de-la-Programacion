//Flavius Ciapsa y Marcos Herrero

package tp.p1.control.commands;

import tp.p1.control.Controller;
import tp.p1.logic.Game;
import tp.p1.logic.factories.PlantFactory;
import tp.p1.logic.objects.Position;
import tp.p1.logic.objects.plants.Plant;

public class AddCommand extends Command {
	private String plantName;
	private Position pos;
	
	public static final String[] COMMANDNAMES = {"add", "a"};
    public static final String COMMANDTEXT = "Add <plant> <x> <y>";
    public static final String HELPINFO = "Adds a plant in position x,y.";
    
    public AddCommand(){
    	super(COMMANDNAMES, COMMANDTEXT, HELPINFO);
    	this.plantName = null;
    	this.pos = null;
    }
	
	public AddCommand parse(String[] commandWords){
		if(commandWords.length != 4) return null;
		else if (!checkName(commandWords[0])) return null;
		else{
		plantName = commandWords[1];
		pos = new Position(Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]));
		return this;
		}
	}
	
	public void execute(Game game, Controller controller){
		boolean OK = true;
		
		Plant plant = PlantFactory.getPlant(plantName);
		if(plant == null){
			System.out.println("Invalid plant");
			OK = false;
		}
		
		if(OK) OK = game.addPlantToGame(plant, pos);
		controller.setNextTurn(OK);
	}
}
