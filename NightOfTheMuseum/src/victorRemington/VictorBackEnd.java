package victorRemington;

public class VictorBackEnd implements RemingtonSupport{

	private RemingtonFrontEnd frontend;
	private VictorRemingtonPlot[][] minefield;
	private static int bombs; 
	
	private static int correctFlaged;
	private static int flaged;
	
	public VictorBackEnd(RemingtonFrontEnd remingtonFrontEnd) {
		this.frontend = remingtonFrontEnd;
		minefield = new VictorRemingtonPlot[8][8];
		bombs = 0;
		correctFlaged = 0;
		flaged = 0;
		populateMinefield();
	}
	
	public void populateMinefield() {
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				minefield[row][col] = new VictorRemingtonPlot(row, col);
			}
		}
		setContents();
	}
	
	public static int getBombs() {
		return bombs;
	}
	
	public void setUpBombs() {
		while(bombs < 1) {
			int row = (int)(Math.random() * 8);
			int col = (int)(Math.random() * 8);
			while(minefield[row][col].hasBomb()) {
				row = (int)(Math.random() * 8);
				col = (int)(Math.random() * 8);
			}
			minefield[row][col].setContents("B");
			minefield[row][col].setHasBomb(true);
			bombs++;
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
	
	public String numberOfAdjacentBombs(int row, int col) {
		int count = 0;
		if(row > 0) {
			//N
			count += checkForBomb(row - 1, col);
		}
		
		if(row < minefield.length - 1) {
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
			if(row < minefield.length - 1) {
				//SW
				count += checkForBomb(row + 1, col - 1);
			}
		}
		
		if(col < minefield[0].length - 1) {
			//E
			count += checkForBomb(row, col + 1);
			if(row > 0) {
				//NE
				count += checkForBomb(row - 1, col + 1);
			}
			if(row < minefield.length - 1) {
				//SE
				count += checkForBomb(row + 1, col + 1);
			}
		}
		return Integer.toString(count);
	}

	//checks if the space is visible or not or flaged
	public boolean checkValidCoords(int row, int col) {
		if(row >= 0 && row < 8 && col >= 0 && col < 8) {
			return !minefield[row][col].isVisible() || minefield[row][col].isFlaged();
		}
		return false;
	}
	
	public VictorRemingtonPlot[][] getMinefield() {
		return minefield;
	}
	
	public void flag(int[] coords) {
		if(minefield[coords[0]][coords[1]].isFlaged()) {
			minefield[coords[0]][coords[1]].setFlaged(false);
			changeVisibility(coords[0],coords[1], false);
			flaged--;
			if(checkIsFlagedBombs(coords)) {
				correctFlaged--;
			}
		}else {
			if(!minefield[coords[0]][coords[1]].isVisible()) {
				minefield[coords[0]][coords[1]].setFlaged(true);
				changeVisibility(coords[0],coords[1], true);
				flaged++;
				if(checkIsFlagedBombs(coords)) {
					correctFlaged++;
				}
			}
		}
	}
	
	public boolean checkVictory() {
		return (correctFlaged == bombs);
	}
	
	public boolean checkIsFlagedBombs(int[] coords) {
		if(minefield[coords[0]][coords[1]].hasBomb()) {
			return true;
		}
		return false;
	}
	
	public int getFlaged() {
		return flaged;
	}

	public boolean checkValidinput(String s) {
		if(s.equals(frontend.cheatCode)) {
			return true;
		}
		if(s.length() > 5 || s.length() < 3){
			return false;
		}
		
		if(s.length() == 4 && s.substring(0, 1).equals("f") && Character.isDigit(s.charAt(1)) && s.substring(2,3).equals(",") && Character.isDigit(s.charAt(3))  && checkValidCoords(Integer.parseInt(s.substring(1, 2)), Integer.parseInt(s.substring(3, 4)))) {
			return true;
		}
		return Character.isDigit(s.charAt(0)) && s.substring(1,2).equals(",") && Character.isDigit(s.charAt(2)) && checkValidCoords(Integer.parseInt(s.substring(0, 1)), Integer.parseInt(s.substring(2, 3)));
	}

	public int getCorrectFlaged() {
		return correctFlaged;
	}
	
	public void changeVisibility(int row, int col, boolean state) {
		minefield[row][col].setVisible(state);
	}
}
