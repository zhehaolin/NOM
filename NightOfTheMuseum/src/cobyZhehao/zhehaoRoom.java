package cobyZhehao;

import caveExplorer.CaveExplorer;
import caveExplorer.Inventory;
import caveExplorer.NPC;
import caveExplorer.NPCRoom;

public class zhehaoRoom extends NPCRoom {

	private NPC presentNPC;
	public zhehaoRoom(String description) {
		super(description);
		presentNPC =null;
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
		super.performAction(direction);
		if(direction == 4) {
			CaveExplorer.print("In front of you is the Greek exhibit. It is full of ancient Greek Mathematicians. He tells you that there is a special machine at 5,9.");
		}else {
			
				System.out.println("That key does nothing");
			}
		
	}
	public String validKeys(){
		return "wdsafe";	
	}
	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's', or 'd' to move or you can type 'f' to read.");
	}
	public String getContents() {
		return "Z";
	}
}

