import java.util.Scanner;
import java.util.Random;

public class Game
{
	boolean bPlayerOption = false; // false: human vs human | true: human vs cpu
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
		PlayerOption();

		LockCells();

		// update
		int turn = 1; // first turn: player (1)
		for (int i = 0; i < 13; i++)
		{
			DrawBoard();
			DrawInput(turn);

			if (bPlayerOption) CPUTurn(turn);
			else UserInput(turn);

			CheckWin(turn);
			if (bWin)
			{
				DrawBoard();
				DrawWin(turn);
				break;
			}

			if (turn == 1) turn = 2;
			else turn = 1;
		}

		if (CheckRetry())
		{
			ReloadData();
			Start();
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
		if (bPlayerOption) System.out.println("+ === CPU WON! === +");
		else System.out.println("+ === PLAYER " + playerNum + " WON! === +");
	}

	// --------------------------

	private void PlayerOption()
	{
		System.out.println("1. Human vs Human");
		System.out.println("2. Human vs CPU");
		System.out.print("Choose Option: [1/2] ");

		int option = scanner.nextInt();
		if (option == 1) bPlayerOption = false;
		else if (option == 2) bPlayerOption = true;

		System.out.println();
	}

	private void ReloadData()
	{
		for (int r = 0; r < 4; r++)
		{
			for (int c = 0; c < 4; c++)
			{
				board[r][c] = 0;
			}
		}

		bWin = false;
	}

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

	private void CPUTurn(int playerNum)
	{
		int cell = rand.nextInt(16);

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

	private boolean CheckRetry()
	{
		System.out.print("Do you want to play again? [Y/n] ");
		
		boolean bRetry = true;
		
		char chInput = scanner.next().charAt(0);
		if (chInput == 'n' || chInput == 'N') bRetry = false;
		
		return bRetry;
	}
}
