//  y  

package tp.p3.control;

import java.util.Scanner;

import tp.p3.control.commands.Command;
import tp.p3.control.commands.CommandParser;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public class Controller {
   private Game game;
   private Scanner in;
   
   public Controller(Game game){
	   this.game = game;
	   this.in = new Scanner(System.in);
   }
    
   public void run(){
	   System.out.println("Random seed used: "+game.getSeed());
	   System.out.println(game);
		 
		 while(game.isActive()){
			 System.out.print("Command > ");
        	 String commandText = in.nextLine().toLowerCase();
        	 String[] commandWords = commandText.trim().split(" ");
        	 
			 try{
            	 Command command = CommandParser.parseCommand(commandWords);
                 if(command.execute(game)) System.out.println(game);
			 }
			 
			 catch(CommandParseException| CommandExecuteException e){
				 System.out.println(e.getMessage()+System.lineSeparator());
			 }
			 
			 game.checkWinner();
		}
	}
}

