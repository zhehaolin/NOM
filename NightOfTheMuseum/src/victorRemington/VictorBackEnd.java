package victorRemington;

public class VictorBackEnd implements RemingtonSupport{

	private RemingtonFrontEnd frontend;
	private VictorRemingtonPlot[][] minefield;
	private static int bombs; 
	
	public VictorBackEnd(RemingtonFrontEnd remingtonFrontEnd) {
		this.frontend = remingtonFrontEnd;
		minefield = new VictorRemingtonPlot[8][8];
		bombs = 0;
		populateMinefield();
		setContents();
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
		return bombs;
	}
	
	public void setUpBombs() {
		while(bombs < 12) {
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
	
	public int checkForBomb(int row, int col) {
		if(minefield[row][col].hasBomb()) {
			return 1;
		}
		return 0;
	}
	
	public void setContents() {
		setUpBombs();
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				VictorRemingtonPlot currentPlot = minefield[row][col];
				if(!currentPlot.hasBomb()) {
					currentPlot.setContents(numberOfAdjacentBombs(row, col));	
				}
			}
		}
	}
	
	private String numberOfAdjacentBombs(int row, int col) {
		int count = 0;
		if(row > 0) {
			//N
			count += checkForBomb(row - 1, col);
		}
		
		if(row < minefield.length - 2) {
			//S
			count += checkForBomb(row + 1, col);
		}
		
		if(col > 0) {
			//W
			count += checkForBomb(row, col - 1);
			if(row > 0) {
				//NW
				count += checkForBomb(row - 1, col - 1);
			}
			if(row < minefield.length - 2) {
				//SW
				count += checkForBomb(row + 1, col - 1);
			}
		}
		
		if(col < minefield[0].length - 2) {
			//E
			count += checkForBomb(row, col + 1);
			if(row > 0) {
				//NE
				count += checkForBomb(row - 1, col + 1);
			}
			if(row < minefield.length - 2) {
				//SE
				count += checkForBomb(row + 1, col + 1);
			}
		}
		return Integer.toString(count);
	}

	public boolean checkValidinput(int[] coords) {
		if(minefield[coords[0]][coords[1]].isVisible()) {
			return false;
		}
		return true;
	}
	
	public void changeNumConcealedBombs() {
		//changes number of bombs based on front end input
		//also have seperate var checking how many are actually bombs
		//ensure player cant just flag 11 random tiles and win
	}
	
	public String victoryStatement() {
		return "You won! All of the mines have been cleared!";
	}
}
