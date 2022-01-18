//Flavius Ciapsa y Marcos Herrero

package tp.p1.logic.printers;

import tp.p1.logic.Game;
import tp.p1.logic.objects.Position;

public class ReleasePrinter extends BoardPrinter implements GamePrinter {
	public static final String[] PRINTERNAMES = {"release", "r"} ;
	public static final int CELLSIZE = 7;
	
	public ReleasePrinter(){
		super();
	}
	
     public void setDimensions(Game game){
    	 dimX= Game.NROWS;
    	 dimY= Game.NCOLUMNS;
     }
     
     public void encodeGame(Game game){
       	 board = new String[Game.NROWS][Game.NCOLUMNS];
    		Position pos = new Position();
    		
    		for(int x = 0; x < dimX; ++x) {
    			pos.setX(x);
    			for(int y = 0; y < dimY; ++y) {
    				pos.setY(y);
    				board[x][y] = game.toStringRelease(pos);
    			}
    		}
        }
     
     public String printGame( Game game){
    	 StringBuilder str = new StringBuilder();
    	 
    	 str.append(game.getRealeaseGameState());
    	 setDimensions(game);
    	 encodeGame(game);
    	 str.append(boardToString(game,CELLSIZE ));
    	 
    	 return str.toString();
     }
}
