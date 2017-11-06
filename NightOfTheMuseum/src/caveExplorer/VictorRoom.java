package caveExplorer;

public class VictorRoom extends CaveRoom{

	private boolean triggered;
	private boolean passed;
	
	public VictorRoom(String description) {
		super(description);
		triggered = false;
		passed = false;
	}
	
	public String validKeys() {
		return "wdsax";
	}
	
	public void printAllowedEntry() {
		System.out.println("You can only enter 'w', 'a', 's', 'd', or 'x'.");
	}
	
	public void performAction(int direction) {
		if(direction == 4) {
			activateTrap();
			if(!triggered) {
				CaveExplorer.print("You got 10 gold.");
				Inventory.changeGold(10);
				this.setDescription("This room has coords 2, 3. The box is now empty.");
			}
		}else {
			System.out.println("That key does nothing");
		}
	}

	public void activateTrap() {
		if(Math.random() > 0.5 && getContents().equals("X") && !triggered && !passed) {
			Inventory.changeHP(-10);
			triggered = true;
			CaveExplorer.print("The box was a trap!");
			this.setDescription("This room has coords 2, 3. The trap has been sprung.");
		}
		passed = true;
	}

}
