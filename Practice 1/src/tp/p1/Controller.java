//Alumnos: Marcos Herrero Agustin
//         Flavius Abel Ciapsa

package tp.p1;

import java.util.Scanner;

public class Controller {
   private Game game;
   private Scanner in;
   private boolean play;
   private boolean reset;  
   private boolean nextTurn;
   
   public Controller(Game game){
	   this.game = game;
	   this.in = new Scanner(System.in);
	   this.play = true;
	   this.reset = false;
	   this.nextTurn = false;
   }
   
   public void showInfoCommands(){
	   System.out.println("Add <plant> <x> <y>: Adds a plant in position x, y.");
	   System.out.println("List: Prints the list of available plants.");
	   System.out.println("Reset: Starts a new game.");
	   System.out.println("Help: Prints this help message.");
	   System.out.println("Exit: Terminates the program.");
	   System.out.println("[none]: Skips cycle.");
   }
   
   
   public boolean userAdd(String[] command){
		Position pos = new Position(Integer.parseInt(command[2]), Integer.parseInt(command[3]));
		if(!game.validPosition(pos)){
				 System.out.println("Invalid position");
				 return false;
		}
		else if(game.somethingThere(pos)){
				 System.out.println("There is something there!");
				 return false;
		}
		else {
			boolean OK;
			
		   switch(command[1].toLowerCase()){
			 case "s":
			 case "sunflower": {
				 OK= game.addSunflower(pos);
				 if(!OK)System.out.println("Not enough suncoins");	
				 return OK;
			 }
				 
			 case "p":
			 case "peashooter": {
				 OK= game.addPeashooter(pos);
				 if(!OK)System.out.println("Not enough suncoins");	
				 return OK;
			 }
			 default: {
				System.out.println("Invalid object to add");
				return false;
			 }
		   }
		}
	} 
   
   public void exeCommand(String[] command){
	   switch(command[0].toLowerCase()){
		 case "a":
		 case "add": {
			 if(command.length != 4){
				 System.out.println("Invalid format for add command");
				 nextTurn = false;
			 }
			 else nextTurn= userAdd(command);
			 break;
		 }
		 
		 case "reset":{
			 if(command.length != 1){
				 System.out.println("Invalid format for reset command");
				 nextTurn = false; 
		     }
			 else {
				 reset = true;    // se resetea al inicio del siguiente ciclo
				 nextTurn = true;
			 }
			 break;
		 }
		 
		 case "l":
		 case "list":{
			 if(command.length != 1){
				 System.out.println("Invalid format for list command");
				 nextTurn = false; 
		     }
			 else {
				 game.showInfoPlants();
				 nextTurn = false;
			 }
			 break;
		 }
		 case "":
		 case "n":
		 case "none":{
			 if(command.length != 1){
				 System.out.println("Invalid format for none command");
				 nextTurn = false; 
		     }
			 else nextTurn = true;
			 break;
		 }
		 case "e":
		 case "exit":{
			 if(command.length != 1){
				 System.out.println("Invalid format for exit command");
				 nextTurn = false; 
		     }
			 else {
				 System.out.println("Game over");
				 play = false;
				 nextTurn = true;
			 }
			 break;
		 }
		 case "h":
		 case "help":{
			 if(command.length != 1){
				 System.out.println("Invalid format for help command");
				 nextTurn = false; 
		     }
			 else {
				 showInfoCommands();
				 nextTurn = false;
			 }
			 break;
		 }
		 default:{
			 System.out.println("Invalid command");
			 nextTurn = false;
	   }
   }
   }
   
   public void run(){
	 System.out.println(game.toString());
	 
	 while(play){
		 if(reset){
			 game.reset();
			 System.out.println(game.toString());
			 reset = false;
		 }
		 nextTurn = false;
		 
		 String phrase; String[] command;
		 
		   while(!nextTurn){
			 System.out.print("Command > ");
             phrase = in.nextLine();
             command = phrase.split(" ");
    		 exeCommand(command); 
		   }
		 
		 if(play && !reset){  
		    game.update();
		    System.out.println(game.toString());
		 
		 
		    if (game.playerWins()){
			  System.out.println("Game over");
			  System.out.println("Player wins!");
			  play = false;
		     } 
		 
		     else if (game.zombiesWin()){
			   System.out.println("Game over");
			   System.out.println("Zombies win!");
			   play = false;
		     }
		 }
	 }
	 
   }
   
}
