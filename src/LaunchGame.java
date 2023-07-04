import java.util.Random;
import java.util.Scanner;

class TicTacToe
{
	static char[][] board;
	
	//creating a constructor
	public TicTacToe() 
	{
		board = new char[3][3];
		initBoard();
	}
	
	//initialize the board
	void initBoard() 
	{
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++) 
			{
				board[i][j] = ' ';
			}
		}
	}
	
	//displaying the board
	static void dispBoard()
	{
		System.out.println("-------------");
		
		for(int i = 0; i < board.length; i++)
		{
			System.out.print("| ");
			for(int j = 0; j < board[i].length; j++) 
			{
				System.out.print( board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
			
		}
		
	}
	
	//placing the mark either 'x' or 'o' on board
	static void placeMark(int row, int col, char mark)

	{
		if( row >= 0 && row <= 2 && col >= 0 && col <=2 )
		{
			board[row][col] = mark;
		}
		else 
		{
			System.out.println("Invalid position");
	    }
	
	
      }
	
	//declaring win if its match at column wise
	static boolean checkColWin() 
	{
		for(int j = 0; j <= 2; j++) 
		{
			if( board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j])
			{
				return true;
			}
		}
		return false;
	}
	
	//declaring win if its match at row wise
	static boolean checkRowWin() 
	{
		for(int i = 0; i <= 2; i++) 
		{
			
			if( board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
			{
				return true;
			}
		}
		return false;
	}
	

    //declaring win if its match at diagonal wise
	static boolean checkDiagonalWin() 
	{
		
			if(( board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2] )
					|| ( board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]))
				{
					return true;
				}
			
			return false;
		}
	
	//Creating the function to check whether game is a draw
	static boolean checkDraw()
	{
		for(int i = 0 ; i <= 2 ; i++ )
		{
			for(int j = 0 ; j <= 2 ; j++ )
			{
				if(board[i][j] == ' ')
				{
					return false;
				}
			}
		}
		return true;
	}
}	


//Creating a class player
abstract class Player {
	String name;
	char mark;
	
	abstract void makeMove();
	
	
	//Function to check whether move is valid
	boolean isValidMove( int row, int col) {
			if( row >= 0 && row <=2 && col >= 0 && col <=2)
			{
				if( TicTacToe.board[row][col] == ' ') 
				{
					return true;
				}
			}
			return false;
		}
	
}

//Creating class having a Human Player 
//And inherits variables and method from parent class "Player"
class HumanPlayer extends Player {
	
//	String name;
//	char mark;

	public HumanPlayer(String name, char mark)
	{
		this.name = name; 
		this.mark = mark;
	}
	
	
	//taking input from user
	void makeMove() {
		Scanner scan = new Scanner(System.in);
		int row;
		int col;
		do {
			System.out.println("Enter the row and coloumn");
			row = scan.nextInt();
			col = scan.nextInt();
		} while(!isValidMove(row, col));
		
		TicTacToe.placeMark(row, col, mark);
				
		
	}
	
//	//Function to check whether move is valid
//	boolean isValidMove( int row, int col) {
//		if( row >= 0 && row <=2 && col >= 0 && col <=2)
//		{
//			if( TicTacToe.board[row][col] == ' ') 
//			{
//				return true;
//			}
//		}
//		return false;
	
}



//Creating class having a AI Player
//And inherits variables and method from parent class "Player"
class AIPlayer extends Player {
//	String name;
//	char mark;
	
	
	public AIPlayer(String name, char mark)
	{
		this.name = name; 
		this.mark = mark;
	}
	
	
	//taking input from AI using random in-built class of java
	void makeMove() {
		Scanner scan = new Scanner(System.in);
		int row;
		int col;
		do {
			Random r = new Random();
			row = r.nextInt(3);     //generates inputs 0, 1, & 2
			col = r.nextInt(3);
		} while(!isValidMove(row, col));
		
		TicTacToe.placeMark(row, col, mark);
				
		
	}
	
//	//Function to check whether move is valid
//	boolean isValidMove( int row, int col) {
//		if( row >= 0 && row <=2 && col >= 0 && col <=2)
//		{
//			if( TicTacToe.board[row][col] == ' ') 
//			{
//				return true;
//			}
//		}
//		return false;
//	}
	
	
}



//main class if AI is a Player
public class LaunchGame {
public static void main(String[] args) {
	TicTacToe t = new TicTacToe();
	
	HumanPlayer p1 = new HumanPlayer("Bob", 'X');
	AIPlayer p2 = new AIPlayer("YourAI", 'O');
	
	
	Player cp;
	cp = p1;
	
	while(true)
	{
		System.out.println(cp.name + " turn");
		cp.makeMove();
		TicTacToe.dispBoard();
		
		if(TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagonalWin())
		{
			System.out.println(cp.name + " has won");
			break;
		}
		else if(TicTacToe.checkDraw())
		{
			System.out.println("Game is draw");
			break;
		}
		else 
		{
			if(cp == p1)
			{
				cp = p2;
			}
			else
				cp = p1;
		}
	}

	
}
}






////main class if Human is a Player
//public class LaunchGame {
//	public static void main(String[] args) {
//		TicTacToe t = new TicTacToe();
//		
//		HumanPlayer p1 = new HumanPlayer("Bob", 'X');
//		HumanPlayer p2 = new HumanPlayer("Solo", 'O');
//		
//		HumanPlayer cp;
//		cp = p1;
//		
//		while(true)
//		{
//			System.out.println(cp.name + " turn");
//			cp.makeMove();
//			TicTacToe.dispBoard();
//			
//			if(TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagonalWin())
//			{
//				System.out.println(cp.name + " has won");
//				break;
//			}
//			else 
//			{
//				if(cp == p1)
//				{
//					cp = p2;
//				}
//				else
//					cp = p1;
//			}
//		}
//	
//		
//	}
//
//}
