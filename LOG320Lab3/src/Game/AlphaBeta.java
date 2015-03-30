package Game;

import java.util.Map.Entry;



public class AlphaBeta
{

	public float alphabeta(MinMaxNode node,float alpha,float beta,boolean Player)
	{
		if(node.getChildren().isEmpty())
		{
		    return node.getValue();
		}
	    if (Player)
	    {
	    	for(Entry<Integer,MinMaxNode> child : node.getChildren().entrySet())
	        {
	        	alpha = Math.max(alpha, alphabeta(child.getValue(), alpha, beta, !Player ));     
	            if (beta <=alpha)
	                break;                           //  (* Beta cut-off *)
	        }	        	
	        return alpha;
	    }
	    else
	    {
	        for (Entry<Integer,MinMaxNode> child: node.getChildren().entrySet())
	        {
	        	beta = Math.min(beta, alphabeta(child.getValue(), alpha, beta, !Player));     
	            if (beta <= alpha)
	                break;                             
	        }
	        return beta;	
	    }
		
	}         
    
}
