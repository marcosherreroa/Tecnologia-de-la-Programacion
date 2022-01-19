//  y  

package tp.p1.control.commands;

import tp.p1.control.Controller;
import tp.p1.logic.Game;

public class ExitCommand extends NoParamsCommand{
      public static final String[] COMMANDNAMES ={"exit","e"};
      public static final String COMMANDTEXT = "Exit";
      public static final String HELPTEXT = "Terminates the program.";
    		  
      public ExitCommand(){
    	  super(COMMANDNAMES, COMMANDTEXT, HELPTEXT);
      }
      
      public void execute(Game game, Controller controller){
    	  System.out.println("Game over");
    	  controller.setPlay(false);
    	  controller.setNextTurn(true);
      }
}
