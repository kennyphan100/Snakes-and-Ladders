
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
 * This class implements players who can play Snakes and Ladders.
 * @author Kenny Phan
 *
 */

public class Player {
	
	// Attributes
	private String name;
	private double dice;
	private int position;

	/**
	 * Constructs a player object parameterized with a name, a die value, and a position.
	 * @param name The player's initials.
	 * @param dice The player's die value.
	 * @param position The player's location in the board.
	 */
	public Player(String name, double dice, int position) {
		this.name = name;
		this.dice = dice;
		this.position = position;
	}
	
	/** 
	 * Generates a random die value and prints it.
	 * @return A random generated die value.
	 */
	public int throwDie() { 
		
		// Generate random die
		int dieValue = LadderAndSnake.flipDice();
		
		//Prompt the dice value
		System.out.println(name + " obtains a dice value of " + dieValue + ".");
		
		return dieValue;
	}
	
	// toString method
	/**
	 * Returns a string representing the player's name.
	 */
	public String toString() {
		return (name);
	}
	
	// Getters & Setters	
	/**
	 * Gets the player's name.
	 * @return A string representing the player's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the player's name.
	 * @param A string containing the player's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets a die value.
	 * @return a die value belonging to the player object
	 */
	public double getDice() {
		return dice;
	}

	/**
	 * Sets the die value of a player.
	 * @param A double containing the player's die value.
	 */
	public void setDice(double dice) {
		this.dice = dice;
	}
	
	/** 
	 * Gets the player's position.
	 * @return An integer representing the player's location.
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Sets the player's position
	 * @param An integer containing the player's location.
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	
	
}
