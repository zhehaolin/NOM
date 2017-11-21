package victorRemington;

import victorRemington.VictorRemingtonPlot;
import caveExplorer.CaveExplorer;
import victorRemington.RemingtonIntro;
import victorRemington.RemingtonUtility;
import java.util.Scanner;

public class RemingtonFrontEnd implements VictorSupport{

	public static Scanner in;
	private VictorBackEnd backend;
	public String cheatCode;
	public boolean playing;
	
	
	
	public final static void main(String[] args) {
		in = new Scanner(System.in);
		RemingtonFrontEnd demo = new RemingtonFrontEnd();
		demo.play();
	}
	
	public void play() {
		new RemingtonIntro().play();
		in.nextLine();
		introScreen();

		while(!backend.checkVictory()) {
			drawField();
			displayGameState();
			String input = in.nextLine();
			while(!backend.checkValidinput(input)) {
				input = in.nextLine();
				System.out.println("Type input in form #,# or f#,#");
			}
			
			//MAKE SURE TO ENSURE THAT ALL INPUTS WORK. 
			//HAVE SOME ERROR CHECKING TO TELL THE USER WHAT TO DO.
				
			backend.getMinefield()[getCoords(input)[0]][getCoords(input)[1]].setVisible(true);
			//if(checkCheatCode(in.nextLine())){
				System.out.print(revealAll() +"\n\nCongradulations you won!!!!\n\n\n\n\n...By cheating.......");
			//}
		}
		

	}

	private void introScreen() {
		System.out.println("Enter 'r' for rules or 'p' to play.");
		String command = RemingtonUtility.waitForLetterInput("rp");
		if(command.equals("r")){
			RemingtonUtility.print("The objective of the game is to clear the board containing hidden 'mines' or bombs without detonating any of them."
					+" With help from clues(numbers that pop up after putting in a coordinate) about the number of neighboring mines in each field.\n\n      - - press enter - -");
			in.nextLine();
			introScreen();
		}
	}
	
	
	public RemingtonFrontEnd(){
		backend = new VictorBackEnd(this);
		cheatCode = "ab";
	}
	
	public void drawField() {
		String line = "0 |";
		String chart = "\n   0 1 2 3 4 5 6 7 \n   _______________\n";
		VictorRemingtonPlot[][] minefield = backend.getMinefield();
		for(int row = 0; row < minefield.length; row++) {
			for(int col = 0; col < minefield[0].length; col++) {
				if(minefield[row][col].isVisible()) {
					line += minefield[row][col].getTempContents()+" ";
				}else {
					line += "* ";
				}
			}
			chart += line + "\n";
			line = row+1 + " |";      
		}
		System.out.println(chart);;
	}
	public void displayGameState() {
		System.out.println("You have "+ backend.getFlaged() + " flaged bombs. You need to flag 11 to win. But make sure they are actually bombs...or else.");
	}
	
	public String revealAll() {
		String line = "0  ";
		String chart = "   0 1 2 3 4 5 6 7 \n\n";
		VictorRemingtonPlot[][] minefield = backend.getMinefield();
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
}
