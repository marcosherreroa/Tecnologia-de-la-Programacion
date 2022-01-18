//Flavius Ciapsa y Marcos Herrero

package tp.p1.control.commands;


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
		new PrintModeCommand()
		};
	
	public static Command parseCommand(String[] commandWords){
		Command command = null;
		boolean found = false;
		
		for(int i = 0; !found && i < availableCommands.length; ++i){
			command = availableCommands[i].parse(commandWords);
			if(command != null)found = true;
			}
		
		return command;
	}
	
	public static String commandHelp(){
		StringBuilder str = new StringBuilder();
		
		for(Command command: availableCommands){
			str.append(command.helpText()).append(System.lineSeparator());
		}
		return str.toString();
	}
	
	
}
