//  y  

package tp.p1.control.commands;

import tp.p1.control.Controller;
import tp.p1.logic.Game;

public abstract class Command {
	private final String[] commandNames;
	private String commandText;
	private String helpText;
	
	public Command(String[] commandNames, String commandText, String helpText){
	  this.commandNames = commandNames;
	  this.commandText = commandText;
	  this.helpText = helpText;
	}
    
	public abstract void execute(Game game, Controller controller);
	
	public abstract Command parse(String[] commandWords);
	
	public String helpText(){
		return " " + commandText + ": " + helpText
	;}
	
	public boolean checkName(String name){
		boolean found = false;
		for(int i = 0; !found && i< commandNames.length; ++i){
			if(commandNames[i].equals(name)) found = true;
		}
		return found;
	}
}
