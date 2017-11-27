package caveExplorer;

import cobyZhehao.CobyRoom;
import cobyZhehao.MiniStarter;
import cobyZhehao.zhehaoRoom;
import victorRemington.RemingtonFrontEnd;
import victorRemington.VictorRoom;
import jasonDavid.DavidFront;
import jasonDavid.DavidNPC;
import jasonDavid.EndGameRoom;
import jasonDavid.JasonDavidStartRoom;
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
		return "wdsae";
	}
	//override to print a custom string describing what keys do
	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's', 'd', 'e'.");
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
				doors[direction] != null && !borderingRooms[direction].getDoor(oppositeDirection(direction)).getLocked() && !doors[direction].getLocked()){
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
		if(direction == 5) {
			//openDoor()
		}
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
						new NPCRoom("This cave has coords ("+row+","+col+")");
			}
		}
		//3. Replace default rooms with custom rooms
		//--- WE WILL DO LATER
		
		CaveRoom customRoom = new CobyRoom("There is a statue in front of you. Press 'f' to interact.");

		CaveExplorer.caves[2][3] = customRoom;

		CaveExplorer.caves[3][2] = new zhehaoRoom("There is a statue in front of you. Press 'f' to interact");
		
		CaveExplorer.caves[3][3] = new VictorRoom("This is coords 3, 3. There is a shiny box in the middle of the room. Press 'f' to touch it.", 0.5, -20,
				"The box is empty", "The box was a trap!. You have been struck by an arrow", 10);
		
		CaveExplorer.caves[0][2] = new RemingtonFrontEnd("The remains of a fire seem to be in the corner of the room. Maybe the owner will come back soon.");
		
		NPCRoom customRoom2 = new DavidFront("There is a strange figure in the corner. Press 'f' to interact.");
		CaveExplorer.caves[0][4] = customRoom2;		

		NPCRoom customRoom3 = new JasonRoom("There is a mysterious carving in the floor of the room. Press 'f' to interact. Be warned this may do strange things.");
		CaveExplorer.caves[0][1] = customRoom3;

		JasonDavidStartRoom startRoom = new JasonDavidStartRoom("Magic Square");
		CaveExplorer.caves[9][9] = startRoom;
//		CaveExplorer.npcs = new NPC[1];
//		CaveExplorer.npcs[0] = new NPC();
//		CaveExplorer.npcs[0].setposition(9, 2);
		EndGameRoom endgame = new EndGameRoom("Game End");
		
		MiniStarter starterNPC=new MiniStarter("There is a monster moving. Type 'e' to in","You already challenged the monster");
		starterNPC.setposition(3,8);
		CaveExplorer.npcs=new NPC[1];
		CaveExplorer.npcs[0]=starterNPC;
		//4. Set your starting room:
		CaveExplorer.currentRoom = CaveExplorer.caves[9][4];
		CaveExplorer.currentRoom.enter();
		//5. Set up doors
		CaveRoom[][] c = CaveExplorer.caves;

		Door CZRoom = new Door();
		CZRoom.setOpen(false);
		CZRoom.setLocked(false);
		c[3][7].setConnection(EAST, c[3][8], CZRoom);
		c[3][8].setConnection(WEST, c[3][7], CZRoom);
		
		Door giftShop = new Door();
		giftShop.setOpen(false);
		giftShop.setLocked(false);
		c[0][1].setConnection(SOUTH, c[1][1], giftShop);
		c[1][1].setConnection(NORTH, c[0][1], giftShop);
		
		Door lockedDoor = new Door();
		lockedDoor.setOpen(false);
		lockedDoor.setLocked(false);
		c[7][3].setConnection(EAST, c[7][4], lockedDoor);
		c[7][4].setConnection(WEST, c[7][3], lockedDoor);
		
		Door bathroom = new Door();
		bathroom.setOpen(false);
		bathroom.setLocked(true);
		c[9][0].setConnection(EAST, c[9][1], bathroom);
		c[9][1].setConnection(WEST, c[9][0], bathroom);
		
		c[0][2].setConnection(EAST, c[0][3], new Door());
		c[0][3].setConnection(WEST, c[0][2], new Door());
		c[0][3].setConnection(EAST, c[0][4], new Door());
		c[0][4].setConnection(WEST, c[0][3], new Door());
		c[0][4].setConnection(EAST, c[0][5], new Door());
		c[0][5].setConnection(WEST, c[0][4], new Door());
		c[0][5].setConnection(EAST, c[0][6], new Door());
		c[0][6].setConnection(WEST, c[0][5], new Door());
		c[0][6].setConnection(EAST, c[0][7], new Door());
		c[0][7].setConnection(WEST, c[0][6], new Door());
		c[0][7].setConnection(EAST, c[0][8], new Door());
		c[0][8].setConnection(WEST, c[0][7], new Door());
		c[0][8].setConnection(EAST, c[0][9], new Door());
		c[0][9].setConnection(WEST, c[0][8], new Door());
		
		c[1][0].setConnection(EAST, c[1][1], new Door());
		c[1][1].setConnection(WEST, c[1][0], new Door());
		c[1][2].setConnection(EAST, c[1][3], new Door());
		c[1][3].setConnection(WEST, c[1][2], new Door());
		c[1][3].setConnection(EAST, c[1][4], new Door());
		c[1][4].setConnection(WEST, c[1][3], new Door());
		c[1][4].setConnection(EAST, c[1][5], new Door());
		c[1][5].setConnection(WEST, c[1][4], new Door());
		c[1][5].setConnection(EAST, c[1][6], new Door());
		c[1][6].setConnection(WEST, c[1][5], new Door());
		c[1][6].setConnection(EAST, c[1][7], new Door());
		c[1][7].setConnection(WEST, c[1][6], new Door());
		c[1][7].setConnection(EAST, c[1][8], new Door());
		c[1][8].setConnection(WEST, c[1][7], new Door());
		c[1][8].setConnection(EAST, c[1][9], new Door());
		c[1][9].setConnection(WEST, c[1][8], new Door());
		
		c[2][0].setConnection(EAST, c[2][1], new Door());
		c[2][1].setConnection(WEST, c[2][0], new Door());
		c[2][1].setConnection(EAST, c[2][2], new Door());
		c[2][2].setConnection(WEST, c[2][1], new Door()); 
		c[2][2].setConnection(EAST, c[2][3], new Door());
		c[2][3].setConnection(WEST, c[2][2], new Door());
		c[2][3].setConnection(EAST, c[2][4], new Door());
		c[2][4].setConnection(WEST, c[2][3], new Door());
		c[2][4].setConnection(EAST, c[2][5], new Door());
		c[2][5].setConnection(WEST, c[2][4], new Door());
		c[2][5].setConnection(EAST, c[2][6], new Door());
		c[2][6].setConnection(WEST, c[2][5], new Door());
		c[2][7].setConnection(EAST, c[2][8], new Door());
		c[2][8].setConnection(WEST, c[2][7], new Door());
		c[2][8].setConnection(EAST, c[2][9], new Door());
		c[2][9].setConnection(WEST, c[2][8], new Door());
		
		c[3][0].setConnection(EAST, c[3][1], new Door());
		c[3][1].setConnection(WEST, c[3][0], new Door());
		c[3][1].setConnection(EAST, c[3][2], new Door());
		c[3][2].setConnection(WEST, c[3][1], new Door()); 
		c[3][2].setConnection(EAST, c[3][3], new Door());
		c[3][3].setConnection(WEST, c[3][2], new Door());
		c[3][3].setConnection(EAST, c[3][4], new Door());
		c[3][4].setConnection(WEST, c[3][3], new Door());
		c[3][4].setConnection(EAST, c[3][5], new Door());
		c[3][5].setConnection(WEST, c[3][4], new Door());
		c[3][5].setConnection(EAST, c[3][6], new Door());
		c[3][6].setConnection(WEST, c[3][5], new Door());
		c[3][6].setConnection(EAST, c[3][7], new Door());
		c[3][7].setConnection(WEST, c[3][6], new Door());
		c[3][7].setConnection(EAST, c[3][8], new Door());
		c[3][8].setConnection(WEST, c[3][7], new Door());
		c[3][8].setConnection(EAST, c[3][9], new Door());
		c[3][9].setConnection(WEST, c[3][8], new Door());
		
		c[4][0].setConnection(EAST, c[4][1], new Door());
		c[4][1].setConnection(WEST, c[4][0], new Door());
		c[4][1].setConnection(EAST, c[4][2], new Door());
		c[4][2].setConnection(WEST, c[4][1], new Door()); 
		c[4][2].setConnection(EAST, c[4][3], new Door());
		c[4][3].setConnection(WEST, c[4][2], new Door());
		c[4][3].setConnection(EAST, c[4][4], new Door());
		c[4][4].setConnection(WEST, c[4][3], new Door());
		c[4][4].setConnection(EAST, c[4][5], new Door());
		c[4][5].setConnection(WEST, c[4][4], new Door());
		c[4][5].setConnection(EAST, c[4][6], new Door());
		c[4][6].setConnection(WEST, c[4][5], new Door());
		c[4][7].setConnection(EAST, c[4][8], new Door());
		c[4][8].setConnection(WEST, c[4][7], new Door());
		c[4][8].setConnection(EAST, c[4][9], new Door());
		c[4][9].setConnection(WEST, c[4][8], new Door());
		
		c[5][0].setConnection(EAST, c[5][1], new Door());
		c[5][1].setConnection(WEST, c[5][0], new Door());
		c[5][1].setConnection(EAST, c[5][2], new Door());
		c[5][2].setConnection(WEST, c[5][1], new Door()); 
		c[5][2].setConnection(EAST, c[5][3], new Door());
		c[5][3].setConnection(WEST, c[5][2], new Door());
		c[5][3].setConnection(EAST, c[5][4], new Door());
		c[5][4].setConnection(WEST, c[5][3], new Door());
		c[5][4].setConnection(EAST, c[5][5], new Door());
		c[5][5].setConnection(WEST, c[5][4], new Door());
		c[5][5].setConnection(EAST, c[5][6], new Door());
		c[5][6].setConnection(WEST, c[5][5], new Door());
		c[5][6].setConnection(EAST, c[5][7], new Door());
		c[5][7].setConnection(WEST, c[5][6], new Door());
		c[5][7].setConnection(EAST, c[5][8], new Door());
		c[5][8].setConnection(WEST, c[5][7], new Door());
		c[5][8].setConnection(EAST, c[5][9], new Door());
		c[5][9].setConnection(WEST, c[5][8], new Door());
		
		c[6][4].setConnection(EAST, c[6][5], new Door());
		c[6][5].setConnection(WEST, c[6][4], new Door());
		c[6][5].setConnection(EAST, c[6][6], new Door());
		c[6][6].setConnection(WEST, c[6][6], new Door());
		
		c[7][3].setConnection(EAST, c[7][4], new Door());
		c[7][4].setConnection(WEST, c[7][3], new Door());
		c[7][4].setConnection(EAST, c[7][5], new Door());
		c[7][5].setConnection(WEST, c[7][4], new Door());
		c[7][5].setConnection(EAST, c[7][6], new Door());
		c[7][6].setConnection(WEST, c[7][6], new Door());
		
		c[8][4].setConnection(EAST, c[8][5], new Door());
		c[8][5].setConnection(WEST, c[8][4], new Door());
		c[8][5].setConnection(EAST, c[8][6], new Door());
		c[8][6].setConnection(WEST, c[8][6], new Door());
		
		c[9][4].setConnection(EAST, c[9][5], new Door());
		c[9][5].setConnection(WEST, c[9][4], new Door());
		c[9][5].setConnection(EAST, c[9][6], new Door());
		c[9][6].setConnection(WEST, c[9][6], new Door());
		
		c[2][0].setConnection(SOUTH, c[3][0], new Door());
		c[3][0].setConnection(NORTH, c[2][0], new Door());
		c[3][0].setConnection(SOUTH, c[4][0], new Door());
		c[4][0].setConnection(NORTH, c[3][0], new Door());
		c[4][0].setConnection(SOUTH, c[5][0], new Door());
		c[5][0].setConnection(NORTH, c[4][0], new Door());
		
		c[0][1].setConnection(SOUTH, c[1][1], new Door());
		c[1][1].setConnection(NORTH, c[0][1], new Door());
		c[2][1].setConnection(SOUTH, c[3][1], new Door());
		c[3][1].setConnection(NORTH, c[2][1], new Door());
		c[3][1].setConnection(SOUTH, c[4][1], new Door());
		c[4][1].setConnection(NORTH, c[3][1], new Door());
		c[4][1].setConnection(SOUTH, c[5][1], new Door());
		c[5][1].setConnection(NORTH, c[4][1], new Door());
		
		c[0][2].setConnection(SOUTH, c[1][2], new Door());
		c[1][2].setConnection(NORTH, c[0][2], new Door());
		c[1][2].setConnection(SOUTH, c[2][2], new Door());
		c[2][2].setConnection(NORTH, c[1][2], new Door());
		c[2][2].setConnection(SOUTH, c[3][2], new Door());
		c[3][2].setConnection(NORTH, c[2][2], new Door());
		c[3][2].setConnection(SOUTH, c[4][2], new Door());
		c[4][2].setConnection(NORTH, c[3][2], new Door());
		c[4][2].setConnection(SOUTH, c[5][2], new Door());
		c[5][2].setConnection(NORTH, c[4][2], new Door());
		
		c[0][3].setConnection(SOUTH, c[1][3], new Door());
		c[1][3].setConnection(NORTH, c[0][3], new Door());
		c[1][3].setConnection(SOUTH, c[2][3], new Door());
		c[2][3].setConnection(NORTH, c[1][3], new Door());
		c[2][3].setConnection(SOUTH, c[3][3], new Door());
		c[3][3].setConnection(NORTH, c[2][3], new Door());
		c[3][3].setConnection(SOUTH, c[4][3], new Door());
		c[4][3].setConnection(NORTH, c[3][3], new Door());
		c[4][3].setConnection(SOUTH, c[5][3], new Door());
		c[5][3].setConnection(NORTH, c[4][3], new Door());
		
		c[0][4].setConnection(SOUTH, c[1][4], new Door());
		c[1][4].setConnection(NORTH, c[0][4], new Door());
		c[1][4].setConnection(SOUTH, c[2][4], new Door());
		c[2][4].setConnection(NORTH, c[1][4], new Door());
		c[2][4].setConnection(SOUTH, c[3][4], new Door());
		c[3][4].setConnection(NORTH, c[2][4], new Door());		
		c[3][4].setConnection(SOUTH, c[4][4], new Door());
		c[4][4].setConnection(NORTH, c[3][4], new Door());
		c[4][4].setConnection(SOUTH, c[5][4], new Door());
		c[5][4].setConnection(NORTH, c[4][4], new Door());
		c[5][4].setConnection(SOUTH, c[6][4], new Door());
		c[6][4].setConnection(NORTH, c[5][4], new Door());
		c[6][4].setConnection(SOUTH, c[7][4], new Door());
		c[7][4].setConnection(NORTH, c[6][4], new Door());
		c[7][4].setConnection(SOUTH, c[8][4], new Door());
		c[8][4].setConnection(NORTH, c[7][4], new Door());
		c[8][4].setConnection(SOUTH, c[9][4], new Door());
		c[9][4].setConnection(NORTH, c[8][4], new Door());
		
		c[0][5].setConnection(SOUTH, c[1][5], new Door());
		c[1][5].setConnection(NORTH, c[0][5], new Door());
		c[1][5].setConnection(SOUTH, c[2][5], new Door());
		c[2][5].setConnection(NORTH, c[1][5], new Door());
		c[2][5].setConnection(SOUTH, c[3][5], new Door());
		c[3][5].setConnection(NORTH, c[2][5], new Door());		
		c[3][5].setConnection(SOUTH, c[4][5], new Door());
		c[4][5].setConnection(NORTH, c[3][5], new Door());
		c[4][5].setConnection(SOUTH, c[5][5], new Door());
		c[5][5].setConnection(NORTH, c[4][5], new Door());
		c[5][5].setConnection(SOUTH, c[6][5], new Door());
		c[6][5].setConnection(NORTH, c[5][5], new Door());
		c[6][5].setConnection(SOUTH, c[7][5], new Door());
		c[7][5].setConnection(NORTH, c[6][5], new Door());
		c[7][5].setConnection(SOUTH, c[8][5], new Door());
		c[8][5].setConnection(NORTH, c[7][5], new Door());
		c[8][5].setConnection(SOUTH, c[9][5], new Door());
		c[9][5].setConnection(NORTH, c[8][5], new Door());
		
		c[0][6].setConnection(SOUTH, c[1][6], new Door());
		c[1][6].setConnection(NORTH, c[0][6], new Door());
		c[1][6].setConnection(SOUTH, c[2][6], new Door());
		c[2][6].setConnection(NORTH, c[1][6], new Door());
		c[2][6].setConnection(SOUTH, c[3][6], new Door());
		c[3][6].setConnection(NORTH, c[2][6], new Door());		
		c[3][6].setConnection(SOUTH, c[4][6], new Door());
		c[4][6].setConnection(NORTH, c[3][6], new Door());
		c[4][6].setConnection(SOUTH, c[5][6], new Door());
		c[5][6].setConnection(NORTH, c[4][6], new Door());
		c[5][6].setConnection(SOUTH, c[6][6], new Door());
		c[6][6].setConnection(NORTH, c[5][6], new Door());
		c[6][6].setConnection(SOUTH, c[7][6], new Door());
		c[7][6].setConnection(NORTH, c[6][6], new Door());
		c[7][6].setConnection(SOUTH, c[8][6], new Door());
		c[8][6].setConnection(NORTH, c[7][6], new Door());
		c[8][6].setConnection(SOUTH, c[9][6], new Door());
		c[9][6].setConnection(NORTH, c[8][6], new Door());
		
		c[0][7].setConnection(SOUTH, c[1][7], new Door());
		c[1][7].setConnection(NORTH, c[0][7], new Door());
		c[2][7].setConnection(SOUTH, c[3][7], new Door());
		c[3][7].setConnection(NORTH, c[2][7], new Door());		
		c[3][7].setConnection(SOUTH, c[4][7], new Door());
		c[4][7].setConnection(NORTH, c[3][7], new Door());
		c[6][7].setConnection(SOUTH, c[7][7], new Door());
		c[7][7].setConnection(NORTH, c[6][7], new Door());
		c[7][7].setConnection(SOUTH, c[8][7], new Door());
		c[8][7].setConnection(NORTH, c[7][7], new Door());
		c[8][7].setConnection(SOUTH, c[9][7], new Door());
		c[9][7].setConnection(NORTH, c[8][7], new Door());
		
		c[0][8].setConnection(SOUTH, c[1][8], new Door());
		c[1][8].setConnection(NORTH, c[0][8], new Door());
		c[2][8].setConnection(SOUTH, c[3][8], new Door());
		c[3][8].setConnection(NORTH, c[2][8], new Door());		
		c[3][8].setConnection(SOUTH, c[4][8], new Door());
		c[4][8].setConnection(NORTH, c[3][8], new Door());
		c[6][8].setConnection(SOUTH, c[7][8], new Door());
		c[7][8].setConnection(NORTH, c[6][8], new Door());
		c[7][8].setConnection(SOUTH, c[8][8], new Door());
		c[8][8].setConnection(NORTH, c[7][8], new Door());
		c[8][8].setConnection(SOUTH, c[9][8], new Door());
		c[9][8].setConnection(NORTH, c[8][8], new Door());
		
		c[0][9].setConnection(SOUTH, c[1][9], new Door());
		c[1][9].setConnection(NORTH, c[0][9], new Door());
		c[2][9].setConnection(SOUTH, c[3][9], new Door());
		c[3][9].setConnection(NORTH, c[2][9], new Door());		
		c[3][9].setConnection(SOUTH, c[4][9], new Door());
		c[4][9].setConnection(NORTH, c[3][9], new Door());
		c[6][9].setConnection(SOUTH, c[7][9], new Door());
		c[7][9].setConnection(NORTH, c[6][9], new Door());
		c[7][9].setConnection(SOUTH, c[8][9], new Door());
		c[8][9].setConnection(NORTH, c[7][9], new Door());
		c[8][9].setConnection(SOUTH, c[9][9], new Door());
		c[9][9].setConnection(NORTH, c[8][9], new Door());
		
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
		
		c[8][2].setConnection(WEST, c[8][1], new Door());
			
		c[8][1].setConnection(EAST, c[8][2], new Door());
		c[8][2].setConnection(WEST, c[8][1], new Door());
		
		c[6][2].setConnection(EAST, c[6][3], new Door());
		c[6][3].setConnection(WEST, c[6][2], new Door());
		
		c[8][2].setConnection(EAST, c[8][3], new Door());
		c[8][3].setConnection(WEST, c[8][2], new Door());
		
		c[9][1].setConnection(EAST, c[9][2], new Door());
		c[9][2].setConnection(WEST, c[9][1], new Door());
		
		c[6][7].setConnection(WEST, c[6][6], new Door());
		c[6][6].setConnection(EAST, c[6][7], new Door());
		
		c[6][8].setConnection(WEST, c[8][6], new Door());
		c[6][7].setConnection(EAST, c[6][8], new Door());
		
		c[6][9].setConnection(WEST, c[6][8], new Door());
		c[6][8].setConnection(EAST, c[6][9], new Door());
		
		c[6][8].setConnection(SOUTH, c[7][8], new Door());
		c[7][8].setConnection(NORTH, c[6][8], new Door());
		
		c[6][7].setConnection(SOUTH, c[7][7], new Door());
		c[7][7].setConnection(NORTH, c[6][7], new Door());
		
		c[6][9].setConnection(SOUTH, c[7][9], new Door());
		c[7][9].setConnection(NORTH, c[6][9], new Door());
		
		
		c[7][8].setConnection(WEST, c[7][7], new Door());
		c[7][7].setConnection(EAST, c[7][8], new Door());
		
		c[7][9].setConnection(WEST, c[7][8], new Door());
		c[7][8].setConnection(EAST, c[7][9], new Door());
		
		c[8][8].setConnection(WEST, c[8][7], new Door());
		c[8][7].setConnection(EAST, c[8][8], new Door());
		
		c[8][9].setConnection(WEST, c[8][8], new Door());
		c[8][8].setConnection(EAST, c[8][9], new Door());
		
		c[9][2].setConnection(EAST, c[9][3], new Door());
		c[9][3].setConnection(WEST, c[9][2], new Door());
		
		c[9][3].setConnection(EAST, c[9][4], new Door());
		c[9][4].setConnection(WEST, c[9][3], new Door());
		
		//room on bottom left
		c[9][7].setConnection(EAST, c[9][8], new Door());
		c[9][8].setConnection(WEST, c[9][7], new Door());
		c[9][8].setConnection(EAST, c[9][9], new Door());
		c[9][9].setConnection(WEST, c[9][8], new Door());
		c[9][7].setConnection(EAST, c[9][8], new Door());
		c[9][8].setConnection(WEST, c[9][7], new Door());
		
		c[7][2].setConnection(WEST, c[7][1], new Door());
		c[7][1].setConnection(EAST, c[7][2], new Door());
		
		c[7][2].setConnection(EAST, c[7][3], new Door());
		c[7][3].setConnection(WEST, c[7][2], new Door());
		
		c[0][0].setConnection(EAST, c[0][1], new Door());
		c[0][1].setConnection(WEST, c[0][0], new Door());
		
		c[0][0].setConnection(SOUTH, c[1][0], new Door());
		c[1][0].setConnection(NORTH, c[0][0], new Door());
		/** 
		 * Special requests:
		 * moving objects in caves
		 * what happens when you lose?
		 * can another object move toward you?
		 */	
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
	
	public static void unlockRoom(Door door) {
		door.setOpen(true);
		door.setLocked(false);
	}

}
