//Flavius Ciapsa y Marcos Herrero

package tp.p3.control.commands;

import tp.p3.logic.Game;

public class NoneCommand extends NoParamsCommand {
   public static final String[] COMMANDNAMES = {"none", "n", ""};
   public static final String COMMANDTEXT = "[none]";
   public static final String HELPTEXT = "Skips cycle.";
   
   public NoneCommand(){
 	  super(COMMANDNAMES, COMMANDTEXT, HELPTEXT);
   }
   
   public boolean execute(Game game){
      game.update();
      return true;
   }
}
