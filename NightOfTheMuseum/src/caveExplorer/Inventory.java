package caveExplorer;

public class Inventory {

	private String map;
	private boolean gloveofpp;
	private int health;
	private boolean Strangekey;
	private int money;
	
	public Inventory() {
		updateMap();
		gloveofpp = false;
		health = 100;
		Strangekey = false;
		money = 5000;
	}

	public void updateMap() {
		map = " ";
		//create line across top:
		for(int i = 0; i < CaveExplorer.caves[0].length -1; i++) {
			map += "____";//4 underscores
		}
		map+= "___\n";//3 underscores, makes the corner look symmetrical
		for(CaveRoom[] row : CaveExplorer.caves) {
			//3 rows of text
			for(int i = 0; i < 3; i++) {
				String text = "";
				for(CaveRoom cr : row) {
					//if door is open, leave open
					if(cr.getDoor(CaveRoom.WEST) != null &&
							cr.getDoor(CaveRoom.WEST).isOpen()) {
						text += " ";
					}else {
						text += "|";
					}
					//contents of room depend on what row this is
					if(i==0) {
						text+="   ";//3 spaces
					}else if(i == 1) {
						text += " "+cr.getContents()+" ";
					}else if(i == 2) {
						//draw space if door to south is open
						if(cr.getDoor(CaveRoom.SOUTH) != null && 
						cr.getDoor(CaveRoom.SOUTH).isOpen()){
							text+="   ";//3 spaces
						}else {
							text += "___";
						}
					}
				}//last cave room in row
				text+="|";
				map += text +"\n";
			}
		}
	}

	public String getDescription() {
		return map;
//		return "You have nothing in your inventory.";
	}
	public boolean haveGlove() {
		return gloveofpp;
	}
	public void loseHealth(int lost) {
		health = lost - 10;
	}
	public void StrangeKeyObtained() {
		Strangekey = true;
	}
	public boolean haveStrangeKey() {
		return Strangekey;
	}

	public void loseMoney(int i) {
		money -= i;
	}
	
	public int returnhp()
	{
		return health;
	}

	public void restoreHp() {
		health = health + 10;
		if(health > 100) {
			health = 100;
		}
	}
	
	public boolean isHealthy() {
		if(health == 100) {
			return true;
		}
		return false;
	}
}
