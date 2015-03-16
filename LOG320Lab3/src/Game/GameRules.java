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
	
	public boolean mouvementLigneEstValid(int position, int nbrPawnsInLine){
		boolean isValid = false;
		
		// Mouvement ligne vers la droite.
		if(position+nbrPawnsInLine<=7)
			//Mouvement ligne vers la gauche.
			if(position-nbrPawnsInLine>=0)
				isValid = true;
		
		return isValid;
	}
	public boolean mouvementColumnEstValid(int position, int nbrPawnsInColumn){
		boolean isValid = false;
		
		// Mouvement de la column vers le bas.
		if(position+nbrPawnsInColumn<=7)
			// Mouvement de la column vers le haut.
			if(position+nbrPawnsInColumn>=0)
				isValid = true;
		
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
					int nbrPawnsInInverserDiagonal=this.calculatePawnsInReverseDiagonal(board, i, j);
					
					// #TODO: On verifie si le mouvement est dans l'intervalle permi 
					if(mouvementLigneEstValid(j, nbrPawnsInLine)){
						// #TODO: mettre le coup valide à jouer sous forme ex: D6D5
						
						//String letter = RetrieveLetterFromNumber(j);
					}
					if(mouvementColumnEstValid(i, nbrPawnsInColumn)){
						// #TODO: mettre le coup valide à jouer sous forme ex: D6D5
					}
					
					
						
					//Mouvement in diagonal top
					//if(i-nbrPawnsInDiagonal>0&&j+nbrPawnsInDiagonal<8)
					
					//Mouvement in diagonal bottom
					//if(i+nbrPawnsInDiagonal>0&&j-nbrPawnsInDiagonal<8)
					//{
						
					//}
					//Mouvement in inverserDiagonal top
					//if(i+nbrPawnsInDiagonal>0&&j-nbrPawnsInDiagonal<8)
					//{
						
					//}
					//Mouvement in inverserDiagonal bottom
					//if(i-nbrPawnsInDiagonal>0&&j+nbrPawnsInDiagonal<8)
					//{
						
					//}
					
					
					//#TODO: si la case n'est pas ocupper par un pion 
					//#TODO: si le existe un autre pion adversaire dans le chemin
					
					
				}
				
			}
		return validPositions;
	}
}
