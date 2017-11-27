package cobyZhehao;

import java.util.Scanner;

import caveExplorer.CaveExplorer;

public class CobyBackEnd implements ZhehaoSupport{

	
	private ZhehaoFrontEnd frontend;
	private int[] randNums;
	private String[][] answer= {{"1","2","3","4"},{"5","6","7","8"},{"9","10","11","12"},{"13","14","15"," "}};
	private String[][] starting = {{"1","5","10","9"},{"15"," ","4","14"},{"12","2","8","13"},{"11","7","3","6"}};
	//private String[][] starting = {{"1","2","3","4"},{"5","6","7","8"},{"9","10","11","12"},{"13","14"," ","15"}};
	private int[] blankSpot = {1,1};
	public ZhehaoCobyPlot[][] plots;
	public static Scanner in;
	
	public CobyBackEnd(ZhehaoFrontEnd frontend) {
		initScanner();
		this.frontend = frontend;
		plots = new ZhehaoCobyPlot[4][4];
		createPlots();
	}

	public static void initScanner() {
		in = new Scanner(System.in);
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
				plots[i][j].setAnswer(answer[i][j]);
			}
		}
	}
	
	public boolean gameover() {
		int rightCount = 0;
		for(int i=0;i<answer.length;i++) {
			for(int j=0;j<answer[i].length; j++) {
				if(!plots[i][j].getContents().equals(plots[i][j].getAnswer())) {
					return false;
				}else {
					rightCount++;
				}
			}
		}
		if(rightCount == 16) {
			return true;
		}
		return false;
	}

	private int[] findCoords(String input) {
		int[] coords = {0,0};
		if(input.substring(0,1).equals("9") && input.substring(2,3).equals("9")) {
			coords[0]=9;
			coords[1]=9;
			return coords;
		
		}else {
			int a = Integer.parseInt(input.substring(0,1));
			int b = Integer.parseInt(input.substring(2,3));
			coords[0] = a;
			coords[1] = b;
			return coords;
		}
	}

	public boolean validCoords(String input) {
		if(input.substring(1,2).equals(",") && input.length() == 3){
			if(input.substring(0,1).equals(blankSpot[0]) && input.substring(2,3).equals(blankSpot[1])){
				return false;
			}
			if(input.substring(0,1).equals("0") || input.substring(0,1).equals("1") || input.substring(0,1).equals("2") || input.substring(0,1).equals("3")) {
				if(input.substring(2,3).equals("0") || input.substring(2,3).equals("1") || input.substring(2,3).equals("2") || input.substring(2,3).equals("3")) {
					return true;
				}
			}
		}
		if(input.substring(0,1).equals("9") && input.substring(2,3).equals("9")) {
			return true;
		}
		return false;
	}
	
	public boolean isNextToBlank(ZhehaoCobyPlot p) {
		int oneUp = p.getRow()-1;
		int oneDown = p.getRow()+1;
		int oneLeft = p.getCol()-1;
		int oneRight = p.getCol()+1;
		if(oneUp == blankSpot[0] && p.getCol() == blankSpot[1]|| oneDown == blankSpot[0] && p.getCol() == blankSpot[1]
				|| oneLeft == blankSpot[1] && p.getRow() == blankSpot[0] || oneRight == blankSpot[1] && p.getRow() == blankSpot[0]) {
			return true;
		}
		return false;
	}
	
	public ZhehaoCobyPlot[][] getPlots() {
		return plots;
	}

	public int[] getCoordInput() {
		int[] coords = null;
		String input = frontend.in.nextLine();
				if(validCoords(input)) {
				coords = findCoords(input);
			}
			return coords;
		
		}
		

	public void move(ZhehaoCobyPlot p) {
		if(isNextToBlank(p)) {
			moveIntoBlank(p);
		} 
	}
	
	//Coords is the coordinates of the new spot being turned into a blank
	public void moveIntoBlank(ZhehaoCobyPlot p) {
		plots[blankSpot[0]][blankSpot[1]].setContents(p.getContents());
		p.setContentsNull();
		blankSpot[0] = p.getRow();
		blankSpot[1] = p.getCol();
	}
}
