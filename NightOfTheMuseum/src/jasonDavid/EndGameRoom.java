package jasonDavid;
import caveExplorer.CaveRoom;

public class EndGameRoom extends CaveRoom{

	private String contents;
	public EndGameRoom(String description) {
		super(description);
	}
	public String getContents() {
		return "E";
	}
	public void enter() {
		super.enter();
		FinishGame.main(null);
		
	}
//df
}
