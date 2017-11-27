package cobyZhehao;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.Door;
import caveExplorer.Inventory;

public class keyRoom extends CaveRoom{
	
	public keyRoom(String description) {
		super(description);
		
	}
	
	public void performAction(int direction) {
		if(direction == 4) {
			if(Inventory.getKeys() == 3) {
				CaveRoom.unlockRoom(bathroom);
				CaveExplorer.print("You put one of your keys into each slots. The door opens in front of you!");
			} else {
				CaveExplorer.print("You find 3 keys slots in the door.");
			}
		} else {
			super.performAction(direction);
		}
	}
}