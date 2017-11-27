package jasonDavid;
import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.NPC;
import caveExplorer.NPCRoom;

public class JasonRoom extends NPCRoom{
	
	public static JasonNPC jason = new JasonNPC();
	
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
			teleport(randomNum(0,9),randomNum(0,9));
		}else {
			if (direction == 5){                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
				{
					CaveExplorer.print("hi");
					jason.interact();
				}	
			}else {
				CaveExplorer.print(":)");
				super.performAction(direction);
			}
		}
	}
	public static int randomNum(int min, int max) {
		return (int)(Math.random() *(max-min+1)) + min;
	}
	public void teleport(int num1, int num2) {
		CaveExplorer.print("You have been teleported!)");
		CaveExplorer.currentRoom.leave();
		CaveExplorer.currentRoom = CaveExplorer.caves[num1][num2];
		CaveExplorer.currentRoom.enter();
	}
	public String getContents() {
		return "J";
	}
}
