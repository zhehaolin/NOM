package jasonDavid;
import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.NPC;
import caveExplorer.NPCRoom;

public class JasonRoom extends NPCRoom{
	
	public JasonNPC jason;
	
	public JasonRoom(String description) {
		super(description);
	}
	
	public String validKeys() {
		return "wdsafe";
	}
	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's', or 'd' to move or you can type 'e' to interact. Enter 'f' for a surprise!");
	}
	public void performAction(int direction) {
		if (direction == 4) {
			CaveExplorer.currentRoom.leave();
			CaveExplorer.print("You have been teleported!)");
			CaveExplorer.currentRoom = CaveExplorer.caves[randomNum(0,9)][randomNum(0,9)];
			CaveExplorer.currentRoom.enter();
		}else {
			if (direction == 5){
				if(containsNPC() && jason.isActive()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       ) {
					jason.interact();
				}	
			}else {
				System.out.println("That key does nothing");
			}
		}
	}
	public int randomNum(int min, int max) {
		return (int)(Math.random() *(max-min)) + min;
	}
	
}