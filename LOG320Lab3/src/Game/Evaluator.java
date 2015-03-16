package Game;

import java.util.List;
import java.util.Random;

public class Evaluator
{

	/**
	 *  https://i.imgflip.com/iwhub.jpg
	 */
	public String RetrieveBestMove(List<String> validPositions)
	{
		Random ran = new Random();
		int index = ran.nextInt(validPositions.size() -1);
		
		return validPositions.get(index);
	}

}
