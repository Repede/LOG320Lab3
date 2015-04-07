package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AIMechanics
{
	public static int TIME = 3000;
	public static int MY_COLOR;
	//On cherche le meilleur coup possible a jouer considerant le board actuel et quel couleur on joue
	public String getBestMove(Board currentBoard, int myColor)
	{
		//On garde en memoire le temps du lancement de la recherche du meilleur mouvement.
		long initTime = System.currentTimeMillis();
		
		//Notre meilleur coup possible est un node parmi l'arbre
		//MinMaxNode bestMove = null;
		
		float maxAlphaBeta =- 100F;
		AlphaBeta ab = new AlphaBeta();
		
		//On initialise le node parent on lui met en mémoire l'etat du board actuel
		MinMaxNode rootBoard = new MinMaxNode();
		Board copyOfCurrentBoard = new Board(currentBoard);
		rootBoard.setBoard(copyOfCurrentBoard);
		
		GameRules gr = new GameRules();
		//On genere les meilleur coups possible a jouer
		List<String> moves = gr.generateMoves(copyOfCurrentBoard.getBoard(), myColor);
		AIMechanics.MY_COLOR=myColor;
		//On cree les enfants de la racine
		Evaluator.createParentsChildren(moves, rootBoard, myColor, initTime);
		
		//System.out.println(System.currentTimeMillis() - initTime);
		
		//Pour chaque étage de l'arbre on doit changer la couleur des nodes crées. Les coups de notre couleur sont des choix MAX, les coups de l'adversaire sont des choix MIN
		int newColor = (myColor == GameRules.WHITE_PAWN) ? GameRules.BLACK_PAWN : GameRules.WHITE_PAWN;
		MinMaxNode premierNode = rootBoard.getChildren().get(0);
		
		//Tant que le temps nous le permet, on parcoure en largeur les enfants de la profondeur actuelle et on leur cree des enfants a leur tour
		while (System.currentTimeMillis() - initTime < TIME) {
			MinMaxNode currentNode = premierNode;
			boolean finDeLaProfondeur = false;
			
			while (!finDeLaProfondeur) {	
				
				//On genere tout les coups possible de l'enfant
				List<String> movesNewColor = gr.generateMoves(currentNode.getBoard().getBoard(), newColor);	
				
				//L'nfant en question devient à son tour un parent, on cree ses enfants.
				Evaluator.createParentsChildren(movesNewColor, currentNode, newColor, initTime);
				
				if(currentNode.getNextNode() == null) {
					finDeLaProfondeur = true;
				} else {
					currentNode = currentNode.getNextNode();
				}
				
				if(System.currentTimeMillis()-initTime > TIME) {
					break;
				}
			}
			newColor = (newColor == GameRules.BLACK_PAWN) ? GameRules.WHITE_PAWN : GameRules.BLACK_PAWN;
			premierNode = premierNode.getChildren().get(0);
		}
		
		//Pour chaque enfant de la racine, on lance l'alpha beta qui trouvera le meilleur coup a jouer
		Map<MinMaxNode, Integer> entriesAlphaBeta = new HashMap<MinMaxNode, Integer>();
		//List<Float> listeValeursAlphaBeta = new ArrayList<Float>();
		for(Entry<Integer,MinMaxNode> child : rootBoard.getChildren().entrySet()) {
			
			MinMaxNode tempAlphaBeta = ab.alphabeta(child.getValue(), -100, 100, true);
			
			//listeValeursAlphaBeta.add(tempAlphaBeta.getValue());
			entriesAlphaBeta.put(tempAlphaBeta, child.getKey());
			
			/*if(tempAlphaBeta > maxAlphaBeta) {
				bestMove = child.getValue();
			}*/
		}
		MinMaxNode bestChild = new MinMaxNode();
		bestChild.setValue(Float.NEGATIVE_INFINITY);
		for(MinMaxNode node : entriesAlphaBeta.keySet())
		{
			if(node.getValue() > bestChild.getValue())
			{
				bestChild = node;
			}
		}
		
		//int nodeID = entriesAlphaBeta.get(maxValeurAlphaBeta);
		
		/*int nodeID = 0;
		
		for(Entry<MinMaxNode, Integer> entry : entriesAlphaBeta.entrySet()) {
			if(entry.getKey().getValue() == maxValeurAlphaBeta) {
				bestMove = entry.getKey();
				break;
			}
		}*/
		MinMaxNode parent = new MinMaxNode();
		parent = bestChild.getParent();
		while(parent.getParent() != null)
		{
			bestChild = parent;
			parent = bestChild.getParent();
		}
		
		//bestMove = rootBoard.getChildren().get(nodeID);
		
		/*for(Entry<MinMaxNode, Integer> entry : entriesAlphaBeta.entrySet()) {
			System.out.println(entry.getKey().getValue());
		}*/

		System.out.println("BEST: "+bestChild.getValue());
		//On retourne l'information du node ayant le meilleur coup a jouer
		return bestChild.getMove();
	}
}
