package cobyZhehao;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.Inventory;

public class CobyRoom extends CaveRoom{

	private boolean isEaten = false;

	public CobyRoom(String description) {
		super(description);
	}

	public void performAction(int direction) {
		if(direction == 4) {
			if(isEaten) {
				CaveExplorer.print("You find a sandwich wrapper at the base of the statue.");
			} else {
				CaveExplorer.print("You look behind the statue. You find a sandwich!");
				if(CaveExplorer.inventory.isHealthy() == true) {
					CaveExplorer.print("You're hp is full so you decide to leave it there.");
				}else {
					CaveExplorer.print("You ate the sandwich and gained 10 hp!");
					Inventory.changeHP(10);
					isEaten = true;
				}
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
