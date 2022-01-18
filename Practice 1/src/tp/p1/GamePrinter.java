//Alumnos: Marcos Herrero Agustin
//         Flavius Abel Ciapsa
package tp.p1;

public class GamePrinter {
	private int dimX; 
	private int dimY;
	private String[][] board;
	private final String space = " ";
	
	
	public GamePrinter(Game game, int dimX, int dimY) {
		this.dimX = dimX;
		this.dimY = dimY;
		
		encodeGame(game);
	}
	
	private void encodeGame(Game game) {
		board = new String[dimX][dimY];
		Position pos = new Position();
		
		for(int x = 0; x < dimX; ++x) {
			pos.setX(x);
			for(int y = 0; y < dimY; ++y) {
				pos.setY(y);
				board[x][y] = game.posToString(pos);
			}
		}
	}
	
	public String toString() {

		int cellSize = 7;
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
}

