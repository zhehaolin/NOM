package victorRemington;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.NPC;

public class RemingtonFrontEnd extends CaveRoom {
	
	private NPC presentNPC;

	public RemingtonFrontEnd(String description) {
		super(description);
		
	}
	
	public String validKeys() {
		return "wdsai";
	}
	
	public void printAllowedEntry() {
		CaveExplorer.print("You can only enter 'w', 'a', 's' or 'd' to move or"
				+ " you can type 'i' for information.");
	}
	
	public boolean containsNPC() {
		return presentNPC != null;
	}
	
	
	public void performAction(int direction) {
		super.performAction(direction);
		if(direction == 4) {
			if(containsNPC() && presentNPC.isActive()) {
				presentNPC.interact();
				CaveExplorer.print("Isn't the museum beautiful??");
			}else {
				CaveExplorer.print("There is nothing to interact with.");
			}
		}else {
			CaveExplorer.print("That key does nothing.");
		}
	}
	
	public String getContents() {
		if(containsNPC() && presentNPC.isActive()) {
			return "M";
		}else {
			//return what would be returned otherwise
			return super.getContents();
		}
	}
	
	public String getDescription() {
		if(containsNPC() && !presentNPC.isActive()) {
			return super.getDescription() +"\n"+presentNPC.getInactiveDescription();
		}else {
			String npcDesc = "";
			if(presentNPC != null) {
				npcDesc = presentNPC.getActiveDescription();
			}
			return super.getDescription() + "\n"+npcDesc;
		}
	}
}
