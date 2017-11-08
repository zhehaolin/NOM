package cobyZhehao;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.Inventory;
import caveExplorer.NPC;
import caveExplorer.NPCRoom;

public class CobyRoom extends CaveRoom{

	private String description;

	public CobyRoom(String description) {
		super(description);
		this.description = "There is a statue in front of you.";
	}

	public void performAction(int direction) {
		if(direction == 5) {
			CaveExplorer.print("You look behind the statue. You find a sandwich!");
			if(CaveExplorer.inventory.isHealthy() == true) {
				CaveExplorer.print("You're hp is full so you decide to leave it there.");
			}else {
				CaveExplorer.print("You ate the sandwich and gained 10 hp!");
			}
		} else {
			super.performAction(direction);
		}
	}
	
	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's', or 'd' to move or you can type 'e' to talk. Or you could press 'q' to view the statue.");
	}
	
	public String validKeys() {
		return "wdsaeq";
	}
	
	public String getContents() {
		return "C";
	}
	
	public String getDescription() {
		return this.description;
	}
}
