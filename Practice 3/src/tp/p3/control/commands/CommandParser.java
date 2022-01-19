//  y  

package tp.p3.control.commands;

import tp.p3.exceptions.CommandParseException;


public class CommandParser {
	private static Command[] availableCommands = {
		new AddCommand(),
		new AddZombieCommand(),
		new HelpCommand(),
		new ResetCommand(),
		new ExitCommand(),
		new ListCommand(),
		new ZombieListCommand(),
		new NoneCommand(),
		new PrintModeCommand(),
		new SaveCommand(),
		new LoadCommand()
		};
	
	public static Command parseCommand(String[] commandWords) throws CommandParseException{
		Command command = null;
		boolean found = false;
		
		for(int i = 0; !found && i < availableCommands.length; ++i){
			try{
			command = availableCommands[i].parse(commandWords);
			}
			
			catch(NumberFormatException e){
				throw new CommandParseException("Invalid argument for " + availableCommands[i].getName()
						+ " command, integer expected : "+ availableCommands[i].getCommandText(), e);
			}
			
			if(command != null)found = true;
		}	
		
		if(command == null )throw new CommandParseException("Unknown command. "
				+ "Use 'help' to see the available commands");
	
		return command;
	}
	
	public static String commandHelp(){
		StringBuilder str = new StringBuilder();
		
		str.append("Available commands:").append(System.lineSeparator());
		for(Command command: availableCommands){
			str.append(command.helpText()).append(System.lineSeparator());
		}
		return str.toString();
	}
	
	
}
