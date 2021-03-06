package Test;

import Game.Evaluator;
import junit.framework.TestCase;

public class EvaluatorTest extends TestCase
{
	// ------------------------
	// TEST FOR RETRIEVE THE EULER QUAD VALUE
	// ------------------------
	
	// Seulement 1 Q1
	public void testRetrieveBoardEulerQuad001(){
		int[][] board = {
				{0,0,0,0,0,0,0,2},
				{0,0,2,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{4,0,0,0,0,0,0,0}
		};
		
		assertEquals((float)0.50, Evaluator.retrieveBoardEulerQuad(board, 2));		
	}
	
	public void testRetrieveBoardEulerQuad002(){
		int[][] board = {
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,2,0},
				{0,2,2,0,0,2,0,0},
				{0,0,2,0,2,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,2,0,0,0,0},
				{4,0,0,0,0,0,0,0}
		};
		
		assertEquals((float)-0.75, Evaluator.retrieveBoardEulerQuad(board, 2));		
	}
	
	public void testRetrieveBoardEulerQuad003(){
		int[][] board = {
				{0,0,0,0,0,0,0,0},
				{0,2,2,0,0,0,0,0},
				{0,0,2,0,0,0,2,0},
				{0,2,2,0,0,2,0,0},
				{0,0,2,0,2,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,2,0,0,0,0},
				{4,0,0,0,0,0,0,0}
		};
		
		assertEquals((float)-0.75, Evaluator.retrieveBoardEulerQuad(board, 2));		
	}
	
	// ------------------------
	// TEST FOR VALIDATE QUAD D
	// ------------------------
	
	public void testValidateQuadd001(){		
		int[][] board = {
				{0,0},
				{0,0}
		};
		assertEquals(0, Evaluator.validateQuadd(board, 0, 0, 1));
	}
	
	public void testValidateQuadd002(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{0,1},
				{0,0}
		};
		assertEquals(0, evaluator.validateQuadd(board, 0, 0, 1));
	}
	
	public void testValidateQuadd003(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{0,0},
				{0,1}
		};
		assertEquals(0, evaluator.validateQuadd(board, 0, 0, 1));
	}
	
	public void testValidateQuadd004(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{1,0},
				{0,1}
		};
		assertEquals(1, evaluator.validateQuadd(board, 0, 0, 1));
	}
	
	public void testValidateQuadd005(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{0,1},
				{1,0}
		};
		assertEquals(1, evaluator.validateQuadd(board, 0, 0, 1));
	}
	
	public void testValidateQuadd006(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{0,1},
				{0,1}
		};
		assertEquals(0, evaluator.validateQuadd(board, 0, 0, 1));
	}
	
	
	
	// ------------------------
	// TEST FOR VALIDATE QUAD 3
	// ------------------------
	
	public void testValidateQuad3001(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{0,0},
				{0,0}
		};
		assertEquals(0, evaluator.validateQuad3(board, 0, 0, 1));
	}
	
	public void testValidateQuad3002(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{0,1},
				{0,0}
		};
		assertEquals(0, evaluator.validateQuad3(board, 0, 0, 1));
	}
	
	public void testValidateQuad3003(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{0,0},
				{1,0}
		};
		assertEquals(0, evaluator.validateQuad3(board, 0, 0, 1));
	}
	
	public void testValidateQuad3004(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{0,0},
				{0,1}
		};
		assertEquals(0, evaluator.validateQuad3(board, 0, 0, 1));
	}
	
	public void testValidateQuad3005(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{1,0},
				{1,1}
		};
		assertEquals(1, evaluator.validateQuad3(board, 0, 0, 1));
	}
	
	public void testValidateQuad3006(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{1,1},
				{1,0}
		};
		assertEquals(1, evaluator.validateQuad3(board, 0, 0, 1));
	}
	
	public void testValidateQuad3007(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{1,1},
				{0,1}
		};
		assertEquals(1, evaluator.validateQuad3(board, 0, 0, 1));
	}
	
	public void testValidateQuad3008(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{0,1},
				{1,1}
		};
		assertEquals(1, evaluator.validateQuad3(board, 0, 0, 1));
	}
	
	
	// ------------------------
	// TEST FOR VALIDATE QUAD 1
	// ------------------------
	
	public void testValidateQuad1001(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{0,0},
				{0,0}
		};
		assertEquals(0, evaluator.validateQuad1(board, 0, 0, 1));
	}
	
	public void testValidateQuad1002(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{1,0},
				{0,0}
		};
		assertEquals(1, evaluator.validateQuad1(board, 0, 0, 1));
	}
	
	public void testValidateQuad1003(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{0,1},
				{0,0}
		};
		assertEquals(1, evaluator.validateQuad1(board, 0, 0, 1));
	}
	
	public void testValidateQuad1004(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{0,0},
				{1,0}
		};
		assertEquals(1, evaluator.validateQuad1(board, 0, 0, 1));
	}
	
	public void testValidateQuad1005(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{0,0},
				{0,1}
		};
		assertEquals(1, evaluator.validateQuad1(board, 0, 0, 1));
	}
	
	public void testValidateQuad1006(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{1,0},
				{0,1}
		};
		assertEquals(0, evaluator.validateQuad1(board, 0, 0, 1));
	}
	
	public void testValidateQuad1007(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{1,1},
				{1,1}
		};
		assertEquals(0, evaluator.validateQuad1(board, 0, 0, 1));
	}
	public void testWinningBoard(){
		int[][] board = {
				{0,0,0,0,0,0,0,0},
				{0,2,2,0,0,0,0,0},
				{0,0,0,2,0,0,0,0},
				{0,0,0,0,2,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{4,0,0,0,0,0,0,0}
		};
		
		assertEquals(true, Evaluator.isWinningBoard(board,2));		
	}
	public void testWinningBoardFail(){
		/*int[][] board = {
				{0,0,0,0,0,0,0,0},
				{0,2,2,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,2,0,0,0},
				{0,0,0,0,0,2,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{4,0,0,0,0,0,0,0}
		};*/
		
		int[][] board = {
				{0,0,0,0,0,0,0,0},
				{2,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,2},
				{2,0,0,4,0,0,0,2},
				{2,0,0,0,0,0,0,2},
				{0,0,0,0,0,0,0,0}
		};
		
		assertEquals(false, Evaluator.isWinningBoard(board,2));		
	}
}
