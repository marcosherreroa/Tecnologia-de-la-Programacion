//Flavius Ciapsa y Marcos Herrero

package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public abstract class Command {
	private final String[] commandNames;
	private String commandText;
	private String helpText;
	private int numberOfWords;
	
	public Command(String[] commandNames, String commandText, String helpText, int numberOfWords){
	  this.commandNames = commandNames;
	  this.commandText = commandText;
	  this.helpText = helpText;
	  this.numberOfWords = numberOfWords;
	}
    
	public String getCommandText(){
		return commandText;
	}
	
	public String getName(){
		return commandNames[0];
	}
	
	public abstract boolean execute(Game game) throws CommandExecuteException;
	
	public abstract Command parse(String[] commandWords) throws CommandParseException;
	
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
	
	public void checkNumberOfWords(int length) throws CommandParseException{
		if(numberOfWords != length) 
			throw new CommandParseException
			("Invalid number of arguments for "+commandNames[0]+" command : "+ commandText);
	}

}
