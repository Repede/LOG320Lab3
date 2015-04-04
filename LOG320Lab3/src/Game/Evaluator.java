package Game;

import java.awt.Point;
import java.util.Map.Entry;
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
	public static void createParentsChildren(List<String> validPositions, MinMaxNode rootBoard, int color, long time)
	{
		//On garde une copie du rootBoard pour pouvoir �valuer plusieurs moves du root
		GameRules gr = new GameRules();
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
			if(System.currentTimeMillis()-time>4000)
			  return;
		}
		for(Entry<Integer,MinMaxNode> child : rootBoard.getChildren().entrySet())
		{			
			int newColor=(color==GameRules.WHITE_PAWN)?GameRules.BLACK_PAWN:GameRules.WHITE_PAWN;
			List<String> moves = gr.generateMoves(child.getValue().getBoard().getBoard(), newColor);	
			createParentsChildren(moves,child.getValue(),color,time);
		}
	}
	

	
	private static float calculateBoardWeight(int board[][], int color) throws Exception
	{
		// --------------- NORMAL EVALUATOR --------------- 
		// Utilise l'approche par centre de masse, basé sur https://pure.uvt.nl/portal/files/1216600/quad_ICCA_newsletter_vol_24_no_1.pdf
	/*	
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
		Float cencentrationBlack = 1/averageDistanceBlack;*/
		
/////////////////////////////////////////////////////////For each Color////////////////////////////////////////////////		
		Point centerOfMassWhite = retrieveCenterOfMass(board,color);
		//Point centerOfMassBlack = retrieveCenterOfMass(board, 4);
		
		// Second, we compute for each piece its distance to the centre of mass. 
		//	- The distance is measured as the minimal number of squares the piece is removed from the centre of mass. 
		//  - These distances are summed together, called the sum-of-distances. 
		List<Float> distancesWithCenterWhite = retrieveSumOfDistances(board, color, centerOfMassWhite);
		//List<Float> distancesWithCenterBlack = retrieveSumOfDistances(board, 4, centerOfMassBlack);
		
		// Third, the sum-of-minimaldistances is calculated. It is defined as the sum of the minimal distances of the pieces from the centre of mass.
		//	- 	This computation is necessary since otherwise boards with a few pieces would be preferred. For instance, if we
		//		have ten pieces, there will be always at least eight pieces at a distance of 1 from the centre of mass, and one
		//		piece at a distance of 2. In this case the total sum of distances is minimal 10. Thus, the sum-of-minimaldistances
		//		is subtracted from the sum-of-distances. 
		List<Float> distancesWhite = retrieveCalculateDistance(distancesWithCenterWhite);
	//	List<Float> distancesBlack = retrieveCalculateDistance(distancesWithCenterBlack);
		
		// Fourth, the average distance towards the centre of mass is calculated 
		Float averageDistanceWhite = calculateAverageDistance(distancesWhite);
		//Float averageDistanceBlack = calculateAverageDistance(distancesBlack);
		
		// Fifth, the inverse of the average distance is defined as the concentration.
		Float concentrationWhite = 1/averageDistanceWhite;
	//	Float cencentrationBlack = 1/averageDistanceBlack;
		
		
		// --------------- QUAD EVALUATOR --------------- 
		Float quadEvaluatorValue = retrieveBoardEulerQuad(board, color);
		
		
		// #TODO: Est-ce que l'on privilégie les boards dont les pieces sont plus proche du centre de masse avec un nombre de quad élevés?
		
		return concentrationWhite + quadEvaluatorValue;
	}
	
	
	private static Float calculateAverageDistance(List<Float> distancesWhite)
	{
		Float averageDistance =  0F; 
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
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board.length; j++)
				if(board[i][j]==color)
					pieceNumber++;
		
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

	public static float retrieveBoardEulerQuad(int board[][], int color){
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
				boolean indexIOdd = (i & 1) == 0;
				boolean indexJOdd = (j & 1) == 0;
				boolean pastIsNotQuad3AndD = (i==0 || j==0) || ((i != 0) && (j != 0) && validateQuad3(board, i-1, j-1, color) == 0);
				boolean indexIJAresOdd = ( (indexIOdd && indexJOdd) || (i == 0 && j == 0));
				
				// calcul de Q3
				if(validateQuad3(board, i, j, color) == 1 ){
					numberQuad3 += 1;
				}
				// calcul de Qd
				else if (validateQuadd(board, i, j, color) == 1){  //&&  (indexIOdd && indexJOdd)
					numberQuadd += 1;
				}
				// calcul de Q1
				else if( pastIsNotQuad3AndD && indexIJAresOdd)
				{
					// FUCK THIS SHIT
					// si ligne du bas validaate le fucking quad1
					if((i == 6 || i ==0 ) && validateQuad1(board, i, j, color) ==1){
						numberQuad1 +=1 ;
					}
					// FUCK THIS SHIT
					// sinon vérifie les quad extérieur ...
					else if(i>=1 && i<=5 && j>=1 && j<=5){
						// Aucun pion ne doit appartenir à un quad3 ou un quadD
						// FUCK THIS SHIT
						if(validateQuad3(board, i+1, j, color) ==0
								&& validateQuad3(board, i+1, j+1, color) ==0
								&& validateQuad3(board, i, j+1, color) ==0
								&& validateQuad3(board, i+1, j-1, color) ==0
								&& validateQuadd(board, i+1, j, color) ==0
								&& validateQuadd(board, i+1, j+1, color) ==0
								&& validateQuad1(board, i, j, color) == 1){
							numberQuad1 += 1;
						}
					}
				}
			}
		}
		
		System.out.println("Quad 1: " + numberQuad1 + "  Quad 3: " + numberQuad3 + "  Quad d: " + numberQuadd);
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
		// |1|0|
		// |0|1|
		if(board[i][j] == color && board[i][j+1] == 0  &&  board[i+1][j] == 0  &&  board[i+1][j+1] == color)
		{
			return 1;
		}
		
		// |0|1|
		// |1|0|
		else if(board[i][j] == 0 && board[i][j+1] == color  &&  board[i+1][j] == color  &&  board[i+1][j+1] == 0 )
		{
			return 1;
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
