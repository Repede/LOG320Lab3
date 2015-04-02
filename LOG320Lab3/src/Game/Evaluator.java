package Game;

import java.awt.Point;
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
	public static Map<Integer,MinMaxNode> evaluateBestMoves(List<String> validPositions, Board rootBoard, int color)
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
			
			try
			{
				node.setValue(calculateBoardWeight(referenceBoard.getBoard(), color));
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
	public static void createParentsChildren(List<String> validPositions, MinMaxNode rootBoard, int color)
	{
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
			
			try
			{
				node.setValue(calculateBoardWeight(referenceBoard.getBoard(), color));
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			node.setParent(rootBoard);
			rootBoard.getChildren().put(i, node);
			//On recr�� le board
			referenceBoard = new Board(rootBoard.getBoard());
		}
	}
	

	
	private static float calculateBoardWeight(int board[][], int color) throws Exception
	{
		// --------------- NORMAL EVALUATOR --------------- 
		// Utilise l'approche par centre de masse, basé sur https://pure.uvt.nl/portal/files/1216600/quad_ICCA_newsletter_vol_24_no_1.pdf
		
		// First, the centre of mass of the pieces on the board is computed for each side.
		Point centerOfMassWhite = retrieveCenterOfMass(board, 2);
		Point centerOfMassBlack = retrieveCenterOfMass(board, 4);
		
		// Second, we compute for each piece its distance to the centre of mass. 
		//	- The distance is measured as the minimal number of squares the piece is removed from the centre of mass. 
		//  - These distances are summed together, called the sum-of-distances. 
		List<Float> distancesWithCenterWhite = retrieveSumOfDistances(board, 2, centerOfMassWhite);
		List<Float> distancesWithCenterBlack = retrieveSumOfDistances(board, 4, centerOfMassBlack);
		
		// Third, the sum-of-minimaldistances is calculated. It is defined as the sum of the minimal distances of the pieces from the centre of mass.
		//	- 	This computation is necessary since otherwise boards with a few pieces would be preferred. For instance, if we
		//		have ten pieces, there will be always at least eight pieces at a distance of 1 from the centre of mass, and one
		//		piece at a distance of 2. In this case the total sum of distances is minimal 10. Thus, the sum-of-minimaldistances
		//		is subtracted from the sum-of-distances. 
		List<Float> distancesWhite = retrieveCalculateDistance(distancesWithCenterWhite);
		List<Float> distancesBlack = retrieveCalculateDistance(distancesWithCenterBlack);
		
		// Fourth, the average distance towards the centre of mass is calculated 
		Float averageDistanceWhite = calculateAverageDistance(distancesWhite);
		Float averageDistanceBlack = calculateAverageDistance(distancesBlack);
		
		// Fifth, the inverse of the average distance is defined as the concentration.
		Float concentrationWhite = 1/averageDistanceWhite;
		Float cencentrationBlack = 1/averageDistanceBlack;
		
		
		// --------------- QUAD EVALUATOR --------------- 
		Float quadEvaluatorValue = retrieveBoardEulerQuad(board, color);
		
		
		// Valeur temporaire
		return concentrationWhite + quadEvaluatorValue;
	}
	
	
	private static Float calculateAverageDistance(List<Float> distancesWhite)
	{
		Float averageDistance = (float) 0;
		for(Float distance : distancesWhite){
			averageDistance += distance;
		}
		averageDistance = averageDistance / distancesWhite.size();
	
		return averageDistance;
	}

	private static List<Float> retrieveCalculateDistance(List<Float> distancesWithCenter)
	{
		// Found the minimal value
		Float minimalValue = Float.MAX_VALUE;
		for(Float point : distancesWithCenter){
			if(point < minimalValue){
				minimalValue = point;
			}				
		}
		
		// Remove all the value not equal to the minimal value
		int index = 0;
		for(Float point : distancesWithCenter){
			if(point != minimalValue){
				distancesWithCenter.remove(index);
			}
			index ++;
		}
		
		return distancesWithCenter;
	}

	private static List<Float> retrieveSumOfDistances(int[][] board, int color, Point center)
	{
		List<Float> distancesWithCenter = new ArrayList<Float>();
		
		for(int i = 0 ; i < 7 ; i++)
		{
			for(int j = 0 ; j < 7 ; j++)
			{
				if(board[i][j] == color){
					float distance = (float) Math.abs(center.x - j) + Math.abs(center.y - i) ;
					distancesWithCenter.add(distance);
				}
			}
		}
		
		return distancesWithCenter;
	}

	private static Point retrieveCenterOfMass(int[][] board, int color) throws Exception
	{
		// Basé sur http://stackoverflow.com/questions/18591964/how-to-calculate-centroid-of-an-arraylist-of-points

		Point center = new Point();
		int pieceNumber = 0;
		int centroidI = 0;
		int centroidJ = 0;
		
		for(int i = 0 ; i < 7 ; i++)
		{
			for(int j = 0 ; j < 7 ; j++)
			{
				if(board[i][j] == color){
					centroidI += i;
					centroidJ += j;
				}
			}			
		}
		
		if(pieceNumber == 0){
			throw new Exception("Il n'y a plus de pion !!");
		}
		
		center.x = centroidJ / pieceNumber;
		center.y = centroidI / pieceNumber;
		
		return center;
	}

	private static float retrieveBoardEulerQuad(int board[][], int color){
		// basé sur http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.4.3549&rep=rep1&type=pdf
		/*
		 *  Quelque questions:
		 *  1: Est-ce qu'un Quad est valide lorsqu'il y a un pion adverse dans son square?
		 *  	- Pour l'instant, un quad est valide lorqu'un square possède seulement la même couleur.
		 */
		
		float numberQuad1 = 0;
		float numberQuad3 = 0;
		float numberQuadd = 0;
		
		for(int i = 0 ; i < 7 ; i++)
		{
			for(int j = 0 ; j < 7 ; j++)
			{
				// calcul de Q1
				numberQuad1 += validateQuad1(board, i, j, color);
				// calcul de Q3
				numberQuad3 += validateQuad3(board, i, j, color);
				// calcul de Qd
				numberQuadd += validateQuadd(board, i, j, color);
			}
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
	 * @param color
	 * @return
	 */
	public static int validateQuadd(int[][] board, int i, int j, int color)
	{
		// premier cas
		if(board[i][j] == color)
		{
			if(board[i][j+1] == 0  &&  board[i+1][j] == 0  &&  board[i+1][j+1] == color ){
				return 1;
			}
		}
		// deuxieme cas
		else if(board[i][j] == 0)
		{
			if(board[i][j+1] == color  &&  board[i+1][j] == color  &&  board[i+1][j+1] == 0 ){
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
	 * @param color
	 * @return
	 */
	public static int validateQuad3(int[][] board, int i, int j, int color)
	{
		
		// bas gauche vide
		if(board[i][j] == color && board[i][j+1] == color  &&  board[i+1][j] == 0  &&  board[i+1][j+1] == color ){
			return 1;
		}
		// bas droite vide
		if(board[i][j] == color && board[i][j+1] == color  &&  board[i+1][j] == color  &&  board[i+1][j+1] == 0 ){
			return 1;
		}
		// haut gauche vide
		if(board[i][j] == 0 && board[i][j+1] == color  &&  board[i+1][j] == color  &&  board[i+1][j+1] == color ){
			return 1;
		}
		// haut droite vide
		if(board[i][j] == color && board[i][j+1] == 0  &&  board[i+1][j] == color  &&  board[i+1][j+1] == color ){
			return 1;
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
	 * @param color
	 * @return
	 */
	public static int validateQuad1(int[][] board, int i, int j, int color)
	{
		// haut gauche remplis
		if(board[i][j] == color && board[i][j+1] == 0  &&  board[i+1][j] == 0  &&  board[i+1][j+1] == 0 ){
			return 1;
		}
		// haut droite remplis
		if(board[i][j] == 0 && board[i][j+1] == color  &&  board[i+1][j] == 0  &&  board[i+1][j+1] == 0 ){
			return 1;
		}
		// bas gauche remplis
		if(board[i][j] == 0 && board[i][j+1] == 0  &&  board[i+1][j] == color  &&  board[i+1][j+1] == 0 ){
			return 1;
		}
		// bas droite remplis
		if(board[i][j] == 0 && board[i][j+1] == 0  &&  board[i+1][j] == 0  &&  board[i+1][j+1] == color ){
			return 1;
		}
			
		return 0;
	}
}
