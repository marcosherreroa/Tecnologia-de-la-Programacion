//  y  

package tp.p1.logic.printers;

import tp.p1.logic.Game;

public class DebugPrinter extends BoardPrinter implements GamePrinter {
	   public static final String[] PRINTERNAMES = {"debug", "d"};
	   public static final int CELLSIZE = 18;
	   
	   public DebugPrinter(){
		   super();
	   }
	   
	   public void setDimensions(Game game){
		   dimX = 1;
		   dimY = game.getNumberOfObjects();
	   }
       
       public void encodeGame(Game game){
    	     int p = game.getNumberOfPlants();
  		     int z = game.getNumberOfZombies();
    	   	 board = new String[1][p+z];
    		 
    		 
    			for(int i = 0; i < p; ++i) {
    				board[0][i] = game.PlantsToStringDebug(i);
    				}
    			
    			for(int i = 0; i< z; ++i){
    				board [0][p+i] = game.ZombiesToStringDebug(i);
    			}
   
    	    }
       
       public String printGame( Game game){
      	 StringBuilder str = new StringBuilder();
      	 
      	 str.append(game.getDebugGameState());
      	 setDimensions(game);
      	 encodeGame(game);
      	 str.append(boardToString(game,CELLSIZE));
      	 
      	 return str.toString();
       }
}
