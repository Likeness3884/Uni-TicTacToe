import java.util.Scanner;
import java.util.Random;

public class Game
{
	private int[][] board = {
		{ 0, 0, 0, 0 },
		{ 0, 0, 0, 0 },
		{ 0, 0, 0, 0 },
		{ 0, 0, 0, 0 },
	};
	private Random rand = new Random();
	private Scanner scanner = new Scanner(System.in);
	private boolean bWin = false;

	// ==============================

	public void Start()
	{
		// start
		LockCells();

		// update
		int turn = 1; // first turn: player (1)
		for (int i = 0; i < 13; i++)
		{
			DrawBoard();
			DrawInput(turn);

			UserInput(turn);

			CheckWin(turn);
			if (bWin)
			{
				DrawWin(turn);
				return;
			}

			if (turn == 1) turn = 2;
			else turn = 1;
		}
	}

	// ==============================

	private void DrawBoard()
	{
		int cell;
		char chCell;
		for (int i = 0; i < 4; i++) {
			System.out.println("-----------------");
			for (int j = 0; j < 4; j++)
			{
				cell = board[i][j];
				switch (cell)
				{
					case 1:
						chCell = 'X';
						break;
					case 2:
						chCell = 'O';
						break;
					case -1:
						chCell = '#';
						break;
					default:
						chCell = ' ';
						break;
				}

				System.out.print("| " + chCell + " ");
			}
			System.out.println("|");
		}
		System.out.println("-----------------");
		System.out.println();
	}

	private void DrawInput(int playerNum)
	{
		System.out.print("-- PLAYER " + playerNum + " --> ");
	}

	private void DrawWin(int playerNum)
	{
		System.out.println("+ == PLAYER " + playerNum + " WON! === +");
	}

	// --------------------------

	private void LockCells()
	{
		int randNumber;
		int row, col;
		for (int i = 0; i < 3; i++)
		{
			randNumber = rand.nextInt(16);
			row = randNumber / 4;
			col = randNumber % 4;
			
			board[row][col] = -1;
		}
	}
	
	private void UserInput(int playerNum)
	{
		int cell = scanner.nextInt();

		int row = cell / 4;
		int col = cell % 4;
		
		board[row][col] = playerNum;
	}

	private boolean CheckWin(int playerNum)
	{
		// Check Horizontal
		for (int r = 0; r < 4; r++)
		{
			for (int i = 0; i < 2; i++)
			{
				bWin = true;
				for (int j = i; j < 3 + i; j++)
				{
					if (board[r][j] != playerNum)
					{
						bWin = false;
						break;
					}
				}
				if (bWin) return true;
			}
		}

		// Check Vertical
		for (int c = 0; c < 4; c++)
		{
			for (int i = 0; i < 2; i++)
			{
				bWin = true;
				for (int j = i; j < 3 + i; j++)
				{
					if (board[j][c] != playerNum)
					{
						bWin = false;
						break;
					}
				}
				if (bWin) return true;
			}
		}

		// TODO: Check Diagonal
		
		return bWin;
	}
}
