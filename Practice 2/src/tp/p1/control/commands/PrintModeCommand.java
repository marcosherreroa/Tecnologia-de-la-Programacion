//  y  

package tp.p1.control.commands;

import tp.p1.control.Controller;
import tp.p1.logic.Game;

public class PrintModeCommand extends Command {
	private String printMode;

	public static final String[] COMMANDNAMES = {"printmode", "p"};
    public static final  String COMMANDTEXT = "PrintMode";
    public static final String HELPTEXT = "Change print mode [Release|Debug].";
  		  
    public PrintModeCommand(){
  	  super(COMMANDNAMES, COMMANDTEXT, HELPTEXT);
  	  this.printMode = null;
    }
    
    public PrintModeCommand parse(String[] commandWords){
		if(commandWords.length != 2) return null;
		else if (!checkName(commandWords[0])) return null;
		else{
		printMode = commandWords[1];
		return this;
		}
	}
    

	
	public void execute(Game game, Controller controller){
		switch(printMode) {
		case "r":
		case "release": controller.setReleasePrinter(); break;
		case "d":
		case "debug": controller.setDebugPrinter(); break;
		default: System.out.println("Invalid printer"+System.lineSeparator());
		}
		
		controller.setPrintAgain(true);
	}
}