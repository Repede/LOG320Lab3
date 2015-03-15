
public class Board
{
	private int[][] board = new int[8][8];
	
	public void displayBoard()
	{
		StringBuilder boardDisplay = new StringBuilder("");
		for(int i = 0 ; i < 8 ; ++i)
		{
			boardDisplay.append((8-i)+" ");
			for(int j = 0 ; j < 8 ; ++j)
			{
				if(board[i][j] == 0)
				{
					boardDisplay.append(".");
				}
				if(board[i][j] == 2)
				{
					boardDisplay.append("o");
				}
				if(board[i][j] == 4)
				{
					boardDisplay.append("x");
				}
			}
			boardDisplay.append("\n");
		}
		boardDisplay.append("  ABCDEFGH\n");
		System.out.println(boardDisplay.toString());
	}
	
	public Board updateBoard(Board gameBoard, String movement)
	{
		String[] movementToUpdate = new String[2];
		int[] boardCoord1;
		int[] boardCoord2;
		
		if(movement.contains("-")) {
			movementToUpdate = movement.split("-");
		} else {
			movementToUpdate[0] = movement.substring(0, 2);
			movementToUpdate[1] = movement.substring(2);
		}
		
		movementToUpdate[0] = movementToUpdate[0].trim();
		movementToUpdate[1] = movementToUpdate[1].trim();
		
		boardCoord1 = letterToBoardIndex(movementToUpdate[0]);
		// On va chercher l'indice de la couleur d'où provient le déplacement.
		int movementColor = getBoardValue(boardCoord1[0], boardCoord1[1]);
		// On met la case vide à l'endroit d'où provient le déplacement.
		gameBoard.setBoardValue(boardCoord1[0], boardCoord1[1], 0);
		
		boardCoord2 = letterToBoardIndex(movementToUpdate[1]);
		// On effectue met la couleur du déplacement effectué.
		gameBoard.setBoardValue(boardCoord2[0], boardCoord2[1], movementColor);
		
		return gameBoard;
	}
	
	public int[] letterToBoardIndex(String movementToUpdate)
	{
		char letter = movementToUpdate.charAt(0);
		int x = 0;
		int y = Integer.parseInt(movementToUpdate.substring(1));
		
		int[] boardCoord = new int[2];
		
		switch(letter) {
			case 'A': 
				x = 0;
		    	break;
			case 'B':
				x = 1;
		     	break;
			case 'C':
				x = 2;
		     	break;
			case 'D':
				x = 3;
		     	break;
			case 'E':
				x = 4;
		     	break;
			case 'F':
				x = 5;
		     	break;
			case 'G':
				x = 6;
		     	break;
			case 'H':
				x = 7;
		     	break;
			default: 
				System.out.println("Non ça me tente pas de faire que ya un problème.");
		  		break;
		}
		boardCoord[0] = 8-y;
		boardCoord[1] = x;
		return boardCoord;
	}
	
	public int[][] getBoard()
	{
		return board;
	}
	
	public void setBoard(int[][] board)
	{
		this.board = board;
	}
	
	public int getBoardValue(int x, int y)
	{
		int value;
		value = board[x][y];
		return value;
	}
	
	public void setBoardValue(int x, int y, int value)
	{
		this.board[x][y] = value;
	}
}
