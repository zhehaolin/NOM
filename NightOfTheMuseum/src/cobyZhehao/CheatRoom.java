package cobyZhehao;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.Door;
import caveExplorer.Inventory;

public class CheatRoom extends CaveRoom{
	
	public CheatRoom(String description) {
		super(description);
	}
	
	public void performAction(int direction) {
		if(direction == 4) {
			if(Inventory.getKeys() < 3) {
				while(Inventory.getKeys() != 3) {
					Inventory.Obtainkeys();
				}
				CaveExplorer.print("You get all of the keys you need.");
			}
		} else {
			super.performAction(direction);
		}
	}
	public String getContents() {
		return "3";
	}
}