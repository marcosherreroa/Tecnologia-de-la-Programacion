//Flavius Ciapsa y Marcos Herrero

package tp.p1.control.commands;

import tp.p1.control.Controller;
import tp.p1.logic.Game;

public class HelpCommand extends NoParamsCommand {
	public static final String[] COMMANDNAMES = {"help","h"};
	public static final String COMMANDTEXT = "Help";
	public static final String HELPTEXT = "Prints this help message.";
	
	public HelpCommand(){
		super(COMMANDNAMES, COMMANDTEXT, HELPTEXT);
	}
	
	public void execute(Game game, Controller controller) {
		System.out.println(CommandParser.commandHelp());
	}

}
