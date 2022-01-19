//  y  

package tp.p1.control;

import java.util.Scanner;

import tp.p1.control.commands.Command;
import tp.p1.control.commands.CommandParser;
import tp.p1.logic.Game;
import tp.p1.logic.printers.DebugPrinter;
import tp.p1.logic.printers.GamePrinter;
import tp.p1.logic.printers.ReleasePrinter;

public class Controller {
   private Game game;
   private Scanner in;
   private boolean play;
   private boolean reset;
   private boolean nextTurn;
   private boolean printAgain;
   private GamePrinter gamePrinter;
   
   public Controller(Game game){
	   this.game = game;
	   this.in = new Scanner(System.in);
	   this.play = true;
	   this.reset = false;
	   this.nextTurn = false;
	   this.printAgain = false;
	   this.gamePrinter = new ReleasePrinter();
   }
   
   public boolean isPlay() {
	return play;
   } 

   public void setPlay(boolean play) {
	this.play = play;
   }

   public boolean isReset() {
	return reset;
   }

   public void setReset(boolean reset) {
	this.reset = reset;
   }

   public boolean isNextTurn() {
	return nextTurn;
   }

   public void setNextTurn(boolean nextTurn) {
	this.nextTurn = nextTurn;
   }
   
   public boolean isPrintAgain(){
	   return printAgain;
   }
   
   public void setPrintAgain(boolean printAgain){
	   this.printAgain = printAgain;
   }
   public void setDebugPrinter() {
		gamePrinter = new DebugPrinter();
  }
  
  public void setReleasePrinter() {
	   gamePrinter = new ReleasePrinter();
  }
  
   public void run(){
	   System.out.println("Random seed used: "+game.getSeed());
	   System.out.println(gamePrinter.printGame(game));
		 
		 while(play){
			 if(reset){
				 game.reset();
				 System.out.println(gamePrinter.printGame(game));
				 reset = false;
			 }
			 nextTurn = false;
			 
			 String commandText; String[] commandWords; Command command;
			   while(!nextTurn){
				 System.out.print("Command > ");
	             commandText = in.nextLine().toLowerCase();
	             commandWords = commandText.trim().split(" ");
	             command = CommandParser.parseCommand(commandWords);
	             
	             while(command == null){
	            	 System.out.println("Unknown command"+System.lineSeparator());
	            	 System.out.print("Command > ");
	            	 commandText = in.nextLine().toLowerCase();
	            	 commandWords = commandText.trim().split(" ");
		             command = CommandParser.parseCommand(commandWords);
	             }
	             
	    		 command.execute(game, this);
	    		 if(printAgain){
	    			 System.out.println(gamePrinter.printGame(game));
	    			 printAgain = false;
	    		 }
			   }
			   
			 if(play && !reset){
			    game.update();
			    System.out.println(gamePrinter.printGame(game));
			    play = !game.checkWinner();
			 }
		 }
	 
	 }
   
}
