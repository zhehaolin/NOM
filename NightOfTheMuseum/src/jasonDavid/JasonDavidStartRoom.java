package jasonDavid;
import caveExplorer.CaveRoom;

public class JasonDavidStartRoom extends CaveRoom{

	public JasonDavidStartRoom(String description) {
		super(description);
	}
	
	public void enter() {
		super.enter();
		DavidFrontEnd.main(null);
		//s
	}

}
