//Flavius Ciapsa y Marcos Herrero

package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.exceptions.InvalidGameObjectException;
import tp.p3.exceptions.UnableToAddGameObjectException;
import tp.p3.logic.Game;
import tp.p3.logic.factories.ZombieFactory;
import tp.p3.logic.objects.Position;
import tp.p3.logic.objects.zombies.Zombie;

public class AddZombieCommand extends Command{
	private String zombieName;
	private Position pos;
	
	public static final String[] COMMANDNAMES = {"addzombie", "az"};
    public static final String COMMANDTEXT = "Add <zombie> <x> <y>";
    public static final String HELPINFO = "Adds a zombie in position x,y.";
    public static final int NUMBEROFWORDS = 4;
    
    public AddZombieCommand(){
    	super(COMMANDNAMES, COMMANDTEXT, HELPINFO, NUMBEROFWORDS);
    	this.zombieName = null;
    	this.pos = null;
    }
	
	public AddZombieCommand parse(String[] commandWords) throws CommandParseException{
		if(!checkName(commandWords[0])) return null;
	
		checkNumberOfWords(commandWords.length);
		zombieName = commandWords[1];
		pos = new Position(Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]));
		return this;
	}
	
	
	public boolean execute(Game game) throws CommandExecuteException{
		Zombie zombie;
		try {
			zombie = ZombieFactory.getZombie(zombieName);
		} catch (InvalidGameObjectException e) {
			throw new CommandExecuteException(e.getMessage(), e);
		}
		
		try{
	    game.addZombieToGame(zombie, pos);
		} catch (UnableToAddGameObjectException e){
			throw new CommandExecuteException(e.getMessage(), e);
		}
	    return true;
	}
}

