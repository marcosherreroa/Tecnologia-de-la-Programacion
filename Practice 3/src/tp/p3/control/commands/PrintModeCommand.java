//  y  

package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public class PrintModeCommand extends Command {
	private String printMode;

	public static final String[] COMMANDNAMES = {"printmode", "p"};
    public static final  String COMMANDTEXT = "PrintMode <mode>";
    public static final String HELPTEXT = "Change print mode [Release|Debug].";
    public static final int NUMBEROFWORDS = 2;
  		  
    public PrintModeCommand(){
  	  super(COMMANDNAMES, COMMANDTEXT, HELPTEXT, NUMBEROFWORDS);
  	  this.printMode = null;
    }
    
    public PrintModeCommand parse(String[] commandWords) throws CommandParseException{
    	if (!checkName(commandWords[0])){
			return null;
		}
    	
	    checkNumberOfWords(commandWords.length);
	    
		printMode = commandWords[1];
		return this;
	}
    
	public boolean execute(Game game) throws CommandExecuteException{
		switch(printMode) {
		case "r":
		case "release": game.setReleasePrinter(); break;
		case "d":
		case "debug": game.setDebugPrinter(); break;
		default:{
			throw new CommandExecuteException("Unknown print mode : "+printMode);
		}
		}
		
		return true;
	}
}