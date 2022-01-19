//  y  

package tp.p1.control.commands;

import tp.p1.control.Controller;
import tp.p1.logic.Game;


public class ResetCommand extends NoParamsCommand {
     public static final String[] COMMANDNAMES = {"reset","r"};
     public static final String COMMANDTEXT = "Reset";
     public static final String HELPTEXT = "Starts a new game.";
     
     public ResetCommand(){
    	 super(COMMANDNAMES, COMMANDTEXT, HELPTEXT);
     }
     
     public void execute(Game game, Controller controller){
    	 controller.setReset(true);
    	 controller.setNextTurn(true);
     }
}
