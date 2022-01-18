//Flavius Ciapsa y Marcos Herrero

package tp.p3.control.commands;

import tp.p3.logic.Game;
import tp.p3.logic.factories.ZombieFactory;

public class ZombieListCommand extends NoParamsCommand {
	public static final String[] COMMANDNAMES = {"zombielist", "zl"};
    public static final  String COMMANDTEXT = "ZombieList";
    public static final String HELPTEXT = "Prints the list of available zombies.";
	
	public ZombieListCommand(){
		super(COMMANDNAMES, COMMANDTEXT, HELPTEXT);		
	}
	
	public boolean execute(Game game) {
		System.out.println(ZombieFactory.listOfAvailableZombies());
		return false;
	}

}
