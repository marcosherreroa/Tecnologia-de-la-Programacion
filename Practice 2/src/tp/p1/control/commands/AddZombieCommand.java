//  y  

package tp.p1.control.commands;

import tp.p1.control.Controller;
import tp.p1.logic.Game;
import tp.p1.logic.factories.ZombieFactory;
import tp.p1.logic.objects.Position;
import tp.p1.logic.objects.zombies.Zombie;

public class AddZombieCommand extends Command{
	private String zombieName;
	private Position pos;
	
	public static final String[] COMMANDNAMES = {"addzombie", "az"};
    public static final String COMMANDTEXT = "Add <zombie> <x> <y>";
    public static final String HELPINFO = "Adds a zombie in position x,y.";
    
    public AddZombieCommand(){
    	super(COMMANDNAMES, COMMANDTEXT, HELPINFO);
    	this.zombieName = null;
    	this.pos = null;
    }
	
	public AddZombieCommand parse(String[ ] commandWords){
		if(commandWords.length != 4) return null;
		else if(!checkName(commandWords[0])) return null;
		else{
			zombieName = commandWords[1];
			pos = new Position(Integer.parseInt(commandWords[2]), 
					Integer.parseInt(commandWords[3]));
			return this;
		}
	}
	
	
	public void execute(Game game, Controller controller){
		boolean OK = true;
		Zombie zombie = ZombieFactory.getZombie(zombieName);
		if(zombie == null){
			System.out.println("Invalid zombie"+System.lineSeparator());
			OK = false;
		}
	    if(OK) OK = game.addZombieToGame(zombie, pos);
	    controller.setPrintAgain(OK);
	}
}

