package Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Evaluator
{

	/**
	 *  https://i.imgflip.com/iwhub.jpg
	 */
	public Map<Integer,MinMaxNode> evaluateBestMoves(List<String> validPositions, Board rootBoard, int color)
	{
		Map<Integer,MinMaxNode> minMaxDictionary = new HashMap<Integer,MinMaxNode>();
		//On garde une copie du rootBoard pour pouvoir �valuer plusieurs moves du root
		Board referenceBoard = new Board(rootBoard);
		for(int i = 0 ; i < validPositions.size() ; ++i)
		{
			//On Pr�pare une racine
			MinMaxNode node = new MinMaxNode();
			//2 Transformer les validPositions en board (on fait le move dans referenceBoard selon la couleur)
			referenceBoard.updateBoard(referenceBoard, validPositions.get(i));
			//On ajoute le board, le move et le poid du board dans la racine
			node.setBoard(new Board(referenceBoard));
			node.setMove(validPositions.get(i));
			node.setValue(this.calculateBoardWeight(referenceBoard));
			//On met la racine dans la liste
			minMaxDictionary.put(i,node);
			//On recr�� le board
			referenceBoard = new Board(rootBoard);
		}
		
		return minMaxDictionary;
	}

	/**
	 * Evalue les moves possible et les assignes directement au Node parent envoye
	 * @param validPositions
	 * @param rootBoard
	 * @param color
	 */
	public void createParentsChildren(List<String> validPositions, MinMaxNode rootBoard, int color)
	{
		Map<Integer,MinMaxNode> minMaxDictionary = new HashMap<Integer,MinMaxNode>();
		//On garde une copie du rootBoard pour pouvoir �valuer plusieurs moves du root
		Board referenceBoard = new Board(rootBoard.getBoard());
		for(int i = 0 ; i < validPositions.size() ; ++i)
		{
			//On Pr�pare une racine
			MinMaxNode node = new MinMaxNode();
			//2 Transformer les validPositions en board (on fait le move dans referenceBoard selon la couleur)
			referenceBoard.updateBoard(referenceBoard, validPositions.get(i));
			//On ajoute le board, le move et le poid du board dans la racine
			node.setBoard(new Board(referenceBoard));
			node.setMove(validPositions.get(i));
			node.setValue(this.calculateBoardWeight(referenceBoard));
			node.setParent(rootBoard);
			//On met la racine dans la liste
			minMaxDictionary.put(i,node);
			//On recr�� le board
			referenceBoard = new Board(rootBoard.getBoard());
		}
	}
	
	private int calculateBoardWeight(Board board)
	{
		// basé sur http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.4.3549&rep=rep1&type=pdf
		int numberQuad1 = 0;
		int numberQuad3 = 0;
		int numberQuadd = 0;
		
		for(int i = 0 ; i < 7 ; i++)
		{
			for(int j = 0 ; j < 7 ; j++)
			{
				// calcul de Q1
				numberQuad1 += validateQuad1(board.getBoard(), i, j);
				// calcul de Q3
				numberQuad3 += validateQuad3(board.getBoard(), i, j);
				// calcul de Qd
				numberQuadd += validateQuadd(board.getBoard(), i, j);
;			}
		}
		
		// calculate Euler E = ( ∑Q1 −∑Q3 − 2 ∑Qd) / 4 
		return (numberQuad1 - numberQuad3 - 2*numberQuadd) / 4;
	}

	/**
	 * Quad d
	 * |1|0|  et  |0|1|
	 * |0|1|      |1|0|
	 * @param board
	 * @param i
	 * @param j
	 * @return
	 */
	private int validateQuadd(int[][] board, int i, int j)
	{
		// premier cas
		if(board[i][j] == 1)
		{
			if(board[i][j+1] == 0  &&  board[i+1][j] == 0  &&  board[i+1][j+1] == 1 ){
				return 1;
			}
		}
		// deuxieme cas
		else if(board[i][j] == 0)
		{
			if(board[i][j+1] == 1  &&  board[i+1][j] == 1  &&  board[i+1][j+1] == 0 ){
				return 1;
			}	
		}
	
		return 0;
	}

	/**
	 * Quad 3
	 * |1|1|
	 * |0|1|
	 * @param board
	 * @param i
	 * @param j
	 * @return
	 */
	private int validateQuad3(int[][] board, int i, int j)
	{
		if(board[i][j] == 1)
		{
			if(board[i][j+1] == 1  &&  board[i+1][j] == 0  &&  board[i+1][j+1] == 1 ){
				return 1;
			}
		}
		return 0;
	}

	/**
	 * Quad 1
	 * |1|0|
	 * |0|0| 
	 * @param board
	 * @param i
	 * @param j
	 * @return
	 */
	private int validateQuad1(int[][] board, int i, int j)
	{
		if(board[i][j] == 1)
		{
			if(board[i][j+1] == 0  &&  board[i+1][j] == 0  &&  board[i+1][j+1] == 0 ){
				return 1;
			}
		}
			
		return 0;
	}
}
