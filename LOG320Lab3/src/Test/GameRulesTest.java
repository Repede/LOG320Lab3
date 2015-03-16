package Test;
import Game.GameRules;
import junit.framework.TestCase;
 
public class GameRulesTest extends TestCase
{	
	

	public void testCanMoveInline001(){
		GameRules gr = new GameRules();
		
		int[][] board = {
				{4,0,2,0,0,0,0,4}
		};
		
		assertEquals(3, gr.canMoveInline(board, 0)); 
	}
	
	public void testCanMoveInColumn001(){
		GameRules gr = new GameRules();
		
		int[][] board = {
				{4},
				{0},
				{2},
				{0},
				{0},
				{0},
				{0},
				{4}
		};
		
		assertEquals(3, gr.canMoveInColumn(board, 0));
	}
	
	public void testCanMoveDiagonal001(){
		GameRules gr = new GameRules();
		
		int[][] board = {
				{0,0,0,0,0,0,0,2},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{4,0,0,0,0,0,0,0}
		};
		
		assertEquals(2, gr.canMoveDiagonal(board, 7, 0));
	}
	
	//public void testCanMoveInverserDiagonal001(){
	//	GameRules gr = new GameRules();
		
	//	int[][] board = {
	//			{4,0,0,0,0,0,0,0},
	//			{0,0,0,0,0,0,0,0},
	//			{0,0,0,0,0,0,0,0},
	//			{0,0,0,2,0,0,0,0},
	//			{0,0,0,0,0,0,0,0},
	//			{0,0,0,0,0,2,0,0},
	//			{0,0,0,0,0,0,4,0},
	//			{0,0,0,0,0,0,0,4}
	//	};
		
	//	assertEquals(5, gr.canMoveInverserDiagonal(board, 0, 0));
	//}
}
