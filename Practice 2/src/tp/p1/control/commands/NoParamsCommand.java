//  y  

package tp.p1.control.commands;


public abstract class NoParamsCommand extends Command {
   public NoParamsCommand(String[] commandName, String commandText, String helpText ){
	   super(commandName, commandText, helpText);
   }
   
   public NoParamsCommand parse(String[] commandWords){
	   if(commandWords.length != 1) return null;
	   else if(!checkName(commandWords[0])) return null;
	   else return this;
   }
}
