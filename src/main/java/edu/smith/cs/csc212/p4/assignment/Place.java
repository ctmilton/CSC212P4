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
	 * This is a list of keys that belong to this place.
	 */
	public List<String> keys;
	
	/**
	 * This is a list of keys that the Player has collected.
	 */
	public List<String> inventory;
	
	/**
	 * This shows whether the keys in this place have been taken or not.
	 */
	private boolean isTaken;
	
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
	 * @param keys - the items that the Player can take.
	 * @param terminal - whether this place ends the game.
	 */
	private Place(String id, String description, List<String> keys, boolean terminal) {
		this.id = id;
		this.description = description;
		this.exits = new ArrayList<>();
		this.secretExits = new ArrayList<>();
		this.keys = new ArrayList<>();
		this.inventory = new ArrayList<>();
		this.isTaken = false;
		this.terminal = terminal;
	}
	
	/**
	 * Create an exit for the user to navigate to another Place.
	 * @param exit - the description and target of the other Place.
	 */
	public void addExit(Exit exit) {
		if (exit.isSecret()) {
			this.secretExits.add((SecretExit) exit);  
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
	 * The narrative description of this place.
	 * @return what we show to a player about this place.
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * The narrative description of this place along with the keys.
	 * @return what we show to a player about this place and the objects within it.
	 */
	public void printDescription() {
		if (isTaken) {
			System.out.println(this.description);
			System.out.println("In this place, you can take:");
			System.out.println(this.keys);
			System.out.println("To take the items, type 'take'.");
		} else {
			System.out.println(this.description);
		}
	}

	/**
	 * Get a view of the exits from this Place, for navigation.
	 * @return all the exits from this place, except for maybe the secretExits.
	 */
	public List<Exit> getVisibleExits() {
		List<Exit> visibleExits = new ArrayList<>();
		
		for (Exit e: exits) {
			visibleExits.add(e);
		}
		
		for (SecretExit se: secretExits) {
			if (!se.isSecret()) {
				visibleExits.add(se);
			}
		}
		
		return Collections.unmodifiableList(visibleExits);
	}
	
	/**
	 * The key items of this place.
	 * @return what we show to a player about the items in this place.
	 */
	public List<String> getKeys() {
		return this.keys;
	}
	
	/**
	 * The Player's key items.
	 * @return all of the items that they Player has taken.
	 */
	public List<String> getInventory() {
		return inventory;
	}
	
	/**
	 * Takes the keys from this place and adds them to the inventory.
	 * @return all of the keys the Player has taken.
	 */
	public List<String> takeKeys() {
		if (isTaken == false) {
			for (String k : keys) {
				inventory.add(k);
			}
			isTaken = true;
		}
		return inventory;
	}
	
	/**
	 * This is a terminal location (good or bad).
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is the description of the place.
	 * @return the Place object.
	 */
	public static Place terminal(String id, String description, List<String> keys) {
		return new Place(id, description, keys, true);
	}
	
	/**
	 * Create a place with an id and description.
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is what we show to the user.
	 * @return the new Place object (add exits to it).
	 */
	public static Place create(String id, String description, List<String> keys) {
		return new Place(id, description, keys, false);
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
	
}
