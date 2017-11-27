package victorRemington;
import caveExplorer.CaveRoom;

public class VictorRemingtonPuzzleRoom extends CaveRoom{
	public VictorRemingtonPuzzleRoom(String description) {
		super(description);
	}
	public String getContents() {
		return "J";
	}
	public void enter() {
		super.enter();
		RemingtonFrontEnd.main(null);		
	}
}
