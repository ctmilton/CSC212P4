package edu.smith.cs.csc212.p4.assignment;

/**
 * This class represents a locked exit that can only be accessed through an item.
 * @author ctmilton
 *
 */
public class LockedExit extends Exit{
	
	private String key = "";
	
	/**
	 * Create a new Exit.
	 * @param target - where it goes.
	 * @param description - how it looks.
	 * @param key - the items that belong to this locked exit.
	 */
	public LockedExit(String target, String description, String key) {
		super(target, description);
		this.key = key;
	}
	
	@Override
	public boolean isLocked() {
		return true;
	}
	
	/**
	 * Gets the key that belongs to this locked exit.
	 * @return key
	 */
	public String getKey() {
		return this.key;
	}
	
	/**
	 * Takes an item input and sets it as the key that belongs to this exit.
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}
}
