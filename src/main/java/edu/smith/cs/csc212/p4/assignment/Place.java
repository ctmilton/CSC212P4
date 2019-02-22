package edu.smith.cs.csc212.p4.assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This represents a place in our text adventure.
 * @author jfoley
 *
 */
public class Place {
	/**
	 * This is a list of places we can get to from this place.
	 */
	private List<Exit> exits;
	
	/**
	 * This is the identifier of the place.
	 */
	private String id;
	
	/**
	 * This is a list of secret places we can get to from this place.
	 */
	public List<SecretExit> secretExits;
	
	/**
	 * This is a list of items that belong to this place.
	 */
	public List<String> placeItems;
	
	/**
	 * This is a list of locked exits that the player can unlock using certain items.
	 */
	public List<LockedExit> lockedExits;
	
	/**
	 * What to tell the user about this place.
	 */
	private String description;
	
	/**
	 * Whether reaching this place ends the game.
	 */
	private boolean terminal;
	
	
	/**
	 * Internal only constructor for Place. Use {@link #create(String, String)} or {@link #terminal(String, String)} instead.
	 * @param id - the internal id of this place.
	 * @param description - the user-facing description of the place.
	 * @param terminal - whether this place ends the game.
	 */
	private Place(String id, String description, boolean terminal) {
		this.id = id;
		this.description = description;
		this.exits = new ArrayList<>();
		this.secretExits = new ArrayList<>();
		this.lockedExits = new ArrayList<>();
		this.placeItems = new ArrayList<>();
		this.terminal = terminal;
	}
	
	/**
	 * Create an exit for the user to navigate to another Place.
	 * @param exit - the description and target of the other Place.
	 */
	public void addExit(Exit exit) {
		if (exit.isSecret()) {
			this.secretExits.add((SecretExit) exit);  
		} else if (exit.isLocked()){
			this.lockedExits.add((LockedExit) exit);
		} else {
			this.exits.add(exit);
		}
	}
	
	/**
	 * For gameplay, whether this place ends the game.
	 * @return true if this is the end.
	 */
	public boolean isTerminalState() {
		return this.terminal;
	}
	
	/**
	 * The internal id of this place, for referring to it in {@link Exit} objects.
	 * @return the id.
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Gets the items in this place.
	 * @return the list of items.
	 */
	public List<String> getItems() {
		return this.placeItems;
	}
	
	/**
	 * The narrative description of this place.
	 * @return what we show to a player about this place.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Get a view of the exits from this Place, for navigation.
	 * @return all the exits from this place, except for maybe the secretExits.
	 */
	public List<Exit> getVisibleExits() {
		List<Exit> visibleExits = new ArrayList<>();
		
		// Adds the normal exits
		for (Exit e: exits) {
			visibleExits.add(e);
		}
		
		// Adds the secret exits if they are no longer secret
		for (SecretExit se: secretExits) {
			if (!se.isSecret()) {
				visibleExits.add(se);
			}
		}
		
		// Adds the locked exits
		for (LockedExit le: lockedExits) {
			visibleExits.add(le);
		}
		
		return Collections.unmodifiableList(visibleExits);
	}
	
	/**
	 * This is a terminal location (good or bad).
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is the description of the place.
	 * @return the Place object.
	 */
	public static Place terminal(String id, String description) {
		return new Place(id, description, true);
	}
	
	/**
	 * Create a place with an id and description.
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is what we show to the user.
	 * @return the new Place object (add exits to it).
	 */
	public static Place create(String id, String description) {
		return new Place(id, description, false);
	}
	
	/**
	 * Implements what we need to put Place in a HashSet or HashMap.
	 */
	public int hashCode() {
		return this.id.hashCode();
	}
	
	/**
	 * Give a string for debugging what place is what.
	 */
	public String toString() {
		return "Place("+this.id+" with "+this.exits.size()+" exits.)";
	}
	
	/**
	 * Whether this is the same place as another.
	 */
	public boolean equals(Object other) {
		if (other instanceof Place) {
			return this.id.equals(((Place) other).id);
		}
		return false;
	}
	
	/**
	 * Makes each secret exit use the search function.
	 */
	public void search() {
		for (SecretExit se : this.secretExits) {
			se.search();
		}
	}
	
	/**
	 * This method adds an item to the list of items.
	 */
	public void addItem(String item) {
		this.placeItems.add(item);
	}
	
	/**
	 * This method removes an item from the list of items.
	 */
	public void removeItem(String item) {
		this.placeItems.remove(item);
	}
	
	/**
	 * Checks if an item exists in the list of items.
	 * @return true or false depending on whether the item exists.
	 */
	public boolean isPlaceItemFound(String item) {
		boolean itemFound = false;
		for (String pi : this.placeItems) {
			if (pi.contentEquals(item)) {
				itemFound = true;
			}
		}
		return itemFound;
	}
	
	/**
	 * Prints the description.
	 */
	public void printDescription() {
		System.out.println(this.description);
		if (this.placeItems.size() > 0) {
			System.out.println("In this place, you can take the below items:");
			for (String item : this.placeItems) {
				System.out.println(item);
			}
		}
		System.out.println("");
		System.out.println("Enter a command or select an option");
		System.out.println("Available commands are: search, take and stuff");
		System.out.println("");
	}
	
}
