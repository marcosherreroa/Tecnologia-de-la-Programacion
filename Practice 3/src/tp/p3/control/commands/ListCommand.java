//  y  

package tp.p3.control.commands;

import tp.p3.logic.Game;
import tp.p3.logic.factories.PlantFactory;

public class ListCommand extends NoParamsCommand {
      public static final String[] COMMANDNAMES = {"list", "l"};
      public static final  String COMMANDTEXT = "List";
      public static final String HELPTEXT = "Prints the list of available plants.";
    		  
      public ListCommand(){
    	  super(COMMANDNAMES, COMMANDTEXT, HELPTEXT);
      }
      
      public boolean execute(Game game){
    	  System.out.println(PlantFactory.listOfAvailablePlants());
		return false;
      }
}
