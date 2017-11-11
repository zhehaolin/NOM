package jasonDavid;
import caveExplorer.NPC;
import caveExplorer.CaveExplorer;
import caveExplorer.Inventory;


public class JasonNPC extends NPC{
	private static final String[] responses = {"Get robbed!", "I'm feeling generous"};
	 
	public void interact() {
		int ranNum = JasonRoom.randomNum(0, 1);
		chooseMove(ranNum);
		CaveExplorer.print(responses[ranNum]);
	}
	public void chooseMove(int num) {
		if (num == 0) {
			Inventory.changeGold(-500);
		}
		else if (num==1) {
			Inventory.changeGold(500);
		}
	}
}