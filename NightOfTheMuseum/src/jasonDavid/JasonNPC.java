package jasonDavid;
import caveExplorer.NPC;
import caveExplorer.CaveExplorer;
import caveExplorer.Inventory;


public class JasonNPC extends NPC{
	private static final String[] responses = {"Get robbed!", "I'm feeling generous"};
	 
	public void interact() {
		int ranNum = JasonRoom.randomNum(0, 1);
		CaveExplorer.print(responses[ranNum]);
		chooseMove(ranNum);
	}

	public void chooseMove(int num) {
		if (num == 0) {
			System.out.println("You were too broke to be robbed.");
		}
		else if (num==1) {
			System.out.println("It was garbage anyways.");
		}
	}
}

