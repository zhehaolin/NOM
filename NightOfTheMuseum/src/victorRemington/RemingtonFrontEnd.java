package victorRemington;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.Inventory;
import caveExplorer.NPC;
import caveExplorer.NPCRoom;

public class RemingtonFrontEnd extends CaveRoom{

	private String description;
	private boolean used = false;

	public RemingtonFrontEnd(String description) {
		super(description);
		this.description = "You see a couple of items scattered across the room.";
	}

	public void performAction(int direction) {
		if(direction == 4) {
			if(!used) {
				CaveExplorer.print("You pick up the item that's closest to you.");
				CaveExplorer.print("The picture frightens you and you lose 10HP!.");
				Inventory.changeHP(-10);
				used = true;
			} else {
				CaveExplorer.print("This is where you got scared.");
			}
		} else {
			super.performAction(direction);
		}
	}
	
	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's', or 'd' to move or you can type 'f' to interact.");
	}
	
	public String validKeys() {
		return "wdsaf";
	}
	
	public String getContents() {
		return "R";
	}
	
	public String getDescription() {
		return this.description;
	}
}
