package edu.smith.cs.csc212.p4.assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * This is our main class for P4. It interacts with a GameWorld and handles user-input.
 * @author jfoley
 *
 */
public class InteractiveFiction {

	/**
	 * This is where we play the game.
	 * @param args
	 */
	public static void main(String[] args) {
		// This is a text input source (provides getUserWords() and confirm()).
		TextInput input = TextInput.fromArgs(args);

		// This is the game we're playing.
		GameWorld game = new SpookyMansion();
		
		// This is the current location of the player (initialize as start).
		// Maybe we'll expand this to a Player object.
		String place = game.getStart();
		
		// Start the game timer!
		GameTime timeSpent = new GameTime();
		List<String> playerItems = new ArrayList<String>();

		// Play the game until quitting.
		// This is too hard to express here, so we just use an infinite loop with breaks.
		while (true) {
			// Print the description of where you are.
			Place here = game.getPlace(place);
			here.printDescription();
			
			// Let the player know what time it is.
			timeSpent.printCurrentTime();

			// Game over after print!
			if (here.isTerminalState()) {
				break;
			}

			// Show a user the ways out of this place.
			List<Exit> exits = here.getVisibleExits();
			
			for (int i=0; i<exits.size(); i++) {
			    Exit e = exits.get(i);
				System.out.println(" ["+i+"] " + e.getDescription());
			}

			// Figure out what the user wants to do, for now, only "quit" is special.
			List<String> words = input.getUserWords(">");
			if (words.size() == 0) {
				System.out.println("Must type something!");
				continue;
			}
			
			// Get the word they typed as lowercase, and no spaces.
			String action = words.get(0).toLowerCase().trim();
			
			// Get the second word for the take action.
			String action2 = "";
			
			if (action.contentEquals("take")) {
				if (words.size() == 1) {
					System.out.println("Must specify the item for this action");
					continue;
				}
				else {
					action2 = words.get(1).toLowerCase().trim();
				}
			}
			else if (words.size() > 1) {
					System.out.println("Only give me 1 word at a time!");
					continue;
			}
			
			if (action.equals("quit") || action.contentEquals("q") || action.contentEquals("escape")) {
				if (input.confirm("Are you sure you want to quit?")) {
					break;
				} else {
					continue;
				}
			}
			
			// This is where the search command is implemented.
			if (action.contentEquals("search")) {
				for (SecretExit se : here.secretExits) {
					se.search();
				}
				continue;
			}
			
			// This is where the take command is implemented.
			if (action.contentEquals("take")) {
				if (here.isPlaceItemFound(action2)) {
					here.removeItem(action2);
					playerItems.add(action2);
				}
				else {
					System.out.println("Invalid item: " + action2);
				}
				continue;
			}
			
			// This is where the stuff command is implemented.
			if (action.contentEquals("stuff")) {
				if (playerItems.size()>0) {
					System.out.println("You have taken the below items:");
					for (String item: playerItems) {
						System.out.println(item);
					}
				}
				else {
					System.out.println("You have nothing");
				}
				System.out.println("");
				continue;
			}
			
			// From here on out, what they typed better be a number!
			Integer exitNum = null;
			try {
				exitNum = Integer.parseInt(action);
			} catch (NumberFormatException nfe) {
				System.out.println("That's not something I understand! Try a number!");
				continue;
			}
			
			if (exitNum < 0 || exitNum > exits.size()) {
				System.out.println("I don't know what to do with that number!");
				continue;
			}

			// Move to the room they indicated.
			Exit destination = exits.get(exitNum);
			if (destination.isLocked()) {
				LockedExit destination2 = (LockedExit) exits.get(exitNum);
				for (String item: playerItems) {
					if (item.contentEquals(destination2.getKey())) {
						place = destination2.getTarget();
						timeSpent.increaseHour();
						continue;
					}	
				}
				System.out.println("You don't have the key for the locked exit");
				continue;
			}
			place = destination.getTarget();
			timeSpent.increaseHour();
		}

		// You get here by "quit" or by reaching a Terminal Place.
		System.out.println(">>> GAME OVER <<<");
	}

}
