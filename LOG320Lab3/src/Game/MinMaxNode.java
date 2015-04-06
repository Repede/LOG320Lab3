package Game;

import java.util.HashMap;
import java.util.Map;

public class MinMaxNode
{
	private Board board;
	private String move;
	private float value;
	private Map<Integer,MinMaxNode> children = new HashMap<Integer,MinMaxNode>();
	private MinMaxNode parent;
	private MinMaxNode previousNode;
	private MinMaxNode nextNode;
	
	public Board getBoard()
	{
		return board;
	}
	public void setBoard(Board board)
	{
		this.board = board;
	}
	
	public String getMove()
	{
		return move;
	}
	public void setMove(String move)
	{
		this.move = move;
	}
	
	public float getValue()
	{
		return value;
	}
	public void setValue(float value)
	{
		this.value = value;
	}
	
	public Map<Integer,MinMaxNode> getChildren()
	{
		return children;
	}
	public void setChildren(Map<Integer,MinMaxNode> children)
	{
		this.children = children;
	}
	
	public MinMaxNode getParent()
	{
		return parent;
	}
	public void setParent(MinMaxNode parent)
	{
		this.parent = parent;
	}
	
	public MinMaxNode getNextNode()
	{
		return nextNode;
	}
	
	public void setNextNode(MinMaxNode nextNode)
	{
		this.nextNode = nextNode;
	}
	
	public MinMaxNode getPreviousNode()
	{
		return previousNode;
	}
	
	public void setPreviousNode(MinMaxNode previousNode)
	{
		this.previousNode = previousNode;
	}
}
