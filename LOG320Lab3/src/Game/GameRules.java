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
		//looking upper position from original position. Parcours de bas vers le haut, gauche � droite
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
		
		// On se déplace vers la gauche dans la ligne
		if(toLeft){
			// On regarde si le déplacement sort du board
			if(initialPosition-nbrPawnsInRow < 0)
				return false;
						
			// On regarde la ligne d'action s'il y a un pion de l'autre couleur
			while(j > initialPosition-nbrPawnsInRow){
				if(board[i][j] != thePawn && board[i][j] != 0){
					return false;
				}
				
				j--;
			}
			
			// On regarde si au bout de notre déplacement il y a un pion allié
			if(board[i][initialPosition-nbrPawnsInRow] == thePawn){
				return false;
			}
		}
		// On se déplace vers la droite dans la ligne
		else{
			// On regarde si le déplacement sort du board
			if(initialPosition+nbrPawnsInRow > 7)
				return false;
						
			// On regarde dans la ligne d'action s'il y a un pion de l'autre couleur
			while(j < initialPosition+nbrPawnsInRow){
				if(board[i][j] != thePawn && board[i][j] != 0){
					return false;
				}
				
				j++;
			}
			
			// On regarde si au bout de notre déplacement il y a un pion allié
			if(board[i][initialPosition+nbrPawnsInRow] == thePawn){
				return false;
			}
		}
		
		return isValid;
	}
	public boolean validateMouvementInColumn(int i, int j, int nbrPawnsInColumn, int[][] board, boolean toDown){
		boolean isValid = true;
		int  thePawn =  board[i][j];
		int initialPosition = i;
		
		// On se déplace vers le bas dans la colonne
		if(toDown){
			// On regarde si le déplacement sort du board
			if(initialPosition+nbrPawnsInColumn > 7)
				return false;
						
			// On regarde la ligne d'action s'il y a un pion de l'autre couleur
			while(i < initialPosition+nbrPawnsInColumn){
				if(board[i][j] != thePawn && board[i][j] != 0){
					return false;
				}
				
				i++;
			}
			
			// On regarde si au bout de notre déplacement il y a un pion allié
			if(board[initialPosition+nbrPawnsInColumn][j] == thePawn){
				return false;
			}
		}
		// On se déplace vers le haut dans la colonne
		else{
			// On regarde si le déplacement sort du board
			if(initialPosition-nbrPawnsInColumn < 0)
				return false;
						
			// On regarde la ligne d'action s'il y a un pion de l'autre couleur
			while(i > initialPosition-nbrPawnsInColumn){
				if(board[i][j] != thePawn && board[i][j] != 0){
					return false;
				}
				
				i--;
			}
			
			// On regarde si au bout de notre déplacement il y a un pion allié
			if(board[initialPosition-nbrPawnsInColumn][j] == thePawn){
				return false;
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
		
		// On se déplace du bas vers le haut dans la diagonale (de gauche à droite)
		if(downToUp){
			// On regarde si le déplacement sort du board
			if(initialPositionI-nbrPawnsInDiagonal < 0 || initialPositionJ+nbrPawnsInDiagonal > 7){
				return false;
			}
						
			// On regarde la ligne d'action s'il y a un pion de l'autre couleur
			while(i > initialPositionI-nbrPawnsInDiagonal && j < initialPositionJ+nbrPawnsInDiagonal){
				if(board[i][j] != thePawn && board[i][j] != 0){
					return false;
				}
				
				j++;
				i--;
				
			}
			
			// On regarde si au bout du déplacement il y a un pion allié
			if(board[initialPositionI-nbrPawnsInDiagonal][initialPositionJ+nbrPawnsInDiagonal] == thePawn){
				return false;
			}
			
		}
		// On de déplace du haut vers le bas dans la diagonale (de gauche à droite)
		else{
			// On regarde si le déplacement sort du board
			if(initialPositionI+nbrPawnsInDiagonal > 7 || initialPositionJ-nbrPawnsInDiagonal < 0){
				return false;
			}
						
			// On regarde dans la ligne d'action s'il y a un pion de l'autre couleur
			while(i < initialPositionI+nbrPawnsInDiagonal && j > initialPositionJ-nbrPawnsInDiagonal){
				if(board[i][j] != thePawn && board[i][j] != 0){
					return false;
				}
				
				j--;
				i++;
			}
			
			// On regarde si au bout du déplacement il y a un pion allié
			if(board[initialPositionI+nbrPawnsInDiagonal][initialPositionJ-nbrPawnsInDiagonal] == thePawn){
				return false;
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
		
		// On de déplace du bas vers le haut dans la diagonale inverse (de droite à gauche)
		if(downToUp){
			// On regarde si le déplacement sort du board
			if(initialPositionI-nbrPawnsInDiagonal < 0 || initialPositionJ-nbrPawnsInDiagonal < 0){
				return false;
			}
			
			// On regarde si dans la ligne d'action il y a un pion de l'autre couleur
			while(i > initialPositionI-nbrPawnsInDiagonal && j > initialPositionJ-nbrPawnsInDiagonal){
				if(board[i][j] != thePawn && board[i][j] != 0){
					return false;
				}
				
				j--;
				i--;
			}
			
			// On regarde si au bout du déplacement il y a un pion allié
			if(board[initialPositionI-nbrPawnsInDiagonal][initialPositionJ-nbrPawnsInDiagonal] == thePawn){
				return false;
			}
		}
		// On se déplace de haut en bas dans la diagonale inverse (droite à gauche)
		else{
			// On regarde si le déplacement sort du board
			if(initialPositionI+nbrPawnsInDiagonal > 7 || initialPositionJ+nbrPawnsInDiagonal > 7){
				return false;
			}
			
			// On regarde si dans la ligne d'action il y a un pion de l'autre couleur
			while(i < initialPositionI+nbrPawnsInDiagonal && j < initialPositionJ+nbrPawnsInDiagonal){
				if(board[i][j] != thePawn && board[i][j] != 0){
					return false;
				}
				
				j++;
				i++;
			}
			
			// On regarde si au bout du déplacement il y a un pion allié
			if( board[initialPositionI+nbrPawnsInDiagonal][initialPositionJ+nbrPawnsInDiagonal] == thePawn){
				return false;
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
				if(!this.isCaseEmpty(board, i, j) && board[i][j] == myColor)
				{					
					// On calcul le nombre de piece par direction
					int nbrPawnsInLine= this.calculatePawnsInRow(board, i);
					int nbrPawnsInColumn=this.calculatePawnsInColumn(board, j);
					int nbrPawnsInDiagonal=this.calculatePawnsInDiagonal(board, i, j);
					int nbrPawnsInReverseDiagonal=this.calculatePawnsInReverseDiagonal(board, i, j);
					
					// On verifie si le mouvement est dans l'intervalle permi 
					if(validateMouvementInRow(i, j, nbrPawnsInLine, board, true)){
						validPositions.add(Integer.toString(i) + Integer.toString(j) + Integer.toString(i) + Integer.toString(j - nbrPawnsInLine ));
					}
					if(validateMouvementInRow(i, j, nbrPawnsInLine, board, false)){
						validPositions.add(Integer.toString(i) + Integer.toString(j) + Integer.toString(i) + Integer.toString(j + nbrPawnsInLine ));
					}
					
					if(validateMouvementInColumn(i, j, nbrPawnsInColumn, board, true)){
						validPositions.add(Integer.toString(i) + Integer.toString(j) + Integer.toString(i + nbrPawnsInColumn ) + Integer.toString(j));
					}
					if(validateMouvementInColumn(i, j, nbrPawnsInColumn, board, false)){
						validPositions.add(Integer.toString(i) + Integer.toString(j) + Integer.toString(i - nbrPawnsInColumn ) + Integer.toString(j));
					}
					
					if(validateMouvementInDiagonal(i, j, nbrPawnsInDiagonal, board, true)){
						validPositions.add(Integer.toString(i) + Integer.toString(j) + Integer.toString(i - nbrPawnsInDiagonal ) + Integer.toString(j + nbrPawnsInDiagonal));
					}
					if(validateMouvementInDiagonal(i, j, nbrPawnsInDiagonal, board, false)){
						validPositions.add(Integer.toString(i) + Integer.toString(j) + Integer.toString(i + nbrPawnsInDiagonal ) + Integer.toString(j - nbrPawnsInDiagonal));
					}
					
					if(validateMouvementInReverseDiagonal(i, j, nbrPawnsInReverseDiagonal, board, false)){
						validPositions.add(Integer.toString(i) + Integer.toString(j) + Integer.toString(i + nbrPawnsInReverseDiagonal ) + Integer.toString(j + nbrPawnsInReverseDiagonal));
					}
					if(validateMouvementInReverseDiagonal(i, j, nbrPawnsInReverseDiagonal, board, true)){
						validPositions.add(Integer.toString(i) + Integer.toString(j) + Integer.toString(i - nbrPawnsInReverseDiagonal ) + Integer.toString(j - nbrPawnsInReverseDiagonal));
					}					
				}
				
			}
		
		return validPositions;
	}
}