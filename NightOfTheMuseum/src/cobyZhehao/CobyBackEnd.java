package cobyZhehao;

import caveExplorer.CaveExplorer;

public class CobyBackEnd implements ZhehaoSupport{

	private CobySupport frontend;
	private int[] randNums;
	private int[][] answer= {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15}};
	private String[][] starting = {{"1","5","10","9"},{"15"," ","4","14"},{"12","2","8","13"},{"11","7","3","6"}};
	private int[] blankSpot;
	private ZhehaoCobyPlot[][] plots;
	
	public CobyBackEnd(CobySupport frontend) {
		this.frontend = frontend;
		plots = new ZhehaoCobyPlot[4][4];
		createPlots();
	}

	private void createPlots() {
		for(int row = 0; row < plots.length; row++) {
			for(int col = 0; col < plots[row].length; col++) {
				plots[row][col] = new ZhehaoCobyPlot(row, col);
			}
		}
		randNums = new int[15];
		for(int i = 1; i < randNums.length; i++) {
			randNums[i-1] = i;
		}
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				plots[i][j].setContents(starting[i][j]);
				plots[i][j].setAnswer(starting[i][j]);
				if(plots[i][j].equals("0")) {
					plots[i][j] = null;
					blankSpot[0] = i;
					blankSpot[1] = j;
				}
			}
		}
	}
	
	public boolean gameover() {
		for(int i=0;i<answer.length;i++) {
			for(int j=0;j<answer[i].length; j++) {
				if(plots[i][j].getContents() != plots[i][j].getAnswer()) {
					return false;
				}
			}
		}
		return true;
	}

	private int[] findCoords(String input) {
		int[] coords = {};
		int a = Integer.parseInt(input.substring(0,1));
		int b = Integer.parseInt(input.substring(2,3));
		coords[0] = a;
		coords[1] = b;
		return coords;
	}

	public boolean validCoords(String input) {
		if(input.substring(1,2).equals(",") && input.length() == 3){
			if(input.substring(0,1) == "0" || input.substring(0,1) == "1" || input.substring(0,1) == "2" || input.substring(0,1) == "3") {
				if(input.substring(2,3) == "0" || input.substring(2,3) == "1" || input.substring(2,3) == "2" || input.substring(2,3) == "3") {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isNextToBlank(ZhehaoCobyPlot p) {
		int oneUp = p.getRow()-1;
		int oneDown = p.getRow()+1;
		int oneLeft = p.getCol()-1;
		int oneRight = p.getCol()+1;
		if(oneUp < 0) {
			oneUp = 0;
		}
		if(oneDown > 3) {
			oneDown = 3;
		}
		if(oneLeft < 0) {
			oneLeft = 0;
		}
		if(oneRight > 3) {
			oneRight = 3;
		}
		if(oneUp == blankSpot[0] || oneDown == blankSpot[0] || oneLeft == blankSpot[1] || oneRight == blankSpot[1]) {
			return true;
		}
		return false;
	}
	
	public ZhehaoCobyPlot[][] getPlots() {
		return plots;
	}

	@Override
	public int[] getCoordInput() {
		int[] coords = null;
		String input = CaveExplorer.in.nextLine();
		if(validCoords(input)) {
			coords = findCoords(input);
		}
		return coords;
	}

	@Override
	public void move(ZhehaoCobyPlot p) {
		if(isNextToBlank(p)) {
			moveIntoBlank(p);
		} 
	}
	
	//Coords is the coordinates of the new spot being turned into a blank
	public void moveIntoBlank(ZhehaoCobyPlot p) {
		String switchingNum = p.getContents();
		plots[blankSpot[0]][blankSpot[1]].setContents(switchingNum);
		p.setContents(null);
		blankSpot[0] = p.getRow();
		blankSpot[1] = p.getCol();
	}

}
