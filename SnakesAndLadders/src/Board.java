
/*
----------------------------------------------------------------------------------------------------------------------------------------------------
Kenny Phan - 40164827
February 8th, 2021

Purpose: 
This program simulates a game of Snakes and Ladders with 2-4 players. Players will roll a die to determine the playing order.
The player with the highest die value gets to play first, the player with the second highest gets to play second, and so on.
In case of a tie between players, those players who got a tie will have to re-roll in order to determine their playing order.
Players will start at square 0. They will take turn throwing the die and advancing to their square number.
Throughout the board, there are ladders and snakes. If one lands exactly on the bottom of a ladder, then they get to climb the ladder.
If one lands exactly on the head of a snake, then they slide all the way down the snake. Moreover, if one exceeds the square 100,
then they must move backward with the excessive amount. First one to reach exactly square 100 wins. Good luck and have fun!
----------------------------------------------------------------------------------------------------------------------------------------------------
*/

/**
 * This class implements the game board of Snakes and Ladders.
 * @author Kenny Phan
 */

public class Board {
	
	// Attributes
	private String value;
	private boolean positionTaken;
	
	// Constant values
	private static int NUM_SNAKES = 8;
	private static int NUM_LADDERS = 9;
	
	// Board array variables
	public static int[][] snakes;
	public static int[][] ladders;
	
	// Constructor
	/**
	 * Constructs a board  
	 * @param value The number position in the board.
	 * @param positionTaken True if position is taken, else false.
	 */
	public Board(String value, boolean positionTaken) {
		this.value = value;
		this.positionTaken = positionTaken;
	}
	
	// toString method
	/**
	 * Returns a string representing the number in the board.
	 */
	public String toString() {
		return String.valueOf(value);
	}	
	
	/**
	 * Initializes and sets up the board.
	 * @param board The board array to setup.
	 */
	public static void setUpBoard(Board[][] board) {
		int count1 = 100;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (i % 2 == 0) {
					board[i][j].setValue("" + count1--); 
				} else {
					board[i][j].setValue("" + (++count1));
				}
				board[i][j].setPositionTaken(false);
			}
			count1 -= 10;
		}
		
	}
	
	/**
	 * Displays the board game
	 * @param board the board array to display
	 */
	public static void printBoard(Board[][] board) {
		// Display board
		System.out.println("------------------------------------------------");
		for(int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				int num = 0;
				// Catch the board array when it contains a string like "P1"
				try {
					num = Integer.parseInt(board[i][j].getValue());
				} catch (Exception e) {
					
				}
				// Spacing
				if (num <= 10) {
					System.out.print(" " + board[i][j] + "   ");
				} else if (num >= 91) {
					System.out.print(board[i][j] + "   ");
				} else {
					System.out.print(" " + board[i][j] + "  ");
				}
			}
			System.out.println();
		}
		System.out.println("------------------------------------------------");
	}
	
	/**
	 * Initializes the 2D-Array that contains the location of snakes.
	 */
	public static void setSnakes() {
		snakes = new int[NUM_SNAKES][2];
		
		// snakes[i][0] represents the head of the snake
		// snakes[i][1] represents the tail of the snake
		snakes[0][0] = 16;
		snakes[0][1] = 6;
		
		snakes[1][0] = 48;
		snakes[1][1] = 30;
		
		snakes[2][0] = 64;
		snakes[2][1] = 60;
		
		snakes[3][0] = 79;
		snakes[3][1] = 19;
		
		snakes[4][0] = 93;
		snakes[4][1] = 68;
		
		snakes[5][0] = 95;
		snakes[5][1] = 24;
		
		snakes[6][0] = 97;
		snakes[6][1] = 77;
		
		snakes[7][0] = 98;
		snakes[7][1] = 78;
	}
	
	/**
	 * Initializes the 2D-Array that contains the location of ladders.
	 */
	public static void setLadders(){
		ladders = new int[NUM_LADDERS][2];

		// ladders[i][0] represents the bottom of the ladder
		// ladders[i][1] represents the top of the ladder
		
		ladders[0][0] = 1;
		ladders[0][1] = 38;
		
		ladders[1][0] = 4;
		ladders[1][1] = 14;
		
		ladders[2][0] = 9;
		ladders[2][1] = 31;
		
		ladders[3][0] = 21;
		ladders[3][1] = 42;
		
		ladders[4][0] = 28;
		ladders[4][1] = 84;
		
		ladders[5][0] = 36;
		ladders[5][1] = 44;
		
		ladders[6][0] = 51;
		ladders[6][1] = 67;
		
		ladders[7][0] = 71;
		ladders[7][1] = 91;
		
		ladders[8][0] = 80;
		ladders[8][1] = 100;
	}
	
	// GETTERS AND SETTERS

	/**
	 * Gets the value in the board.
	 * @return A string representing the number in the board.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value in the board
	 * @param value A string containing the number in the board.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the availability of the board.
	 * @return A boolean representing the availability of the board.
	 */
	public boolean getPositionTaken() {
		return positionTaken;
	}

	/** Sets the availability of the board.
	 * @param A boolean containing the availability of the board.
	 */
	public void setPositionTaken(boolean positionTaken) {
		this.positionTaken = positionTaken;
	}

	
	
	
	
	
	
	
}
