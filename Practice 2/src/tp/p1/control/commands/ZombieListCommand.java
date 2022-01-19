//  y  

package tp.p1.control.commands;

import tp.p1.control.Controller;
import tp.p1.logic.Game;
import tp.p1.logic.factories.ZombieFactory;

public class ZombieListCommand extends NoParamsCommand {
	public static final String[] COMMANDNAMES = {"zombielist", "zl"};
    public static final  String COMMANDTEXT = "ZombieList";
    public static final String HELPTEXT = "Prints the list of available zombies.";
	
	public ZombieListCommand(){
		super(COMMANDNAMES, COMMANDTEXT, HELPTEXT);		
	}
	
	public void execute(Game game, Controller controller) {
		System.out.println(ZombieFactory.listOfAvailableZombies());
	}

}
