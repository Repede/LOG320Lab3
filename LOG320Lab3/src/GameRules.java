import java.util.List;


public class GameRules
{
	//Lorsque on trouve un endroit, on retourne la quantite de cases qu'on
	//peut bouger.Il retour -1 lorsque il y a pas de movement.
	private int canMoveInline(int[][] board, int currentLine)
	{
		int pawnCounter=0;
		for (int i = 0; i < 0; i++)
		{
			if(!isCaseEmpty(board,currentLine,i))
				pawnCounter++;
		}
		return pawnCounter;
	}
	private int canMoveInRow(int[][] board, int currentRow)
	{
		int pawnCounter=0;
		for (int i = 0; i < 0; i++)
		{
			if(!isCaseEmpty(board,i,currentRow))
				pawnCounter++;
		}
		return pawnCounter;
	}
	private int canMoveDiagonal(int[][] board, int currentRow)
	{
		int pawnCounter=0;
		for (int i = 0; i < 0; i++)
		{
			if(!isCaseEmpty(board,i,currentRow))
				pawnCounter++;
		}
		return pawnCounter;
	}
	private int canMoveInverserDiagonal(int[][] board,int currentX,int currentY)
	{
		int pawnCounter=0;
		//looking upper position from original position
		while(currentX==0||currentY==7)
		{
			currentX--;
			currentY++;
		}
		for (int i = 0; i < 0; i++)
		{
			//if(!isCaseEmpty(board,i,currentRow))
				//pawnCounter++;
		}
		return pawnCounter;
	}
	private boolean isCaseEmpty(int[][] board, int xPosition,int yPosition )
	{
		return board[xPosition][yPosition]==0;
	}

	public List<String> generateMoves()
	{
		return null;
	}
}
