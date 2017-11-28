package jasonDavid;
import caveExplorer.NPC;
import caveExplorer.CaveExplorer;
import caveExplorer.Inventory;


public class JasonNPC extends NPC{
	 
	public void interact() {
		System.out.println("Have you heard of Magic Square? Lets play it right now!");
		CaveExplorer.currentRoom.leave();
		CaveExplorer.currentRoom = CaveExplorer.caves[9][9];
		CaveExplorer.currentRoom.enter();
	}
}

