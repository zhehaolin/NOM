package victorRemington;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.Inventory;
import caveExplorer.NPC;
import caveExplorer.NPCRoom;

public class RemingtonFrontEnd extends CaveRoom{

	private String description;

	public RemingtonFrontEnd(String description) {
		super(description);
		this.description = "You see a couple of items scattered across the room.";
	}

	public void performAction(int direction) {
		if(direction == 4) {
			CaveExplorer.print("You pick up the item that's closest to you.");
			CaveExplorer.print("The picture frightens you and you lose 10HP!.");
			Inventory.changeHP(-10);
		} else {
			super.performAction(direction);
		}
	}
	
	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's', or 'd' to move or you can type 'i' to interact.");
	}
	
	public String validKeys() {
		return "wdsai";
	}
	
	public String getContents() {
		return "C";
	}
	
	public String getDescription() {
		return this.description;
	}
}
