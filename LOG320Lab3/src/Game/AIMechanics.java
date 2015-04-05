package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

public class AIMechanics
{
	public String getBestMove(Board currentBoard, int myColor)
	{
		long initTime=System.currentTimeMillis();
		MinMaxNode bestMove=null;
		float maxAlphaBeta=-100F;
		AlphaBeta ab=new AlphaBeta();
		MinMaxNode rootBoard = new MinMaxNode();
		rootBoard.setBoard(currentBoard);
		
		GameRules gr = new GameRules();
		List<String> moves = gr.generateMoves(currentBoard.getBoard(), myColor);
		//Créé les enfants (devrait être récursif
		Evaluator.createParentsChildren(moves, rootBoard, myColor,initTime);
		//List<Float> resultAlphaBeta=new ArrayList<Float>();
		for(Entry<Integer,MinMaxNode> child: rootBoard.getChildren().entrySet())
		{
			float tempAphBet=ab.alphabeta(child.getValue(), -100, 100, true);
			if(tempAphBet>maxAlphaBeta)
				bestMove=child.getValue();
			//  resultAlphaBeta.add(ab.alphabeta(child.getValue(), -100, 100, true));
		}
		//for(Float e: resultAlphaBeta)
	//		System.out.println(e);
		//finalIndex=Collections.max(resultAlphaBeta)
		System.out.println( bestMove.getMove());
		return bestMove.getMove();
	}
}
