package victorRemington;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.Inventory;

public class VictorRoom extends CaveRoom{

	private boolean triggered;
	private boolean passed;
	private double probability;
	private int damage;
	private String descDisabledTrap;
	private int treasureAmount;
	private String descTrapActivate;
	
	public VictorRoom(String description, double probability, int damage, String descFailedTrap, String descTrapActivate, int treasureAmount) {
		super(description);
		triggered = false;
		passed = false;
		this.probability = probability;
		this.damage = damage;
		this.descDisabledTrap = descFailedTrap;
		this.treasureAmount = treasureAmount;
		this.descTrapActivate = descTrapActivate;
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
				CaveExplorer.print("You got " + treasureAmount + " gold.");
				Inventory.changeGold(treasureAmount);
				this.setDescription("This room has coords 2, 3. " + descDisabledTrap);
			}
		}else {
			System.out.println("That key does nothing");
		}
	}

	public void activateTrap() {
		if(Math.random() < probability && getContents().equals("X") && !triggered && !passed) {
			Inventory.changeHP(damage);
			triggered = true;
			CaveExplorer.print(descTrapActivate);
			this.setDescription("This room has coords 2, 3. The trap has been sprung.");
		}
		passed = true;
	}

}