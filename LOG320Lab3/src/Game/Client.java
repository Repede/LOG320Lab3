package Game;
import java.io.*;
import java.net.*;
import java.util.List;

class Client
{
	public static void main(String[] args)
	{
		GameRules gameRules = new GameRules();
		AIMechanics aiMech=new AIMechanics();
		Evaluator evaluator = new Evaluator();
		Board gameBoard = new Board();
		Board previousValidBoard = new Board();
		Socket MyClient;
		BufferedInputStream input;
		BufferedOutputStream output;
		int currentColor = 0;
		try
		{
			MyClient = new Socket("localhost", 8888);
			input = new BufferedInputStream(MyClient.getInputStream());
			output = new BufferedOutputStream(MyClient.getOutputStream());
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
			while (true)
			{
				char cmd = 0;

				cmd = (char) input.read();

				// Début de la partie en joueur blanc
				if (cmd == '1')
				{
					currentColor = GameRules.WHITE_PAWN;
					byte[] aBuffer = new byte[1024];

					int size = input.available();
					// System.out.println("size " + size);
					input.read(aBuffer, 0, size);
					String s = new String(aBuffer).trim();
					String[] boardValues;
					boardValues = s.split(" ");
					int x = 0, y = 0;
					for (int i = 0; i < boardValues.length; i++)
					{
						gameBoard.setBoardValue(y, x, Integer.parseInt(boardValues[i]));
						x++;
						if (x == 8)
						{
							x = 0;
							y++;
						}
					}

					gameBoard.displayBoard();
					//previousValidBoard = gameBoard;
					
					//Mettre le break point ici (sur le sysout) pour �tre capable de d�bugger comme il le faut. Faire "step-over" sur le getBestMove
					System.out.print("Nouvelle partie! Vous jouer blanc, entrez votre premier coup : ");				
					
					//List<String> validPositions = gameRules.generateMoves(gameBoard.getBoard()or)
					//gameBoard.boardIndexToLetter(evaluator.evaluateBestMoves, currentColor);
					
					// Le getBestMove change compl�tement le format de notre gameBoard, donc l'affichage fuck apr�s �a.
					// La m�thode createParentChildren fait en sorte que notre referenceBoard est updat� (appel de updateBoard), quand referenceBoard est modifi�, notre gameBoard est modifi� aussi.
					// Donc il essaie de display un board o� le move a d�j� �t� updater...
					// Voir commentaires dans la m�thode createParentChildren.
					String move = aiMech.getBestMove(gameBoard , currentColor);
					String outputToServer = gameBoard.boardIndexToLetter(move);	
					
					gameBoard.updateBoardWithLetters(gameBoard,outputToServer);
					System.out.print("\n");
					gameBoard.displayBoard();
					output.write(outputToServer.getBytes(), 0, outputToServer.length());
					output.flush();
				}
				// Début de la partie en joueur Noir
				if (cmd == '2')
				{
					currentColor = GameRules.BLACK_PAWN;
					System.out.print("Nouvelle partie! Vous jouer noir, attendez le coup des blancs");
					byte[] aBuffer = new byte[1024];

					int size = input.available();
					// System.out.println("size " + size);
					input.read(aBuffer, 0, size);
					String s = new String(aBuffer).trim();
					String[] boardValues;
					boardValues = s.split(" ");
					int x = 0, y = 0;
					for (int i = 0; i < boardValues.length; i++)
					{
						gameBoard.setBoardValue(y, x, Integer.parseInt(boardValues[i]));
						x++;
						if (x == 8)
						{
							x = 0;
							y++;
						}
					}
					//previousValidBoard = gameBoard;
				}

				// Le serveur demande le prochain coup
				// Le message contient aussi le dernier coup jou�.
				if (cmd == '3')
				{
					byte[] aBuffer = new byte[16];

					int size = input.available();
					// System.out.println("size " + size);
					input.read(aBuffer, 0, size);
					String s = new String(aBuffer);
					
					gameBoard.updateBoardWithLetters(gameBoard, s);
					System.out.print("\n");
					gameBoard.displayBoard();
					//previousValidBoard = gameBoard;
					System.out.print("Dernier coup : " + s);
					System.out.print("\nEntrez votre coup : ");
					
					String move = aiMech.getBestMove(gameBoard, currentColor);
					String outputToServer = gameBoard.boardIndexToLetter(move);	
					gameBoard.updateBoardWithLetters(gameBoard, outputToServer);
					System.out.print("\n");
					gameBoard.displayBoard();
					output.write(outputToServer.getBytes(), 0, outputToServer.length());
					output.flush();
				}
				// Le dernier coup est invalide
				if (cmd == '4')
				{
					gameBoard = previousValidBoard;
					System.out.print("Coup invalide, entrez un nouveau coup : ");

					//List<String> validPositions = gameRules.generateMoves(gameBoard.getBoard(), currentColor);
					/*String move = gameBoard.boardIndexToLetter(evaluator.evaluateBestMoves(validPositions));

					System.out.println("Move: " + move);
					System.out.print("\n");
					gameBoard.displayBoard();
					output.write(move.getBytes(), 0, move.length());
					output.flush();*/
					
					String move = aiMech.getBestMove(gameBoard, currentColor);
					String outputToServer = gameBoard.boardIndexToLetter(move);	
					gameBoard.updateBoardWithLetters(gameBoard, outputToServer);
					System.out.print("\n");
					gameBoard.displayBoard();
					output.write(outputToServer.getBytes(), 0, outputToServer.length());
					output.flush();

				}
			}
		} catch (IOException e)
		{
			System.out.println(e);
		}
		
	}
	
}