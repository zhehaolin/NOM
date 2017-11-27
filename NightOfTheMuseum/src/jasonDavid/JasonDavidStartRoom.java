package jasonDavid;
import caveExplorer.CaveRoom;

public class JasonDavidStartRoom extends CaveRoom{

	private String contents;
	public JasonDavidStartRoom(String description) {
		super(description);
	}
	public String getContents() {
		return "J";
	}
	public void enter() {
		super.enter();
		DavidFrontEnd.main(null);
		
	}

}
