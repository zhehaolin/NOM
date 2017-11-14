package victorRemington;

public class VictorBackEnd implements RemingtonSupport{

	private RemingtonSupport frontend;
	private VictorRemingtonPlot[][] minefield;
	private int bombs; 
	
	public VictorBackEnd(RemingtonSupport frontend) {
		this.frontend = frontend;
		minefield = new VictorRemingtonPlot[8][8];
		bombs = 11;
	}
	
	public void populateMinefield() {
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				minefield[row][col] = new VictorRemingtonPlot(row, col);
			}
		}
		setUpBombs();
	}
	
	public static int getBombs() {
		return 0;
	}
	
	public void setUpBombs() {
		while(bombs > 0) {
			int row = (int)(Math.random() * 8);
			int col = (int)(Math.random() * 8);
			while(minefield[row][col].hasBomb()) {
				row = (int)(Math.random() * 8);
				col = (int)(Math.random() * 8);
			}
			minefield[row][col].setContents("bomb");
			minefield[row][col].setHasBomb(true);
			bombs--;
		}
	}
	
	public boolean checkAdjacencies() {
		return false;
	}
	
	public boolean checkBoundaries() {
		return false;
	}
	
	public boolean checkValidinput() {
		return false;
	}
	
	public String victoryStatement() {
		return "You won!";
	}
}
