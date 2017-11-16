package caveExplorer;

public class Inventory {

	private String map;
	
	private static int hp;
	private static int gold;

	private boolean gloveofpp;
	private boolean Strangekey;
	private boolean hasKey;
	
	public Inventory() {
		updateMap();
		hasKey = false;
		hp = 100;
		gold = 5000;
		Strangekey = false;
		gloveofpp = false;
	}

	public static int getHp() {
		return hp;
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
				}//last caveroom in row
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
	public void StrangeKeyObtained() {
		Strangekey = true;
	}
	public boolean haveStrangeKey() {
		return Strangekey;
	}
	public static void changeHP(int change) {
		hp += change;
		if(hp > 100) {
			hp = 100;
		}
		if(hp < 0) {
			hp = 0;
		}
	}
	
	public static void changeGold(int change) {
		gold += change;
	}

	public static int getGold() {
		return gold;
	}
	
	public boolean isHealthy() {
		if(hp == 100) {
			return true;
		}
		return false;
	}
	
	public void gotkey() {
		hasKey = true;
	}
}
