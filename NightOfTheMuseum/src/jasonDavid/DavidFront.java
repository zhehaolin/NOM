package jasonDavid;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.Inventory;




public class DavidFront extends CaveRoom {

	private DavidNPC Strangeman = new DavidNPC();
	
	public DavidFront(String description) {
		super(description);
	}
	public String validKeys() {
		return "wdsaef";
	}
	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's', or 'd' to move, or press 'e' to talk, or if you're real devious, press 'f' to try to pickpocket the stranger.");
	}
	public void performAction(int direction) {
		if (direction == 5) {
			if (CaveExplorer.inventory.haveStrangeKey() == true) {
				System.out.println("The stranger has nothing important left to steal.");
			}
			if(CaveExplorer.inventory.haveGlove() == false) {
				System.out.println("The Stranger tells you about how legends say if you complete three minigames, you can leave the museum.");
			}
			else {
				System.out.println("You successfully pickpocket the stranger with the majestic powers of your gloves. (Strange Key has been obtained)");
				CaveExplorer.inventory.StrangeKeyObtained();
			}
		}else {
			super.performAction(direction);
		}
		
	}
	public String getContents() {
		return "D";
	}
}
