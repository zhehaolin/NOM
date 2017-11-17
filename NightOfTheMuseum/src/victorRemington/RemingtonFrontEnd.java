package victorRemington;

import victorRemington.VictorRemingtonPlot;
import caveExplorer.CaveExplorer;
import victorRemington.RemingtonIntro;
import victorRemington.RemingtonUtility;
import java.util.Scanner;

public class RemingtonFrontEnd implements VictorSupport{

	public static Scanner in;
	private VictorBackEnd backend;
	
	
	public final static void main(String[] args) {
		in = new Scanner(System.in);
		RemingtonFrontEnd demo = new RemingtonFrontEnd();
		demo.play();
	}
	
	private int bombAmt;
	
	public void play() {

		new RemingtonIntro().play();
		in.nextLine();
		introScreen();

		//used for cheatcode and testing
		System.out.print(revealAll());

	}

	private void introScreen() {
		System.out.println("Enter 'r' for rules or 'p' to play.");
		String command = RemingtonUtility.waitForLetterInput("rp");
		if(command.equals("r")){
			RemingtonUtility.print("The objective of the game is to clear the board containing hidden 'mines' or bombs without detonating any of them."
					+" With help from clues(numbers that pop up after putting in a coordinate) about the number of neighboring mines in each field.\n\n      - - press enter - -");
			in.nextLine();
			introScreen();
		}else{
			drawField();
		}

	}
	
	public RemingtonFrontEnd(){
		backend = new VictorBackEnd(this);
	}
	
	public void drawField() {
		String line = "0  ";
		String chart = "   0 1 2 3 4 5 6 7 \n\n";
		VictorRemingtonPlot[][] minefield = backend.getMinefield();
		for(int row = 0; row < minefield.length; row++) {
			for(int col = 0; col < minefield[0].length; col++) {
				line += "* ";
			}
			chart += line + "\n";
			line = row+1 + "  ";
		}
		System.out.println(chart);;
	}
	//rr
	public void displayGameState() {
	
	}
	
	public int calculateAdjacentBombs() {
		return 0;
	}
	
	public void evaluateInput() {
		
	}
	
	public void handleVisiblity() {
		
	}
	
	public String revealAll() {
		String line = "0  ";
		String chart = "   0 1 2 3 4 5 6 7 \n\n";
		VictorRemingtonPlot[][] minefield = backend.getMinefield();
		for(int row = 0; row < minefield.length; row++) {
			for(int col = 0; col < minefield[0].length; col++) {
				line += minefield[row][col].getContents() + " ";
			}
			chart += line + "\n";
			line = row+1 + "  ";
		}

		return chart;
	}

	@Override
	public void getSafeTile() {
		// TODO Auto-generated method stub
		
	}
	
}
