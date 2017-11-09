package cobyZhehao;

import caveExplorer.CaveExplorer;
import caveExplorer.Inventory;
import caveExplorer.NPC;
import caveExplorer.NPCRoom;

public class zheRoom extends NPCRoom {
	private NPC presentNPC;
	
	public zheRoom(String description) {
		super(description);
		presentNPC = null;
	}
	public boolean canEnter() {
		return presentNPC == null;
	}
	public void enterNPC(NPC S) {
		presentNPC = S;
	}
	public void leaveNPC() {
		presentNPC = null;
	}
	public boolean containsNPC() {
		return presentNPC !=null;
	}
	public void performAction(int direction) {
		
		if(direction == 4) {
			CaveExplorer.print("This is what is written under the statue: You must collect 3 keys to leave this place. You gain some knowledge, gain 10 hp.");
			Inventory.changeHP(10);
			
		}else if(direction == 5)
		{
			CaveExplorer.print(returnthehp());
		}else {
			super.performAction(direction);
		}
			
	}
	public String returnthehp()
	{
		int number =Inventory.getHp();
		String x="Your current hp is "+ number;
		return x;
	}
	public String validKeys(){
		return "wdsafx";	
	}
	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's', or 'd' to move or you can type 'f' to interact with the statue. Click 'x' to check hp");
	}
	public String getContents() {
		return "S";
	}
	public String getDescription() {
		if(containsNPC() && !presentNPC.isActive()) {
			return "hi";
		}else {
			return "There is a statue in the room. Try to interact.";//hi
		}
	}
	
	
}

