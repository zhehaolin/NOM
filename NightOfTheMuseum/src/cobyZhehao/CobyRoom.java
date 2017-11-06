package cobyZhehao;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.NPC;
import caveExplorer.NPCRoom;

public class CobyRoom extends CaveRoom{

	private String description;

	public CobyRoom(String description) {
		super(description);
		this.description = "There is a statue in front of you.";
		setDirections();
	}

	public void performAction(int direction) {
		if(direction == 5) {
			CaveExplorer.print("A statue falls on top of your leg! You lose 10 hp!");
			
		} else {
			super.performAction(direction);
		}
	}
	
	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's', or 'd' to move or you can type 'e' to talk. Or you could press 'q' to touch the statue.");
	}
	
	public String validKeys() {
		return "wdsaeq";
	}
	
	public String getContents() {
		return "C";
	}
	
	public String getDescription() {
		return description + "\n"+super.directions;
	}
}
