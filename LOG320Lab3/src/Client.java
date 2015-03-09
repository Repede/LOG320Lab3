import java.io.*;
import java.net.*;

class Client
{
	public static void main(String[] args)
	{
		Board gameBoard = new Board();
		Socket MyClient;
		BufferedInputStream input;
		BufferedOutputStream output;
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
						gameBoard.setBoardValue(x, y, Integer.parseInt(boardValues[i]));
						x++;
						if (x == 8)
						{
							x = 0;
							y++;
						}
					}

					System.out.println("Nouvelle partie! Vous jouer blanc, entrez votre premier coup : ");
					gameBoard.displayBoard();
					String move = null;
					move = console.readLine();
					output.write(move.getBytes(), 0, move.length());
					output.flush();
				}
				// Début de la partie en joueur Noir
				if (cmd == '2')
				{
					System.out.println("Nouvelle partie! Vous jouer noir, attendez le coup des blancs");
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
						gameBoard.setBoardValue(x, y, Integer.parseInt(boardValues[i]));
						x++;
						if (x == 8)
						{
							x = 0;
							y++;
						}
					}
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
					System.out.println("Dernier coup : " + s);
					System.out.println("Entrez votre coup : ");
					String move = null;
					move = console.readLine();
					output.write(move.getBytes(), 0, move.length());
					output.flush();

				}
				// Le dernier coup est invalide
				if (cmd == '4')
				{
					System.out
							.println("Coup invalide, entrez un nouveau coup : ");
					String move = null;
					move = console.readLine();
					output.write(move.getBytes(), 0, move.length());
					output.flush();

				}
			}
		} catch (IOException e)
		{
			System.out.println(e);
		}
		
	}
	
}