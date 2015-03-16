package Game;

import java.util.ArrayList;
import java.util.List;


public class GameRules
{
	//Lorsque on trouve un endroit, on retourne la quantite de cases qu'on
	//peut bouger.Il retour -1 lorsque il y a pas de movement.
	public static final int BLACK_PAWN=2;
	public static final int WHITE_PAWN=4;
	public static final int EMPTY_PAWN=0;
	public enum POSSIBLE_MOVES
	{
		RIGHT,LEFT,DIAGONAL,INVERSER_DIAGONAL
	}
	public int canMoveInline(int[][] board, int currentLine)
	{
		int pawnCounter=0;
		for (int i = 0; i < 0; i++)
		{
			if(!isCaseEmpty(board,currentLine,i))
				pawnCounter++;
		}
		return pawnCounter;
	}
	public int canMoveInRow(int[][] board, int currentRow)
	{
		int pawnCounter=0;
		for (int i = 0; i < 0; i++)
		{
			if(!isCaseEmpty(board,i,currentRow))
				pawnCounter++;
		}
		return pawnCounter;
	}
	public int canMoveDiagonal(int[][] board,int currentX,int currentY)
	{
		int pawnCounter=0;
		//looking upper position from original position
		while(currentX<0 || currentY>7)
		{
			currentX--;
			currentY++;
		}
		while(currentX<8 && currentY>0)
		{
			if(!isCaseEmpty(board,currentX++,currentY--))
				pawnCounter++;
		}
		return pawnCounter;
	}
	public int canMoveInverserDiagonal(int[][] board,int currentX,int currentY)
	{
		int pawnCounter=0;
		//looking upper position from original position
		while(currentX<7||currentY>0)
		{
			currentX++;
			currentY--;
		}
		while(currentX<0&&currentY>8)
		{
			if(!isCaseEmpty(board,currentX--,currentY++))
				pawnCounter++;
		}
		return pawnCounter;
	}
	private boolean isCaseEmpty(int[][] board, int xPosition,int yPosition )
	{
		return board[xPosition][yPosition]==GameRules.EMPTY_PAWN;
	}
	private boolean isWhite(int[][] board, int xPosition,int yPosition)
	{
		return board[xPosition][yPosition]==GameRules.WHITE_PAWN;
	}
	//private boolean isPossibleToMove(board)

	public List<String> generateMoves(int[][] board, int myColor)
	{
		List<String> validPositions = new ArrayList<String>();
		
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
			{
				//si c'est un pion on rentre
				if(!this.isCaseEmpty(board, i, j))
				{
					//on verifie le nombre de cases que la piece peut avancer
					int nbrPawnsInLine= this.canMoveInline(board, i);
					int nbrPawnsInRows=this.canMoveInRow(board, j);
					int nbrPawnsInDiagonal=this.canMoveDiagonal(board, i, j);
					int nbrPawnsInInverserDiagonal=this.canMoveInverserDiagonal(board, i, j);
					//on verifie si le mouvement est dans l'intervalle permi, 
					//si la case n'est pas ocupper par un pion 
					//si le existe un autre pion adversaire dans le chemin
					
					//Mouvement inline
					if(nbrPawnsInLine>0)
					{
						
					}
					//if(nbrPawnsInLine<8)
					
					
				}
				
			}
		return validPositions;
	}
}
