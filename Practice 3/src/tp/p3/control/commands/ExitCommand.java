//  y  

package tp.p3.control.commands;

import tp.p3.logic.Game;

public class ExitCommand extends NoParamsCommand{
      public static final String[] COMMANDNAMES ={"exit","e"};
      public static final String COMMANDTEXT = "Exit";
      public static final String HELPTEXT = "Terminates the program.";
    		  
      public ExitCommand(){
    	  super(COMMANDNAMES, COMMANDTEXT, HELPTEXT);
      }
      
      public boolean execute(Game game){
    	  System.out.println(System.lineSeparator()+
    			  "       ****** Game over!: User exit ******");
    	  game.setActive(false);
          return false;
      }
}
