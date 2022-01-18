//Flavius Ciapsa y Marcos Herrero

package tp.p1.control.commands;

import tp.p1.control.Controller;
import tp.p1.logic.Game;

public class NoneCommand extends NoParamsCommand {
   public static final String[] COMMANDNAMES = {"none", "n", ""};
   public static final String COMMANDTEXT = "[none]";
   public static final String HELPTEXT = "Skips cycle.";
   
   public NoneCommand(){
 	  super(COMMANDNAMES, COMMANDTEXT, HELPTEXT);
   }
   
   public void execute(Game game, Controller controller){
      controller.setNextTurn(true);
   }
}
