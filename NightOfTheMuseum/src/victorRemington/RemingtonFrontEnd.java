package victorRemington;

import victorRemington.VictorRemingtonPlot;
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
		for(int row = 0; row < backend.minefield.length; row++) {
			
		}
	
	public void displayGameState() {
	
	}
	
	public void getSafeTile() {
		
	}
	
	public int calculateAdjacentBombs() {
	
		return 0;
	}
	
	public void evaluateInput() {
		
	}
	
	public void handleVisiblity() {
		
	}
}
