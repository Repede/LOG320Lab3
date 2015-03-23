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
		//On garde une copie du rootBoard pour pouvoir évaluer plusieurs moves du root
		Board referenceBoard = new Board(rootBoard);
		for(int i = 0 ; i < validPositions.size() ; ++i)
		{
			//On Prépare une racine
			MinMaxNode node = new MinMaxNode();
			//2 Transformer les validPositions en board (on fait le move dans referenceBoard selon la couleur)
			referenceBoard.updateBoard(referenceBoard, validPositions.get(i));
			//On ajoute le board, le move et le poid du board dans la racine
			node.setBoard(new Board(referenceBoard));
			node.setMove(validPositions.get(i));
			node.setValue(this.calculateBoardWeight(referenceBoard));
			//On met la racine dans la liste
			minMaxDictionary.put(i,node);
			//On recréé le board
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
		//On garde une copie du rootBoard pour pouvoir évaluer plusieurs moves du root
		Board referenceBoard = new Board(rootBoard.getBoard());
		for(int i = 0 ; i < validPositions.size() ; ++i)
		{
			//On Prépare une racine
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
			//On recréé le board
			referenceBoard = new Board(rootBoard.getBoard());
		}
	}
	
	private int calculateBoardWeight(Board board)
	{
		return 0;
	}
}
