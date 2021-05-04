
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

import java.util.Scanner;

/**
 * This class contains the main driver.
 * @author Kenny Phan
 */

public class PlayLadderAndSnake {
	
	public static void main(String[] args) {
		 
		// Initialize scanner
		Scanner scanner = new Scanner(System.in);
		
		// Welcome message
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("  WELCOME TO SNAKES AND LADDERS! (Made by Kenny Phan)");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		// Variables
		int numOfPlayers;
		int count = 1;
		
		// Prompt user for the number of players and check for validity
		System.out.print("Enter the # of players for your game - Number must be between 2 and 4 inclusively: ");
		numOfPlayers = scanner.nextInt();
		
		while (numOfPlayers < 2 || numOfPlayers > 4) {
			System.out.print("Bad Attempt " + count + " - Invalid # of players. Please enter a # between 2 and 4 inclusively: ");
			numOfPlayers = scanner.nextInt();
			count++;
			if (count == 3 && (numOfPlayers < 2 || numOfPlayers > 4)) {
				System.out.print("Bad Attempt " + count + " - Invalid # of players. Please enter a # between 2 and 4 inclusively. This is your last attempt: ");
				numOfPlayers = scanner.nextInt();
				count++;
			}
			if (count == 4 && (numOfPlayers < 2 || numOfPlayers > 4)) {
				System.out.println("Bad Attempt " + count + "! You have exhausted all your chances. Program will terminate. BYE BYE!");
				// Stop the game if failed four attempts
				System.exit(0);
			}
		}

		// Display the number of players
		System.out.println("-------------------------------------------------------");
		System.out.println("~~~~~ " + numOfPlayers + " PEOPLE ARE PLAYING! ~~~~~\n");

		// Create a LadderAndSnake object
		LadderAndSnake game = new LadderAndSnake(numOfPlayers);
		
		// Call the play method
		game.play();
		
		// Close scanner
		scanner.close();
	}
	

	


	
	
	
}
