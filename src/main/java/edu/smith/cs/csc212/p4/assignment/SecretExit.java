package edu.smith.cs.csc212.p4.assignment;

/**
 * This class represents a hidden exit from one place to another place.
 * @author ctmilton
 *
 */

public class SecretExit extends Exit {
	/**
	 * expresses whether the SecretExit is hidden from the player
	 */
	private boolean hidden = true;
	
	/**
	 * Create a new SecretExit.
	 * @param target - where it goes.
	 * @param description - how it looks.
	 */
	public SecretExit(String target, String description) {
		super(target, description);
	}

	@Override
	public boolean isSecret() {
		if (hidden) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public void search() {
		hidden = false;
	}
}
