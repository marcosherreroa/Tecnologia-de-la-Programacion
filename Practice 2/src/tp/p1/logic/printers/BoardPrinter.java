//  y  

package tp.p1.logic.printers;

import tp.p1.MyStringUtils;
import tp.p1.logic.Game;

public abstract class BoardPrinter{
	protected int dimX;
	protected int dimY;
	protected String[][] board;
	protected final String space = " ";
	
	public BoardPrinter(){
		this.dimX = 0;
		this.dimY = 0;
		this.board = null;
	}
	
	public String boardToString(Game game, int cellSize){
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (dimY * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
	
		str.append(lineDelimiter);
		
		for(int i=0; i<dimX; i++) {
				str.append(margin).append(vDelimiter);
				for (int j=0; j<dimY; j++) {
					str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
				str.append(lineDelimiter);
		}
		return str.toString();
	}
	
	public abstract void encodeGame(Game game);
	public abstract void setDimensions(Game game);
}
