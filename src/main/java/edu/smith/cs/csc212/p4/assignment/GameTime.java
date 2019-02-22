package edu.smith.cs.csc212.p4.assignment;

/**
 * This class represents the time system in the game.
 * @author ctmilton
 */
public class GameTime {
	
	/**
	 * This keeps track of the time (in 24 hours) during the game.
	 */
	private int hour = 0;
	
	/**
	 * This keeps track of the total hours that the player spent to finish the game.
	 */
	private int timeSpent = 0;
	
	/**
	 * This method gets the current time in the game.
	 * @return the game time.
	 */
	public int getHour() {
		return this.hour;
	}
	
	/**
	 * This method calculates the game time and the total time the player spends in the game.
	 */
	public void increaseHour() {
		if (this.hour == 23) {
			this.hour = 0;
		}
		else {
			this.hour = this.hour+1;
		}
		this.timeSpent = this.timeSpent+1;
	}
	
	/**
	 * This method prints the current time in the game.
	 */
	public void printCurrentTime() {
		System.out.println("Current time is: " + this.hour+"\n");
	}
	
	/**
	 * This method prints the total time the player has spent in the game.
	 */
	public void printTimeSpent() {
		System.out.println("Total time spent by player is: " + this.timeSpent+" hours\n");
	}
}
