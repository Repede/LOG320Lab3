package Game;

import java.util.List;

public class AIMechanics
{
	public String getBestMove(Board currentBoard, int myColor)
	{
		MinMaxNode rootBoard = new MinMaxNode();
		rootBoard.setBoard(currentBoard);
		
		GameRules gr = new GameRules();
		List<String> moves = gr.generateMoves(currentBoard.getBoard(), myColor);
		//Cr�� les enfants (devrait �tre r�cursif
		Evaluator.createParentsChildren(moves, rootBoard, myColor);
		
		System.out.println(rootBoard.getChildren().get(0).getValue()); 
		
		return rootBoard.getChildren().get(0).getMove();
	}
}
