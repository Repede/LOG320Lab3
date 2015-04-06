package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

public class AIMechanics
{
	//On cherche le meilleur coup possible a jouer considerant le board actuel et quel couleur on joue
	public String getBestMove(Board currentBoard, int myColor)
	{
		//On garde en memoire le temps du lancement de la recherche du meilleur mouvement.
		long initTime = System.currentTimeMillis();
		
		//Notre meilleur coup possible est un node parmi l'arbre
		MinMaxNode bestMove = null;
		
		float maxAlphaBeta =- 100F;
		AlphaBeta ab = new AlphaBeta();
		
		//On initialise le node parent on lui met en mémoire l'etat du board actuel
		MinMaxNode rootBoard = new MinMaxNode();
		Board copyOfCurrentBoard = new Board(currentBoard);
		rootBoard.setBoard(copyOfCurrentBoard);
		
		GameRules gr = new GameRules();
		//On genere les meilleur coups possible a jouer
		List<String> moves = gr.generateMoves(copyOfCurrentBoard.getBoard(), myColor);
		
		//On cree les enfants de la racine
		Evaluator.createParentsChildren(moves, rootBoard, myColor, initTime);
		
		//Pour chaque enfant de la racine, on lance l'alpha beta qui trouvera le meilleur coup a jouer
		for(Entry<Integer,MinMaxNode> child : rootBoard.getChildren().entrySet()) {
			float tempAlphaBeta = ab.alphabeta(child.getValue(), -100, 100, true);
			
			if(tempAlphaBeta > maxAlphaBeta) {
				bestMove = child.getValue();
			}
		}

		//On retourne l'information du node ayant le meilleur coup a jouer
		return bestMove.getMove();
	}
}
