//Flavius Ciapsa y Marcos Herrero

package tp.p3.control.commands;

import tp.p3.logic.Game;

public class HelpCommand extends NoParamsCommand {
	public static final String[] COMMANDNAMES = {"help","h"};
	public static final String COMMANDTEXT = "Help";
	public static final String HELPTEXT = "Prints this help message.";
	
	public HelpCommand(){
		super(COMMANDNAMES, COMMANDTEXT, HELPTEXT);
	}
	
	public boolean execute(Game game) {
		System.out.println(CommandParser.commandHelp());
		return false;
	}

}
