package jasonDavid;
import caveExplorer.CaveExplorer;
import caveExplorer.NPC;

public class JasonNPC extends NPC{
	
	public void interact() {
		CaveExplorer.print("Haha!");
		CaveExplorer.inventory.loseMoney(500);
	}
}