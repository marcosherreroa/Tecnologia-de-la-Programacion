//Flavius Ciapsa y Marcos Herrero

package tp.p3.control.commands;

import tp.p3.logic.Game;


public class ResetCommand extends NoParamsCommand {
     public static final String[] COMMANDNAMES = {"reset","r"};
     public static final String COMMANDTEXT = "Reset";
     public static final String HELPTEXT = "Starts a new game.";
     
     public ResetCommand(){
    	 super(COMMANDNAMES, COMMANDTEXT, HELPTEXT);
     }
     
     public boolean execute(Game game){
    	game.reset();
    	return true;
     }
}
