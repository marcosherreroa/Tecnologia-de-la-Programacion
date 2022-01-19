//  y  

package tp.p3.control.commands;

import tp.p3.exceptions.CommandParseException;


public abstract class NoParamsCommand extends Command {
   public static final int NUMBEROFWORDS = 1;
	
   public NoParamsCommand(String[] commandName, String commandText, String helpText ){
	   super(commandName, commandText, helpText, NUMBEROFWORDS);
   }
   
   public NoParamsCommand parse(String[] commandWords) throws CommandParseException{
	   if(!checkName(commandWords[0])) return null;
	   
	   checkNumberOfWords(commandWords.length);
	   return this;
   }
}
