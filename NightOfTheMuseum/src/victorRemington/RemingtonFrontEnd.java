package victorRemington;

import victorRemington.VictorRemingtonPlot;
import caveExplorer.BenNocklesPlot;
import caveExplorer.CaveExplorer;
import victorRemington.RemingtonIntro;
import victorRemington.RemingtonUtility;

public class RemingtonFrontEnd implements VictorSupport{

	private VictorBackEnd backend;
	
	public final static void main(String[] args) {
		RemingtonFrontEnd demo = new RemingtonFrontEnd();
		demo.play();
	}
	
	private int bombAmt;
	
	public void play() {

		new RemingtonIntro().play();
		CaveExplorer.in.nextLine();
		introScreen();

		System.out.print(revealAll());

	}

	private void introScreen() {
		System.out.println("Enter 'r' for rules or 'p' to play.");
		String command = RemingtonUtility.waitForLetterInput("rp");
		if(command.equals("r")){
			RemingtonUtility.print("The objective of the game is to clear the board containing hidden 'mines' or bombs without detonating any of them."
					+" With help from clues(numbers that pop up after putting in a coordinate) about the number of neighboring mines in each field.\n\n      - - press enter - -");
			CaveExplorer.in.nextLine();
			introScreen();
		}else{
			drawField();
		}

	}
	
	public RemingtonFrontEnd(){
		backend = new VictorBackEnd(this);
	}
	
	public void drawField() {
		for(int row = 0; row < backend.getMinefield().length; row++) {
			  for (int col = 0; col < backend.getMinefield()[row].length; col++){
				  minefield[row][col] = new VictorRemingtonPlot(row, col);
			  }
		}
	
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
		String line = "";
		String chart = "";
		VictorRemingtonPlot[][] minefield = backend.getMinefield();
		for(int row = 0; row < minefield.length; row++) {
			for(int col = 0; col < minefield[0].length; col++) {
				line += minefield[row][col].getContents() + " ";
			}
			chart += line + "\n";
			line = "";
		}
		return chart;
	}
	
}
