package Test;

import Game.AIMechanics;
import Game.Board;
import junit.framework.TestCase;

public class AIMechanicsTest extends TestCase
{
	public void testGetBestMove()
	{
		AIMechanics ai = new AIMechanics();
		int[][] board = {
				{0,2,2,2,2,2,2,0},
				{0,0,0,0,0,0,0,0},
				{4,0,4,0,0,0,0,0},
				{4,0,0,0,0,0,0,0},
				{0,4,0,0,0,0,0,0},
				{4,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,2,2,2,2,2,2,0}
		};
		Board gameBoard = new Board();
		gameBoard.setBoard(board);
		String move = ai.getBestMove(gameBoard, 4);
		System.out.println(move);
	}
}
