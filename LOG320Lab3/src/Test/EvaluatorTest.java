package Test;

import Game.Evaluator;
import junit.framework.TestCase;

public class EvaluatorTest extends TestCase
{
	// ------------------------
	// TEST FOR VALIDATE QUAD D
	// ------------------------
	
	public void testValidateQuadd001(){
		Evaluator evaluator = new Evaluator();
		
		int[][] board = {
				{0,0},
				{0,0}
		};
		assertEquals(0, evaluator.validateQuadd(board, 0, 0, 1));
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
}
