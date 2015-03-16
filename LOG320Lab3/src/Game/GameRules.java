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
	
	public int calculatePawnsInRow(int[][] board, int currentLine)
	{
		int pawnCounter=0;
		for (int i = 0; i < 8; i++)
		{
			if(!isCaseEmpty(board,currentLine,i))
				pawnCounter++;
		}
		return pawnCounter;
	}
	public int calculatePawnsInColumn(int[][] board, int currentRow)
	{
		int pawnCounter=0;
		for (int i = 0; i < 8; i++)
		{
			if(!isCaseEmpty(board,i,currentRow))
				pawnCounter++;
		}
		return pawnCounter;
	}
	public int calculatePawnsInDiagonal(int[][] board,int currentX,int currentY)
	{
		int pawnCounter=0;
		//looking upper position from original position. Parcours de bas vers le haut, gauche à droite
		while(currentX<7 && currentY>0)
		{
			currentX++;
			currentY--;
		}
		while(currentX>=0 && currentY<=7)
		{			
			if(!isCaseEmpty(board,currentX,currentY))
				pawnCounter++;
			
			currentX--;
			currentY++;
		}
		return pawnCounter;
	}
	public int calculatePawnsInReverseDiagonal(int[][] board,int currentX,int currentY)
	{
		int pawnCounter=0;
		//looking upper position from original position
		while(currentX>0&&currentY>0)
		{
			currentX--;
			currentY--;
		}
		while(currentX<=7&&currentY<=7)
		{			
			if(!isCaseEmpty(board,currentX,currentY))
				pawnCounter++;
			
			currentX++;
			currentY++;
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
	
	
	public boolean validateMouvementInRow(int i, int j, int nbrPawnsInRow, int[][] board, boolean toLeft){
		boolean isValid = true;
		int thePawn =  board[i][j];
		int initialPosition = j;
		
		if(toLeft){
			while(j < initialPosition+nbrPawnsInRow && j != -1){
				if(board[i][j] != thePawn && board[i][j] != 0){
					isValid = false;
				}
				
				j--;
			}
			
			if(initialPosition-nbrPawnsInRow<=-1)
				isValid = false;
			else if(board[i][initialPosition-nbrPawnsInRow] == thePawn){
				isValid = false;
			}
		}
		else{
			while(j < initialPosition+nbrPawnsInRow && j != 8){
				if(board[i][j] != thePawn && board[i][j] != 0){
					isValid = false;
				}
				
				j++;
			}
			
			if(initialPosition+nbrPawnsInRow>7)
				isValid = false;
			else if(board[i][initialPosition+nbrPawnsInRow] == thePawn){
				isValid = false;
			}
		}
		
		return isValid;
	}
	public boolean validateMouvementInColumn(int i, int j, int nbrPawnsInColumn, int[][] board, boolean toDown){
		boolean isValid = true;
		int  thePawn =  board[i][j];
		int initialPosition = i;
		
		if(toDown){
			while(i < initialPosition+nbrPawnsInColumn && i != 8){
				if(board[i][j] != thePawn && board[i][j] != 0){
					isValid = false;
				}
				
				i++;
			}
			
			if(initialPosition+nbrPawnsInColumn > 7)
				isValid = false;
			else if(board[initialPosition+nbrPawnsInColumn][j] == thePawn){
				isValid = false;
			}
		}
		else{
			
			while(i > initialPosition-nbrPawnsInColumn && i != -1){
				if(board[i][j] != thePawn && board[i][j] != 0){
					isValid = false;
				}
				
				i--;
			}
			
			if(initialPosition-nbrPawnsInColumn<0)
				isValid = false;
			else if(board[initialPosition-nbrPawnsInColumn][j] == thePawn){
				isValid = false;
			}
		}
							
		return isValid;
	}
	public boolean validateMouvementInDiagonal(int i, int j, int nbrPawnsInDiagonal, int[][] board, boolean downToUp){
		// Diagonal = leftToRight
		boolean isValid = true;
		int thePawn = board[i][j];
		int initialPositionI = i;
		int initialPositionJ = j;
		
		if(downToUp){
			while(i > initialPositionI-nbrPawnsInDiagonal && j < initialPositionJ+nbrPawnsInDiagonal && i != -1 && j != -1){
				if(board[i][j] != thePawn && board[i][j] != 0){
					isValid = false;
				}
				
				j++;
				i--;
				
			}
			
			if(initialPositionI-nbrPawnsInDiagonal<0 && initialPositionJ+nbrPawnsInDiagonal>7){
				isValid = false;
			}
			else if( board[initialPositionI-nbrPawnsInDiagonal][initialPositionJ+nbrPawnsInDiagonal] == thePawn){
				isValid = false;
			}
			
		}
		else{
			while(i < initialPositionI+nbrPawnsInDiagonal && j > initialPositionJ-nbrPawnsInDiagonal && i != 8 && j != 8){
				if(board[i][j] != thePawn && board[i][j] != 0){
					isValid = false;
				}
				
				j--;
				i++;
			}
			
			if(initialPositionI+nbrPawnsInDiagonal>7 && initialPositionJ-nbrPawnsInDiagonal<0){
				isValid = false;
			}
			else if( board[initialPositionI+nbrPawnsInDiagonal][initialPositionJ-nbrPawnsInDiagonal] == thePawn){
				isValid = false;
			}
		}
		
		return isValid;
	}
	public boolean validateMouvementInReverseDiagonal(int i, int j, int nbrPawnsInDiagonal, int[][] board, boolean downToUp){
		// Diagonal = right to left
		boolean isValid = true;
		int thePawn = board[i][j];
		int initialPositionI = i;
		int initialPositionJ = j;
		
		if(downToUp){
			while(i > initialPositionI-nbrPawnsInDiagonal && j > initialPositionJ-nbrPawnsInDiagonal && i != -1 && j != -1){
				if(board[i][j] != thePawn && board[i][j] != 0){
					isValid = false;
				}
				
				j--;
				i--;
			}
			
			if(initialPositionI-nbrPawnsInDiagonal<0 && initialPositionJ-nbrPawnsInDiagonal<0){
				isValid = false;
			}
			else if( board[initialPositionI-nbrPawnsInDiagonal][initialPositionJ-nbrPawnsInDiagonal] == thePawn){
				isValid = false;
			}
		}
		else{
			while(i < initialPositionI+nbrPawnsInDiagonal && j < initialPositionJ+nbrPawnsInDiagonal && i != 8 && j != 8){
				if(board[i][j] != thePawn && board[i][j] != 0){
					isValid = false;
				}
				
				j++;
				i++;
			}
			
			if(initialPositionI+nbrPawnsInDiagonal>7 && initialPositionJ+nbrPawnsInDiagonal>7){
				isValid = false;
			}
			else if( board[initialPositionI+nbrPawnsInDiagonal][initialPositionJ+nbrPawnsInDiagonal] == thePawn){
				isValid = false;
			}
		}
		
		return isValid;
	}
	
	public List<String> generateMoves(int[][] board, int myColor)
	{
		List<String> validPositions = new ArrayList<String>();
		
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
			{
				// Si c'est un pion on rentre
				if(!this.isCaseEmpty(board, i, j))
				{					
					// On calcul le nombre de piece par direction
					int nbrPawnsInLine= this.calculatePawnsInRow(board, i);
					int nbrPawnsInColumn=this.calculatePawnsInColumn(board, j);
					int nbrPawnsInDiagonal=this.calculatePawnsInDiagonal(board, i, j);
					int nbrPawnsInReverseDiagonal=this.calculatePawnsInReverseDiagonal(board, i, j);
					
					// On verifie si le mouvement est dans l'intervalle permi 
					if(validateMouvementInRow(i, j, nbrPawnsInLine, board, true)){
						validPositions.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i) + String.valueOf(j - nbrPawnsInLine ));
					}
					if(validateMouvementInRow(i, j, nbrPawnsInLine, board, false)){
						validPositions.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i) + String.valueOf(j + nbrPawnsInLine ));
					}
					
					if(validateMouvementInColumn(i, j, nbrPawnsInColumn, board, true)){
						validPositions.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i - nbrPawnsInColumn ) + String.valueOf(j));
					}
					if(validateMouvementInColumn(i, j, nbrPawnsInColumn, board, false)){
						validPositions.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i + nbrPawnsInColumn ) + String.valueOf(j));
					}
					
					if(validateMouvementInDiagonal(i, j, nbrPawnsInDiagonal, board, true)){
						validPositions.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i - nbrPawnsInDiagonal ) + String.valueOf(j + nbrPawnsInDiagonal));
					}
					if(validateMouvementInDiagonal(i, j, nbrPawnsInDiagonal, board, true)){
						validPositions.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i + nbrPawnsInDiagonal ) + String.valueOf(j - nbrPawnsInDiagonal));
					}
					
					if(validateMouvementInReverseDiagonal(i, j, nbrPawnsInReverseDiagonal, board, true)){
						validPositions.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i + nbrPawnsInDiagonal ) + String.valueOf(j + nbrPawnsInDiagonal));
					}
					if(validateMouvementInReverseDiagonal(i, j, nbrPawnsInReverseDiagonal, board, true)){
						validPositions.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i - nbrPawnsInDiagonal ) + String.valueOf(j - nbrPawnsInDiagonal));
					}					
				}
				
			}
		return validPositions;
	}
}
