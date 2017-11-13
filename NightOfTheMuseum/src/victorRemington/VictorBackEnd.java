package victorRemington;

public class VictorBackEnd implements RemingtonSupport{

	private RemingtonSupport frontend;
	private VictorRemingtonField[][] minefield;
	private int bombs; 
	
	public VictorBackEnd(RemingtonSupport frontend) {
		this.frontend = frontend;
		bombs = 11;
	}
	
	public void setUpField() {
		//takes initial pos from frontend
	}
	
	public void calculteAdjacentBombs() {
		//sets up numbers
	}
	
	public static int getBombs() {
		return 0;
	}
}
