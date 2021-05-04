
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

import java.util.Random;
import java.util.Scanner;

/**
 * This class implements the core engine of the game.
 * @author Kenny Phan
 */

public class LadderAndSnake {
	
	// Attributes
	private final int NUM_ROWS = 10;
	private final int NUM_COLS = 10;
	private final int NUM_SNAKES = 8;
	private final int NUM_LADDERS = 9;
	private int numOfPlayers;
	private Board[][] originalBoard = new Board[NUM_ROWS][NUM_COLS];
	private Board[][] realBoard = new Board[NUM_ROWS][NUM_COLS]; //The real printed board
	
	/**
	 * Constructs a LadderAndSnake object parameterized with the number of players.
	 * @param numOfPlayers the number of players in the game
	 */
	public LadderAndSnake(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
	}
	
	/**
	 * Starts the core engine of the game.
	 */
	public void play() {
		// Create scanner
		Scanner scanner = new Scanner(System.in);
		
		// Create an array of Player objects to store the playing order
		Player[] players = new Player[numOfPlayers];
		
		// Initialize the players array with their name and a die value
		for (int i = 0 ; i < numOfPlayers; i++) {
			// Prompt players to enter their name
			System.out.print("Enter the initials of Player" + (i+1) + ": ");
			String name = scanner.next();
			players[i] = new Player(name, flipDice(), 0); //sets the player's name, generates a random die value, and sets position 0 on the board
		}
		
		System.out.println();
		System.out.println("~~~~~ DETERMINING PLAYING ORDER ~~~~~");
		
		// Display the die value of each player
		for (int i = 0; i < numOfPlayers; i++) {
			System.out.println(players[i].getName() + " obtains a dice value of " + (int)players[i].getDice());
		}
		
		// Sort the array of players in ascending order based on die values
        for (int i = 0; i < players.length - 1; i++) 
            for (int j = 0; j < players.length - i - 1; j++) 
                if (players[j].getDice() > players[j+1].getDice()) { 
                    // Swap players[i] with player[i+1] 
                    Player temp = players[j]; 
                    players[j] = players[j+1]; 
                    players[j+1] = temp; 
                } 

        // Check for tie between four people
        if (numOfPlayers == 4) {
        	System.out.println();
            while (players[0].getDice() == players[1].getDice() && players[0].getDice() == players[2].getDice() && players[0].getDice() == players[3].getDice()) {
            	// Display the players who got a tie
            	System.out.println(players[0].getName() + ", " + players[1].getName() + ", " + players[2].getName() + " and " + players[3].getName()  + " got a tie! Attempting to break the tie.");
        		// Re-roll for the players who got a tie
            	players[0].setDice(flipDice());
        		players[1].setDice(flipDice());
        		players[2].setDice(flipDice());
        		players[3].setDice(flipDice());
        		// Display the new dice values for those players
        		for (int i = 0 ; i < numOfPlayers; i++) {
        			System.out.println(players[i].getName() + " obtains a dice value of " + (int)players[i].getDice());
        		}
				// Sort the players who re-rolled in ascending order
				bubbleSort(players, 0 ,numOfPlayers);
				System.out.println();
            }
        }
        
		// Check for tie between three people
		for (int i = 0; i < numOfPlayers - 2; i++) {
			while(players[i].getDice() == players[i+1].getDice() && players[i].getDice() == players[i+2].getDice()) {
				System.out.println();
				// Display the players who got a tie
				System.out.println(players[i].getName() + ", " + players[i+1].getName() + " and " + players[i+2].getName() +" got a tie! Attempting to break the tie.");
				// Re-roll for the players who got a tie
				int temp1 = flipDice();
				int temp2 = flipDice();
				int temp3 = flipDice();
				// Display the new dice values for those players
				System.out.println(players[i] + " obtains a dice value of " + temp1);
				System.out.println(players[i+1] + " obtains a dice value of " + temp2);
				System.out.println(players[i+2] + " obtains a dice value of " + temp3);
				// Set the new dice values for those players
				players[i].setDice(players[i].getDice() + (temp1*0.1));
				players[i+1].setDice(players[i+1].getDice() + (temp2*0.1));
				players[i+2].setDice(players[i+2].getDice() + (temp3*0.1));
				// Sort the players who re-rolled in ascending order
				bubbleSort(players, i, i+2);
				System.out.println();
			}
		}	  
        
		// Check for tie between two people
		for (int i = 0; i < numOfPlayers - 1; i++) {
			while (players[i].getDice() == players[i+1].getDice()) {
				// Display the players who got a tie
				System.out.println(players[i].getName() + " and " + players[i+1].getName() + " got a tie! Attempting to break the tie.");
				// Re-roll for the players who got a tie
				int temp1 = flipDice();
				int temp2 = flipDice();
				// Display the new dice values for those players
				System.out.println(players[i].getName() + " obtains a dice value of " + temp1);
				System.out.println(players[i+1].getName() + " obtains a dice value of " + temp2);
				// Set the new dice values for those players
				players[i].setDice(players[i].getDice() + temp1*0.1);
				players[i+1].setDice(players[i+1].getDice() + temp2*0.1);
				// Sort the players who re-rolled in ascending order
				Player temp;
				if (players[i].getDice() > players[i+1].getDice()) {
					temp = players[i];
					players[i] = players[i+1];
					players[i+1] = temp;
				}
			}
		}
		
		// Sort the players array in descending order
		for (int i = 0; i < numOfPlayers/2; i++) {
			Player temp = players[i];
			players[i] = players[numOfPlayers - i -1];
			players[numOfPlayers - i -1] = temp;
		}
		
		// Display final playing order
		System.out.println();
		System.out.print("Reached final playing order: ");
		for (int i = 0; i < numOfPlayers-1; i++) {
			System.out.print(players[i].getName() + " -> ");
		}
		System.out.print(players[players.length-1]);
		System.out.println();
		
		// Initialize the boards
		for (int i = 0; i < NUM_ROWS ; i++) {
			for (int j = 0; j < NUM_COLS; j++) {
				originalBoard[i][j] = new Board("0", false);
				realBoard[i][j] = new Board("0", false);
			}
		}
		
		// Set up both boards
		Board.setUpBoard(originalBoard);
		Board.setUpBoard(realBoard);
		
		// Initialize the snakes' array and the ladders' array
		Board.setSnakes();
		Board.setLadders();
		
		// Start the game
		System.out.println();
		System.out.println("~~~~~ STARTING GAME ~~~~~");
		// Prompt players to continue the game
		System.out.print("Are you ready to play? (y/n): ");
		String reply = scanner.next();
		if (!reply.equalsIgnoreCase("y") && !reply.equalsIgnoreCase("yes")) {
			System.out.println();
			System.out.println("~~~~~ BYE BYE! ~~~~~");
			System.exit(0);
		}
		System.out.println();
		
		boolean gameRunning = true; // used to keep the game running until there is a winner
		int player = 0; // used to keep count of the player's turn
		
		// Keep looping the game until there is a winner
		while (gameRunning) {
			
			// Select the current player to roll the die
			Player currentPlayer = players[player];
			
			// Acquire a die value for the player
			int dieValue = currentPlayer.throwDie();
			
			// Check what square the player lands on
			gameRunning = game(currentPlayer, dieValue);
			
			// Check if game is over (if a player reaches square 100)
			if (gameRunning == false) {
				System.out.println();
				System.out.println(currentPlayer + " reaches square 100!");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("          " + currentPlayer + " IS THE WINNER !!!");
				System.out.println(" Thank you for playing Snakes and Ladder.");
				System.out.println("                GOODBYE!                ");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.exit(0);
			}
			
			// If there is no winner yet, then go to the next player
			player++;
			
			// Reset the playing order after the last player plays
			if (player == numOfPlayers) {
				
				//Display the real board at the end of each round
				Board.printBoard(realBoard);
				
				// Reset the board after each round
				Board.setUpBoard(realBoard);
				
				player = 0;
				System.out.println();
				
				// Prompt players to continue the game if it is not over yet
				System.out.print("No one won yet. Continue the game? (y/n): ");
				String response = scanner.next();
				if (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("yes")) {
					System.out.println();
					System.out.println("~~~~~ GOODBYE! Thank you for playing. ~~~~~");
					System.exit(0);
				}
			}
			
			System.out.println();	
		}
		
	}
	
	/**
	 * This method checks what the player will land on and proceeds based on the position
	 * @param player the player to move
	 * @param dieValue an integer representing the spaces to move
	 * @return returns false if game is over, else true
	 */
	public boolean game(Player player, int dieValue) {
		
		// Keep track of the player's position
		int position = player.getPosition();
		position += dieValue;
		
		// Check if any player wins
		if (position == 100) {
			player.setPosition(100);
			System.out.println(player + " lands on square 100.");
			return false;
		
		// Continue the game normally if there is no winner
			
			//Check if player exceeds square 100
		} else if (position > 100) {
			int excess = position - 100;
			position = 100 - excess;
			player.setPosition(position);
			System.out.println(player + " exceeds square 100 and lands on square " + position + ".");
			
			// Place player on the board
			for (int i = 0; i < NUM_ROWS; i++) {
				for (int j = 0; j < NUM_COLS; j++) {
					if (String.valueOf(position).equals(originalBoard[i][j].getValue())) {
						if (realBoard[i][j].getPositionTaken()) {
							realBoard[i][j].setValue(realBoard[i][j].getValue().concat(player.getName()));
						} else {
							realBoard[i][j].setValue(player.getName());
							realBoard[i][j].setPositionTaken(true);
						}
					}
					
				}
			}
			return true;
			
		} else {
			// Check if player lands on a ladder
			for (int i = 0; i < NUM_LADDERS; i++) {
				if (position == Board.ladders[i][0]) {
					position = Board.ladders[i][1];
					player.setPosition(position);
					System.out.println(player + " lands on ladder " + Board.ladders[i][0]  + " and climbs to square " + position + "." );
					
					// If a player lands on ladder 80, then they climb to square 100, and they win the game.
					if (position == 100) {
						player.setPosition(position);
						return false;
					}
					
					// Place player on the board
					for (int i1 = 0; i1 < NUM_ROWS; i1++) {
						for (int j = 0; j < NUM_COLS; j++) {
							if (String.valueOf(position).equals(originalBoard[i1][j].getValue())) {
								// If position is already taken, then concatenate the player's name on that same square
								if (realBoard[i1][j].getPositionTaken()) {
									realBoard[i1][j].setValue(realBoard[i1][j].getValue().concat(player.getName()));
									// If position is not taken, then place the player on that square
								} else {
									realBoard[i1][j].setValue(player.getName());
									realBoard[i1][j].setPositionTaken(true);
								}
							}
							
						}
					}
					return true;
				}
			}
			
			// Check if player lands on a snake
			for (int i = 0; i < NUM_SNAKES; i++) {
				if (position == Board.snakes[i][0]) {
					position = Board.snakes[i][1];
					player.setPosition(position);
					System.out.println(player + " lands on snake " + Board.snakes[i][0] + " and slides down to square " + position + ".");
					
					// Place player on the board
					for (int i1 = 0; i1 < NUM_ROWS; i1++) {
						for (int j = 0; j < NUM_COLS; j++) {
							if (String.valueOf(position).equals(originalBoard[i1][j].getValue())) {
								if (realBoard[i1][j].getPositionTaken()) {
									realBoard[i1][j].setValue(realBoard[i1][j].getValue().concat(player.getName()));
								} else {
									realBoard[i1][j].setValue(player.getName());
									realBoard[i1][j].setPositionTaken(true);
								}
							}
							
						}
					}
					
					return true;
				}
			}
			
			// If player does not win or does not land on a snake or ladder, then update position
			player.setPosition(position);
			System.out.println(player + " lands on square " + position + ".");

			// Put player's initials on the board
			for (int i = 0; i < NUM_ROWS; i++) {
				for (int j = 0; j < NUM_COLS; j++) {
					if (String.valueOf(position).equals(originalBoard[i][j].getValue())) {
						if (realBoard[i][j].getPositionTaken()) {
							realBoard[i][j].setValue(realBoard[i][j].getValue().concat(player.getName()));
						} else {
							realBoard[i][j].setValue(player.getName());
							realBoard[i][j].setPositionTaken(true);
						}
					}
					
				}
			}

			return true;
			
		}
	}
	// ---------------------END OF GAME METHOD-----------------------------
	
	/**
	 * Rolls a die
	 * @return A random integer between 1-6.
	 */
	public static int flipDice() {
		Random random = new Random();
		return (random.nextInt(6) + 1);
	}
	
	/**
	 * Sorts a section of an array
	 * @param arr an array of player
	 * @param start an integer value representing the starting point
	 * @param end an integer value representing the ending point
	 */
	public static void bubbleSort(Player[] arr, int start, int end) {
		for (int j = start; j < end-1; j++){ 
			for (int i = start; i < end-j-1; i++) { 
				if (arr[i].getDice() > arr[i + 1].getDice()) { 
					Player temp = arr[i]; 
					arr[i] = arr[i + 1]; 
					arr[i + 1] = temp; 
				} 
			}
		} 
	}
	
	
}
