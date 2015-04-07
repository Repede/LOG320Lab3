package Game;

import java.util.Map.Entry;



public class AlphaBeta
{

	public MinMaxNode alphabeta(MinMaxNode node, float alpha, float beta, boolean Player)
	{
		if(node.getChildren().isEmpty())
		{
		    return node;
		}
	    if (Player)
	    {
	    	MinMaxNode alphaNode = new MinMaxNode();
	    	for(Entry<Integer,MinMaxNode> child : node.getChildren().entrySet())
	        {
	    		alphaNode = child.getValue();
	    		MinMaxNode otherNode = alphabeta(alphaNode, alpha, beta, !Player );
	    		if(alphaNode.getValue() < otherNode.getValue())
	    		{
	    			alphaNode = otherNode;
	    			alpha = alphaNode.getValue();
	    		}
	            if (beta <= alpha)
	                break;                           //  (* Beta cut-off *)
	        }	        	
	        return alphaNode;
	    }
	    else
	    {
	    	MinMaxNode betaNode = new MinMaxNode();
	        for (Entry<Integer,MinMaxNode> child: node.getChildren().entrySet())
	        {
	        	betaNode = child.getValue();
	        	MinMaxNode otherNode = alphabeta(betaNode, alpha, beta, !Player);
	        	if(betaNode.getValue() > otherNode.getValue())
	        	{
	        		betaNode = otherNode;
	        		beta = betaNode.getValue();
	        	}
	            if (beta <= alpha)
	                break;                             
	        }
	        return betaNode;	
	    }
		
	}         
    
}
