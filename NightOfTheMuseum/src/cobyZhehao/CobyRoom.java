package cobyZhehao;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.Inventory;

public class CobyRoom extends CaveRoom{

	private boolean isWiped = false;

	public CobyRoom(String description) {
		super(description);
	}

	public void performAction(int direction) {
		boolean used = false;
		if(direction == 4) {
			if(isWiped) {
				CaveExplorer.print("The sign says 'Bring 3 keys ..' the rest is not readable.");
			} else {
				CaveExplorer.print("You wipe the filth off the sign. You see some words on the sign. Press 'f' to read the sign.");
				isWiped = true;
			}
		} else {
			super.performAction(direction);
		}
	}
	
	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's', or 'd' to move or you could press 'f' to interact.");
	}
	
	public String validKeys() {
		return "wdsaf";
	}
	
	public String getContents() {
		return "C";
	}
}
