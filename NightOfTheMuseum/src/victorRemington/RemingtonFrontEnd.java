package victorRemington;

import victorRemington.VictorRemingtonPlot;
import caveExplorer.CaveExplorer;
import caveExplorer.Inventory;
import victorRemington.RemingtonIntro;
import victorRemington.RemingtonUtility;
import java.util.Scanner;

public class RemingtonFrontEnd implements VictorSupport{

	public static Scanner in;
	private VictorBackEnd backend;
	public String cheatCode;
	public boolean playing;
	public VictorRemingtonPlot[][] minefield;
	private static boolean passed;
	
	
	public final static void main(String[] args) {
		in = new Scanner(System.in);
		RemingtonFrontEnd demo = new RemingtonFrontEnd();
		if(!passed) {
			demo.play();
		}
		
	}
	
	public void play() {
		new RemingtonIntro().play();
		in.nextLine();
		introScreen();
		boolean victory = backend.checkVictory();
		
		while(!victory) {
			drawField();
			displayGameState();
			String input = in.nextLine();
			while(!backend.checkValidinput(input)) {
				System.out.println("Type input in form #,# or f#,# to flag or unflag a tile.");
				input = in.nextLine();
			}
			int[] coords = getCoords(input);
			if(checkCheatCode(input)){
				victory = true;
			}else {
				if(input.substring(0, 1).equals("f")) {
					backend.flag(coords);
				}else {
					if(minefield[coords[0]][coords[1]].hasBomb()) {
						System.out.println("That was a bomb. You lose.");
						break;
					}else {
						backend.changeVisibility(coords[0], coords[1], true);
						if(minefield[coords[0]][coords[1]].getTempContents().equals("0")) {
							revealAdjacentZeros(coords[0], coords[1]);
						}
					}
				}
			}
			
			//MAKE SURE TO ENSURE THAT ALL INPUTS WORK. 
			//HAVE SOME ERROR CHECKING TO TELL THE USER WHAT TO DO.

			
		}
		if(victory) {
			System.out.print(revealAll());
			System.out.print("\n\nYou won!");
			passed = true;
			Inventory.addKey();
		}
	}

	private void introScreen() {
		System.out.println("Enter 'r' for rules or 'p' to play.");
		String command = RemingtonUtility.waitForLetterInput("rp");
		if(command.equals("r")){
			RemingtonUtility.print("The objective of the game is to clear the board containing hidden 'mines' or bombs without detonating any of them. Then you can get a key after crossing the field."
					+" With help from clues(numbers that pop up after putting in a coordinate) about the number of neighboring mines in each field.\n\n      - - press enter - -");
			in.nextLine();
			introScreen();
		}
	}
	
	
	public RemingtonFrontEnd(){
		backend = new VictorBackEnd(this);
		minefield = backend.getMinefield();
		cheatCode = "ab";
		passed = false;
	}
	
	public void drawField() {
		String line = "";
		String chart = "\n   0 1 2 3 4 5 6 7 \n   _______________\n";
		for(int row = 0; row < minefield.length; row++) {
			line = row + " |"; 
			for(int col = 0; col < minefield[0].length; col++) {
				if(minefield[row][col].isVisible()) {
					line += minefield[row][col].getTempContents()+" ";
				}else {
					line += "* ";
				}
			}
			chart += line + "\n";
			     
		}
		System.out.println(chart);;
	}
	public void displayGameState() {
		System.out.println("You have "+ backend.getFlaged() + " flaged tiles. You need to correctly flag 11 bombs to win. But make sure they are actually bombs...or else." + "TEST " + backend.getCorrectFlaged());
	}
	
	public String revealAll() {
		String line = "0  ";
		String chart = "   0 1 2 3 4 5 6 7 \n\n";
		for(int row = 0; row < minefield.length; row++) {
			for(int col = 0; col < minefield[0].length; col++) {
				line += minefield[row][col].getTempContents() + " ";
			}
			chart += line + "\n";
			line = row+1 + "  ";
		}
		return chart;
	}

	public boolean checkCheatCode(String code) {
		return(code.equals(cheatCode));
	}
	
	public int[] getCoords(String s) {
		int[] coords = new int[2];
		if(s.substring(0,1).equals("f")) {
			coords[0] = Integer.parseInt(s.substring(1, 2));
			coords[1] = Integer.parseInt(s.substring(3, 4));
			return coords;
		}
		coords[0] = Integer.parseInt(s.substring(0, 1));
		coords[1] = Integer.parseInt(s.substring(2, 3));
		return coords;
	}
	
	private void revealAdjacentZeros(int row, int col) {
		backend.changeVisibility(row, col, true);
		if(row > 0) {
			//N
			if(checkForZero(row - 1, col)) {
				if(!minefield[row-1][col].isVisible()) {
					revealAdjacentZeros(row - 1, col);
				}
				revealAdjacent(row - 1, col);
			}
		}
		
		if(row < minefield.length - 1) {
			//S
			if(checkForZero(row + 1, col)) {
				if(!minefield[row+1][col].isVisible()) {
					revealAdjacentZeros(row + 1, col);
				}
				revealAdjacent(row + 1, col);
			}
		}
		
		if(col > 0) {
			//W
			if(checkForZero(row, col - 1)) {
				if(!minefield[row][col-1].isVisible()) {
					revealAdjacentZeros(row, col - 1);
				}
				revealAdjacent(row, col - 1);
			}
			
			if(row > 0) {
				//NW
				if(checkForZero(row - 1, col - 1)) {
					if(!minefield[row-1][col-1].isVisible()) {
						revealAdjacentZeros(row - 1, col - 1);
					}
					revealAdjacent(row - 1, col - 1);
				}
	
			}
			if(row < minefield.length - 1) {
				//SW
				if(checkForZero(row + 1, col - 1)) {
					if(!minefield[row+1][col-1].isVisible()) {
						revealAdjacentZeros(row + 1, col - 1);
					}
					revealAdjacent(row + 1, col - 1);
				}
			}
		}
		
		if(col < minefield[0].length - 1) {
			//E
			if(checkForZero(row, col + 1)) {
				if(!minefield[row][col+1].isVisible()) {
					revealAdjacentZeros(row, col + 1);
				}
				revealAdjacent(row, col + 1);
			}
			if(row > 0) {
				//NE
				if(checkForZero(row - 1, col + 1)) {
					if(!minefield[row-1][col+1].isVisible()) {
						revealAdjacentZeros(row - 1, col + 1);
					}
					revealAdjacent(row - 1, col + 1);
				}
			}
			if(row < minefield.length - 1) {
				//SE
				if(checkForZero(row + 1, col + 1)) {
					if(!minefield[row+1][col+1].isVisible()) {
						revealAdjacentZeros(row + 1, col + 1);
					}
					revealAdjacent(row + 1, col + 1);
				}
					
			}
		}
	}

	private boolean checkForZero(int i, int j) {
		return minefield[i][j].getTempContents().equals("0");
	}
	
	public void revealAdjacent(int row, int col) {
		if(row > 0) {
			//N
			backend.changeVisibility(row - 1, col,true);
		}
		
		if(row < minefield.length - 1) {
			//S
			backend.changeVisibility(row + 1, col, true);
		}
		
		if(col > 0) {
			//W
			backend.changeVisibility(row, col - 1,true);
			if(row > 0) {
				//NW
				backend.changeVisibility(row - 1, col - 1,true);
			}
			if(row < minefield.length - 1) {
				//SW
				backend.changeVisibility(row + 1, col - 1,true);
			}
		}
		
		if(col < minefield[0].length - 1) {
			//E
			backend.changeVisibility(row, col + 1, true);
			if(row > 0) {
				//NE
				backend.changeVisibility(row - 1, col + 1,true);
			}
			if(row < minefield.length - 1) {
				//SE
				backend.changeVisibility(row + 1, col + 1,true);
			}
		}
	}
}
