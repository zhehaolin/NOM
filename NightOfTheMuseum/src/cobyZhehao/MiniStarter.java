package cobyZhehao;

import caveExplorer.NPC;

public class MiniStarter extends NPC {
	private String activeDescription;
	private String inactiveDescription;

	public MiniStarter(String activeDescription, String inactiveDescription) {
		this.activeDescription = activeDescription;
		this.inactiveDescription = inactiveDescription;
	}
	public void interact() {
		ZhehaoFrontEnd.main(null);
	}
	public String getInactiveDescription() {
		return inactiveDescription;
	}

	public String getActiveDescription() {
		return activeDescription;
	}



}
