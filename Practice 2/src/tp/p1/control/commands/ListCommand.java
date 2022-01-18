//Flavius Ciapsa y Marcos Herrero

package tp.p1.control.commands;

import tp.p1.control.Controller;
import tp.p1.logic.Game;
import tp.p1.logic.factories.PlantFactory;

public class ListCommand extends NoParamsCommand {
      public static final String[] COMMANDNAMES = {"list", "l"};
      public static final  String COMMANDTEXT = "List";
      public static final String HELPTEXT = "Prints the list of available plants.";
    		  
      public ListCommand(){
    	  super(COMMANDNAMES, COMMANDTEXT, HELPTEXT);
      }
      
      public void execute(Game game, Controller controller){
    	  System.out.println(PlantFactory.listOfAvailablePlants());
      }
}
