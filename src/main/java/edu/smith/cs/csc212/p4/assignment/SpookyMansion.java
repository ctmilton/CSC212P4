package edu.smith.cs.csc212.p4.assignment;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

/**
 * SpookyMansion, the game.
 * @author jfoley
 *
 */
public class SpookyMansion implements GameWorld {
	private Map<String, Place> places = new HashMap<>();
	
	/**
	 * Where should the player start?
	 */
	@Override
	public String getStart() {
		return "entranceHall";
	}

	/**
	 * This constructor builds our SpookyMansion game.
	 * Got help with Array.asList from the TA, Tiffany.
	 */
	public SpookyMansion() {
		//List<String> items1 = Arrays.asList("backpack", "flashlight");
		List<String> items1 = new ArrayList();
		items1.add("backpack");
		items1.add("flashlight");
		
		
		Place entranceHall = insert(
				Place.create("entranceHall", "You are in the grand entrance hall of an abandoned college dorm.\n"
						+ "The front door is locked. There's no one around. How did you get here?\n"
						+ "The only way to get out is to find your coffin.", items1));
		entranceHall.addExit(new Exit("northWing", "There is a narrow path to the right."));
		entranceHall.addExit(new Exit("southWing", "Look. There is a staircase going down on your left."));
		entranceHall.addExit(new Exit("westWing", "There is a red door in front of you."));
		
		List<String> items2 = Arrays.asList("pencil", "broken phone");
		
		Place northWing = insert(
				Place.create("northWing", "You have entered the living room.\n" + 
		                     "The lights are out. You can't turn them on.", items2
						));
		northWing.addExit(new Exit("entranceHall", "There is a narrow path beside you."));
		northWing.addExit(new Exit("teaRoom", "There is a light coming from behind a pillar."));
		
		List<String> items3 = Arrays.asList("teapot", "spoon");
		
		Place teaRoom = insert(
				Place.create("teaRoom", "It seems you have entered the tea room.\n" +
									"You see a large piano covered in dust and cobwebs.\n" +
									"The piano moves towards you. You jump out of the way.\n" +
									"When you look back, you see that the piano now prevents you from returning.", items3
						));
		teaRoom.addExit(new Exit("diningHall", "The only option is to keep moving forward."));
		
		List<String> items4 = Arrays.asList("rotten food", "broken plate");

		Place diningHall = insert(Place.create("diningHall",
				"You smell rotting food. You have entered the dining hall.\n" +
				"It seems like there had been a large commotion for the place was a mess.\n" +
				"There were broken tables and chairs. Half eaten food littered the ground.", items4
		));
		diningHall.addExit(new Exit("entranceHall", "The smell is too strong and you faint."));
		diningHall.addExit(new SecretExit("bedroom1", "You've encountered a large hole behind a table."));
		
		List<String> items5 = Arrays.asList("piece of glass", "spider");

		Place southWing = insert(Place.create("southWing", "As you go down the staircase, you feel somthing grab hold of you leg.\n"
				+ "You trip and fall. It hurts...\n" + "As you stand back up, you notice that you are in the laundry room.\n"
				+ "The washing machines are still running but there aren't any clothes inside.", items5
				));
		southWing.addExit(new Exit("entranceHall", "You feel cold and hurt. Going back up the stairs will make you feel warmer and safer."));
		southWing.addExit(new Exit("supplyCloset", "There's a supply closet a few feet away. Maybe there are bandages inside."));
		
		List<String> items6 = Arrays.asList("broom", "bandages", "pain killers");
		
		Place supplyCloset = insert(
				Place.create("supplyCloset", "You are in the supply closet.\n" +
							"You see some bandages. However, there are red splotches on them.", items6
							));
		supplyCloset.addExit(new Exit("entranceHall", "You don't want to see what those red splotches are, so you leave."));
		supplyCloset.addExit(new Exit("northWing", "Curiousity overtakes rationality." +
					"You step closer to take a look, but the ceiling collapses above you and a force pulls you upwards."));
		supplyCloset.addExit(new Exit("teaRoom", "You ignore your need for bandages and go through a black door."));
		
		List<String> items7 = Arrays.asList("painting of flowers");
		
		Place westWing = insert(Place.create("westWing", "You've entered the living quarters. The college students' rooms are here.", items7));
		westWing.addExit(new Exit("entranceHall", "You don't want to invade people's privacy, even if they are no longer alive."));
		westWing.addExit(new Exit("bedroom1", "The closest bedroom has cute flowers painted on it. What could possibly go wrong?"));
		
		List<String> items8 = Arrays.asList("dust", "a book on clowns");
		
		Place bedroom1 = insert(Place.create("bedroom1", "Something comes flying at you. You barely dodge it.\n" +
											"When you look down, you see a headless doll. You want to leave immediately.", items8));
		bedroom1.addExit(new Exit("westWing", "You go through a door as fast as you can."));
		bedroom1.addExit(new Exit("bathroom", "You run where you can."));
		bedroom1.addExit(new Exit("bedroom2", "You decide to take a chance and go through the door across the room."));
		
		List<String> items9 = Arrays.asList("handfull of water");
		
		Place bathroom = insert(Place.create("bathroom", "It's a bathroom. There is nothing scary about it.\n" +
							"You release a sigh of relief but it is short lived.", items9));
		bathroom.addExit(new Exit("supplyCloset", "You were leaning on a wall but it suddenly disappeared and you fell."));
		
		List<String> items10 = Arrays.asList("knife");
		
		Place bedroom2 = insert(Place.create("bedroom2", "Another headless doll is thrown at you. But this one has a knife in its plastice hands.", items10));
		bedroom2.addExit(new Exit("bedroom1", "You fear for your life and run."));
		bedroom2.addExit(new Exit("bathroom", "You just want to hide."));
		bedroom2.addExit(new Exit("bedroom3", "You foolishly run across the room and go through a small door."));
		
		List<String> items11 = Arrays.asList("headless doll");
		
		Place bedroom3 = insert(Place.create("bedroom3", "You believe that you must be cursed for another headless doll is coming your way.", items11));
		bedroom3.addExit(new Exit("bedroom2", "You run back the way you came and never look back."));
		bedroom3.addExit(new Exit("crypt", "You see a rather dark and ominous corner and decide to hide there."));
		
		List<String> items12 = Arrays.asList("coffin");
		
		Place crypt = insert(Place.terminal("crypt", "You have found your coffin.\n"
				+ "It is a perfect fit for you.\n"
				+ "Maybe you can try it out if you decide to visit this college dorm again.", items12));
		
		// Make sure your graph makes sense!
		checkAllExitsGoSomewhere();
	}

	/**
	 * This helper method saves us a lot of typing. We always want to map from p.id
	 * to p.
	 * 
	 * @param p - the place.
	 * @return the place you gave us, so that you can store it in a variable.
	 */
	private Place insert(Place p) {
		places.put(p.getId(), p);
		return p;
	}

	/**
	 * I like this method for checking to make sure that my graph makes sense!
	 */
	private void checkAllExitsGoSomewhere() {
		boolean missing = false;
		// For every place:
		for (Place p : places.values()) {
			// For every exit from that place:
			for (Exit x : p.getVisibleExits()) {
				// That exit goes to somewhere that exists!
				if (!places.containsKey(x.getTarget())) {
					// Don't leave immediately, but check everything all at once.
					missing = true;
					// Print every exit with a missing place:
					System.err.println("Found exit pointing at " + x.getTarget() + " which does not exist as a place.");
				}
			}
		}
		
		// Now that we've checked every exit for every place, crash if we printed any errors.
		if (missing) {
			throw new RuntimeException("You have some exits to nowhere!");
		}
	}

	/**
	 * Get a Place object by name.
	 */
	public Place getPlace(String id) {
		return this.places.get(id);		
	}
}
