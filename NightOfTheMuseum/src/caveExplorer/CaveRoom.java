package caveExplorer;

import cobyZhehao.CobyRoom;
import cobyZhehao.zhehaoRoom;
import victorRemington.RemingtonFrontEnd;
import victorRemington.VictorRoom;
import jasonDavid.DavidFront;
import jasonDavid.DavidNPC;
import jasonDavid.JasonNPC;
import jasonDavid.JasonRoom;

public class CaveRoom {

	private String description;//tells what the room looks like
	private String directions;//tells what you can do
	private String contents;//a symbol representing what's in the room
	private String defaultContents;
	//the rooms are organize by direction, 'null' signifies no room/door in that direction
	private CaveRoom[] borderingRooms;
	private Door[] doors;
	
	//constants
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH =2;
	public static final int WEST = 3;
	
	
	public CaveRoom(String description) {
		this.description = description;
		setDefaultContents(" ");
		contents = defaultContents;
		//difference between defaultContents and contents is "contents" becaomes an 'X' when you are
		//inside this room, when you leave, it goes back to defaultContents
		
		//note: by default, arrays will populate with 'null' meaning there are no connections
		borderingRooms = new CaveRoom[4];
		doors = new Door[4];
		setDirections();
	}


	/**
	 * for every door in doors[] appends a String to "directions" describing the access.
	 * For example:
	 *  "There is a door to the north"
	 *  "There is a door to the south"...etc
	 *  
	 * If there are no doors at all, directions should say:
	 *    "There are no doors, you are trapped in here."
	 */
	public void setDirections() {
		directions = "";
		boolean doorFound = false;
		for(int i =0; i < doors.length; i++) {
			if(doors[i] != null) {
				doorFound = true;
				directions += "\n   There is a "+doors[i].getDescription() + " to " +
				toDirection(i)+". "+doors[i].getDetails();
			}
		}
		if(!doorFound) {
			directions += "There is no way out. You are trapped in here.";
		}
	}
	
	/**
	 * converts an int to a direction
	 * 	toDirection(0) -> "the North"
	 * etc
	 * @param dir
	 * @return
	 */
	public static String toDirection(int dir) {
		String[] direction = {"the North", "the East", "the South","the West"};
		return direction[dir];
	}


	public void enter() {
		contents = "X";
	}
	
	public void leave() {
		contents = defaultContents;
	}
	
	/**
	 * Gives this room access to anotherRoom (and vice-versa)
	 * and sets a door between them, updating the directions
	 * @param direction
	 * @param anotherRoom
	 * @param door
	 */
	public void setConnection(int direction, CaveRoom anotherRoom, 
			Door door) {
		addRoom(direction, anotherRoom, door);
		anotherRoom.addRoom(oppositeDirection(direction), this, door);
	}
	
	
	public static int oppositeDirection(int direction) {
		return (direction + 2)%4;
	}


	public void addRoom(int direction, CaveRoom cave, Door door) {
		borderingRooms[direction] = cave;
		doors[direction] = door;
		setDirections();
	}
	
	public void interpretInput(String input) {
		while(!isValid(input)) {
			printAllowedEntry();
			input = CaveExplorer.in.nextLine();
		}
		//task: convert user input into a direction
		//DO NOT USE AN IF STATEMENT
		//(or, if you must, don't use more than 1)
		String dirs = validKeys();
		respondToKey(dirs.indexOf(input));
	}
	// override to add more keys, but always include "wdsa"
	public String validKeys() {
		return "wdsa";
	}
	//override to print a custom string describing what keys do
	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's', or 'd'.");
	}


	private boolean isValid(String input) {
		String validEntries = validKeys();
		return validEntries.indexOf(input) > -1 && input.length() ==1;
	}

	public void respondToKey(int direction) {
		//first, protect against null pointer exception
		//(user cannot go through non-existent door
		if (direction < 4) {
		if(borderingRooms[direction] != null && 
				doors[direction] != null) {
			CaveExplorer.currentRoom.leave();
			CaveExplorer.currentRoom = borderingRooms[direction];
			CaveExplorer.currentRoom.enter();
			CaveExplorer.inventory.updateMap();
		}
		}
		else {
			performAction(direction);
		}
	}
	//override to give response to keys other than 'wasd'
	public void performAction(int direction) {
		System.out.println("That key does nothing");
		
	}


	/**
	 * This will be where your group sets up all the caves 
	 * and all the connections
	 */
	public static void setUpCaves() {
		//ALL OF THIS CODE CAN BE CHANGED
		//1. Decide how big your caves should be
		CaveExplorer.caves = new CaveRoom[10][10];
		//2. Populate with caves and a defualt description: hint: when starting, use coordinates (helps debugging)
		for(int row = 0; row < CaveExplorer.caves.length; row++) {
			//PLEASE PAY ATTENTION TO THE DIFFERENCE:
			for(int col = 0; col < CaveExplorer.caves[row].length; col++) {
				//create a "default" cave
				CaveExplorer.caves[row][col] = 
						new CaveRoom("This cave has coords ("+row+","+col+")");
			}
		}
		//3. Replace default rooms with custom rooms
		//--- WE WILL DO LATER
		
		//CaveRoom customRoom = new CobyRoom("There is a statue in front of you. Press 'f' to interact.");

		//CaveExplorer.caves[2][3] = customRoom;

		CaveExplorer.caves[3][2] = new zhehaoRoom("There is a statue in front of you. Press 'f' to interact");
		
		CaveExplorer.caves[3][3] = new VictorRoom("This is coords 3, 3. There is a shiny box in the middle of the room. Press 'f' to touch it.", 0.5, -20,
				"The box is empty", "The box was a trap!. You have been struck by an arrow", 10);
		
		CaveExplorer.caves[0][2] = new RemingtonFrontEnd("The remains of a fire seem to be in the corner of the room. Maybe the owner will come back soon.");
		
		
		//--- WE WILL DO LATER

		
		CaveRoom customRoom = new CobyRoom("Room");
		CaveExplorer.caves[1][1] = customRoom;
		
		CaveRoom customRoom2 = new DavidFront("Room");
		CaveExplorer.caves[0][3] = customRoom2;
		

		

		NPCRoom customRoom3 = new JasonRoom("There is a mysterious carving in the floor of the room. Press 'f' to interact. Be warned this may do strange things.");
		CaveExplorer.caves[1][1] = customRoom3;


		//4. Set your starting room:
		CaveExplorer.currentRoom = CaveExplorer.caves[0][3];
		CaveExplorer.currentRoom.enter();
		//5. Set up doors
		CaveRoom[][] c = CaveExplorer.caves;

		c[0][3].setConnection(SOUTH, c[1][3], new Door());
		c[1][3].setConnection(NORTH, c[0][3], new Door()); 
		
		c[1][3].setConnection(SOUTH, c[2][3], new Door());
		c[2][3].setConnection(NORTH, c[1][3], new Door()); 
		
		c[3][2].setConnection(EAST, c[3][3], new Door());
		c[3][3].setConnection(WEST, c[3][2], new Door());
		
		c[2][3].setConnection(SOUTH, c[3][3], new Door());
		c[3][3].setConnection(NORTH, c[2][3], new Door());

		c[0][1].setConnection(SOUTH, c[1][1], new Door());
		c[1][1].setConnection(NORTH, c[0][1], new Door());
		
		c[0][1].setConnection(EAST, c[0][2], new Door());
		c[0][2].setConnection(WEST, c[0][1], new Door());
		
		c[0][2].setConnection(EAST, c[0][3], new Door());
		c[0][3].setConnection(WEST, c[0][2], new Door());
		
		c[0][3].setConnection(EAST, c[0][4], new Door());
		c[0][4].setConnection(WEST, c[0][3], new Door());
		
		c[6][0].setConnection(SOUTH, c[7][0], new Door());
		c[7][0].setConnection(NORTH, c[6][0], new Door());
		
		c[7][0].setConnection(SOUTH, c[8][0], new Door());
		c[8][0].setConnection(NORTH, c[7][0], new Door());
		
		c[6][1].setConnection(SOUTH, c[7][1], new Door());
		c[7][1].setConnection(NORTH, c[6][1], new Door());
		
		c[7][1].setConnection(SOUTH, c[8][1], new Door());
		c[8][1].setConnection(NORTH, c[7][1], new Door());
		
		c[6][2].setConnection(SOUTH, c[7][2], new Door());
		c[7][2].setConnection(NORTH, c[6][2], new Door());
		
		c[7][2].setConnection(SOUTH, c[8][2], new Door());
		c[8][2].setConnection(NORTH, c[7][2], new Door());
		
		c[6][3].setConnection(SOUTH, c[7][3], new Door());
		c[7][3].setConnection(NORTH, c[6][3], new Door());
		
		c[7][3].setConnection(SOUTH, c[8][3], new Door());
		c[8][3].setConnection(NORTH, c[7][3], new Door());
		
		c[6][0].setConnection(EAST, c[6][1], new Door());
		c[6][1].setConnection(WEST, c[6][0], new Door());
		
		c[7][0].setConnection(EAST, c[7][1], new Door());
		c[7][1].setConnection(WEST, c[7][0], new Door());
		
		c[8][0].setConnection(EAST, c[8][1], new Door());
		c[8][1].setConnection(WEST, c[8][0], new Door());
		
		c[6][1].setConnection(EAST, c[6][2], new Door());
		c[6][2].setConnection(WEST, c[6][1], new Door());
		
		c[7][1].setConnection(EAST, c[7][2], new Door());
		c[7][2].setConnection(WEST, c[7][1], new Door());
		
		c[8][1].setConnection(EAST, c[8][2], new Door());
		c[8][2].setConnection(WEST, c[8][1], new Door());
		
		//david is cool
		
		c[7][2].setConnection(EAST, c[7][3], new Door());
		c[7][3].setConnection(WEST, c[7][2], new Door());
		
		c[8][2].setConnection(EAST, c[8][3], new Door());
		c[8][3].setConnection(WEST, c[8][2], new Door());
		
		
		
		/** 
		 * Special requests:
		 * moving objects in caves
		 * what happens when you lose?
		 * can another object move toward you?
		 */	
	}

	private void makeConnectionBetweenRooms(CaveRoom r1, CaveRoom r2, int dir, Door d1, Door d2) {
		r1.setConnection(dir, r2, d1);
		r2.setConnection(oppositeDirection(dir), r1, d2);
	}

	public String getDescription() {
		return description + "\n"+directions;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getContents() {
		return contents;
	}

	
	public void setContents(String contents) {
		this.contents = contents;
	}


	public void setDefaultContents(String defaultContents) {
		this.defaultContents = defaultContents;
	}


	public Door getDoor(int direction) {
		return doors[direction];
	}
	
	

}
