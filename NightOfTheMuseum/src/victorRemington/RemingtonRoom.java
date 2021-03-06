package victorRemington;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.Inventory;

public class RemingtonRoom extends CaveRoom{

	private String description;
	private boolean used = false;

	public RemingtonRoom(String description) {
		super(description);
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
