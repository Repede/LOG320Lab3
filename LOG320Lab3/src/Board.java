
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
	
	public int[][] getBoard()
	{
		return board;
	}
	public void setBoard(int[][] board)
	{
		this.board = board;
	}
	public void setBoardValue(int x, int y, int value)
	{
		this.board[x][y] = value;
	}
}
