package jasonDavid;
import caveExplorer.NPC;
import caveExplorer.CaveExplorer;
import caveExplorer.Inventory;


public class JasonNPC extends NPC{
	
	public void interact() {
		CaveExplorer.print("Haha!");
		Inventory.changeGold(-500);
	}
}