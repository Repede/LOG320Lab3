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
		
		assertEquals(3, gr.calculatePawnsInRow(board, 0)); 
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
		
		assertEquals(3, gr.calculatePawnsInColumn(board, 0));
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
		
		assertEquals(2, gr.calculatePawnsInDiagonal(board, 7, 0));
	}
	
	public void testCanMoveDiagonal002(){
		GameRules gr = new GameRules();
		
		int[][] board = {
				{0,0,0,0,0,0,0,2},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,2},
				{0,0,0,0,0,0,4,0},
				{4,0,0,0,0,2,0,0}
		};
		
		assertEquals(3, gr.calculatePawnsInDiagonal(board, 7, 5));
	}
	
	public void testCanMoveInverserDiagonal001(){
		GameRules gr = new GameRules();
		
		int[][] board = {
				{4,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,2,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,2,0,0},
				{0,0,0,0,0,0,4,0},
				{0,0,0,0,0,0,0,4}
		};
	
		assertEquals(5, gr.calculatePawnsInReverseDiagonal(board, 0, 0));
	}
	
	public void testCanMoveInverserDiagonal002(){
		GameRules gr = new GameRules();
		
		int[][] board = {
				{0,0,0,0,2,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,4,0},
				{0,0,0,0,0,0,0,2},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0}
		};
	
		assertEquals(3, gr.calculatePawnsInReverseDiagonal(board, 0, 4));
	}
	
	public void testValidateMouvementInRowLeft001(){
		GameRules gr = new GameRules();
		
		int[][] board = {
				{0,2,2,0,0,0,0,0},
				{2,0,2,0,0,0,0,0},
				{0,4,2,0,0,0,0,0},
				{0,2,2,0,0,0,0,2},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0}
		};
		
		assertTrue(gr.validateMouvementInRow(0, 2, 2, board, true) == true);
		assertTrue(gr.validateMouvementInRow(1, 2, 2, board, true) == false);
		assertTrue(gr.validateMouvementInRow(2, 2, 2, board, true) == false);
		assertTrue(gr.validateMouvementInRow(3, 7, 3, board, true) == true);
	}
	
	public void testValidateMouvementInRowRight001(){
		GameRules gr = new GameRules();
		
		int[][] board = {
				{2,2,0,0,0,0,0,0},
				{2,0,2,0,0,0,0,0},
				{4,2,0,0,0,0,0,0},
				{2,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,2,2},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0}
		};
		
		assertTrue(gr.validateMouvementInRow(0,0, 2, board, false) == true);
		assertTrue(gr.validateMouvementInRow(1, 0, 2, board, false) == false);
		assertTrue(gr.validateMouvementInRow(2, 0, 2, board, false) == false);
		assertTrue(gr.validateMouvementInRow(3, 7, 2, board, false) == false);
		assertTrue(gr.validateMouvementInRow(4, 0, 3, board, false) == true);
	}
	
	public void testValidateMouvementInColumnDown001(){
		GameRules gr = new GameRules();
		
		int[][] board = {
				{2,2,4,2,2,0,0,0},
				{2,0,2,0,0,0,0,0},
				{0,2,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,2,0,0,0},
				{0,0,0,2,2,0,0,0}
		};
		
		assertTrue(gr.validateMouvementInColumn(0, 0, 2, board, true) == true);
		assertTrue(gr.validateMouvementInColumn(0, 1, 2, board, true) == false);
		assertTrue(gr.validateMouvementInColumn(0, 2, 2, board, true) == false);
		assertTrue(gr.validateMouvementInColumn(7, 3, 2, board, true) == false);
		assertTrue(gr.validateMouvementInColumn(0, 4, 3, board, true) == true);
	}
	
	public void testValidateMouvementInColumnUp001(){
		GameRules gr = new GameRules();
		
		int[][] board = {
				{0,0,0,2,2,0,0,0},
				{0,0,0,0,2,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,2,0,0,0,0,0,0},
				{2,0,2,0,0,0,0,0},
				{2,2,4,2,2,0,0,0}
		};
		
		assertTrue(gr.validateMouvementInColumn(7, 0, 2, board, false) == true);
		assertTrue(gr.validateMouvementInColumn(7, 1, 2, board, false) == false);
		assertTrue(gr.validateMouvementInColumn(7, 2, 2, board, false) == false);
		assertTrue(gr.validateMouvementInColumn(0, 3, 2, board, false) == false);
		assertTrue(gr.validateMouvementInColumn(7, 4, 3, board, false) == true);
	}
	
	public void testValidateMouvementInDiagonalUp(){
		GameRules gr = new GameRules();
		
		int[][] board = {
				{0,2,2,4,0,0,4,2},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,2,0,2,0,0,0},
				{0,0,4,0,2,0,0,0},
				{2,4,2,4,0,0,0,0}
		};
		
		assertTrue(gr.validateMouvementInDiagonal(0, 7, 3, board, true) == false);
		assertTrue(gr.validateMouvementInDiagonal(7, 1, 2, board, true) == true);
		assertTrue(gr.validateMouvementInDiagonal(7, 2, 2, board, true) == false);
		assertTrue(gr.validateMouvementInDiagonal(7, 3, 2, board, true) == false);
	}
	
	public void testValidateMouvementInDiagonalDown(){
		GameRules gr = new GameRules();
		
		int[][] board = {
				{0,0,0,0,4,2,4,2},
				{0,0,0,2,0,4,0,0},
				{0,0,0,2,0,2,4,0},
				{0,0,0,0,0,0,0,2},
				{0,0,0,2,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{2,0,0,0,0,0,0,0}
		};
		
		assertTrue(gr.validateMouvementInDiagonal(7, 0, 4, board, false) == false);
		assertTrue(gr.validateMouvementInDiagonal(0, 6, 2, board, false) == true);
		assertTrue(gr.validateMouvementInDiagonal(0, 5, 2, board, false) == false);
		assertTrue(gr.validateMouvementInDiagonal(0, 4, 2, board, false) == false);
	}

	public void testValidateMouvementInReverseDiagonalUp(){
		GameRules gr = new GameRules();
		
		int[][] board = {
				{2,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,2,0,2,0,0},
				{0,0,0,2,0,4,0,0},
				{0,0,0,0,4,2,4,2}
		};
		
		assertTrue(gr.validateMouvementInReverseDiagonal(0, 0, 3, board, true) == false);
		assertTrue(gr.validateMouvementInReverseDiagonal(7, 6, 2, board, true) == true);
		assertTrue(gr.validateMouvementInReverseDiagonal(7, 5, 2, board, true) == false);
		assertTrue(gr.validateMouvementInReverseDiagonal(7, 4, 2, board, true) == false);
	}
	
	public void testValidateMouvementInReverseDiagonalDown(){
		GameRules gr = new GameRules();
				
		int[][] board = {
				{2,4,2,4,0,0,0,0},
				{0,0,4,0,2,0,0,0},
				{0,0,2,0,2,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,2}
		};
		
		assertTrue(gr.validateMouvementInReverseDiagonal(7, 7, 3, board, false) == false);
		assertTrue(gr.validateMouvementInReverseDiagonal(0, 1, 2, board, false) == true);
		assertTrue(gr.validateMouvementInReverseDiagonal(0, 2, 2, board, false) == false);
		assertTrue(gr.validateMouvementInReverseDiagonal(0, 3, 2, board, false) == false);
	}
}
