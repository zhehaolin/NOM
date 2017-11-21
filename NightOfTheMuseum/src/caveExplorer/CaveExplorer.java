package caveExplorer;

import java.util.Scanner;

public class CaveExplorer {

	public static CaveRoom[][] caves;//every room in the cave
	public static Scanner in;//for user input
	public static CaveRoom currentRoom;//changes based on how the user navigated
	public static Inventory inventory;//where all objects found in cave are kept
	public static boolean playing = true;
	public static NPC[] npcs;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		CaveRoom.setUpCaves();
		//npcs[0] = new NPC();

		inventory = new Inventory();
		startExploring();
	}


	private static void startExploring() {
		while(playing) {
			npcActions();
			print(inventory.getDescription());
			print(currentRoom.getDescription());
			CaveExplorer.print("You have " + Inventory.getHp() + " Hp.");
			CaveExplorer.print("You have " + Inventory.getGold() + " gold.");
			print("What would you like to do?");
			String input = in.nextLine();
			currentRoom.interpretInput(input);
		}
	}

	private static void npcActions() {
		if(npcs != null) {
			for(NPC n: npcs) {
				if(n!=null) {
					n.act();
				}

			}
		}
		inventory.updateMap();
	} 

	public static void print(String s) {
		//NOTE: later, you can replace this line with the more sophistocated "multiLinePrint" from Chatbot
		System.out.println(s);
	} 
}