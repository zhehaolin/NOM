package cobyZhehao;

import caveExplorer.CaveExplorer;

public class ZhehaoFrontEnd implements CobySupport {

	private ZhehaoSupport backend;
	private int[][] ZhehaoCobyPlot;
	private int movestaken;
	
	public static final void main(String[] args) {
		ZhehaoFrontEnd test = new ZhehaoFrontEnd();
		test.play();
	}
	
	
	private void play() {
		new ZhehaoGameIntro().play();
		CaveExplorer.in.nextLine();
		menu();
	}

	private void menu() {
		System.out.println("Enter 'r' for rules or 'p' to play.");
		String command = ZhehaoUtility.waitForLetterInput("rp");
		if(command.equals("r")){
			ZhehaoUtility.print("You have to solve the puzzle to unlock the door. In a 4 by 4 square ,you have to place the numbers in order from 1 to 15. Enter the coord of the number "
					+ "you want to move.");
			CaveExplorer.in.nextLine();
			menu();
		}else{
			StartGame();
		}
		
	}


	public ZhehaoFrontEnd() {
		backend = new CobyBackEnd(this);
		movestaken=0;
	}
	
	private void StartGame() {
		 ZhehaoCobyPlot[][] plots = backend.getPlots();
		 ZhehaoCobyPlot p = null;
		 while(!backend.gameover()) {
			 displayField(plots);
			 displaymovestaken(p);
			 System.out.println("Which tile do you want to move? Or enter 'h' for hints based on your puzzle.");
			 String userinput= ZhehaoUtility.waitForLetterInput("h");
			 if(userinput.equals("h")) {
				 displayhints(p);
			 }
			 int[] coords = backend.getCoordInput();
			 p = plots[coords[0]][coords[1]];
			 backend.move(p);
			 movestaken++;
		 }
		 System.out.println("You solve the puzzle! The door is now unclocked!");
	}
	private void displaymovestaken(ZhehaoCobyPlot p) {
		System.out.println("Moves taken: "+movestaken);
	}
	private void displayhints(ZhehaoCobyPlot p) {
		
	}
		


	private void displayField(cobyZhehao.ZhehaoCobyPlot[][] plots) {
		String rows = "0123456789";
		String columns = "  0123456789";
		for(int row = 0; row < plots.length; row++){
			System.out.print(rows.substring(row, row+1)+" ");
			for(int col = 0; col < plots[row].length; col++){
				System.out.println(ZhehaoCobyPlot[row][col]);
			}
			System.out.println(" " + rows.substring(row, row+1));
		}
		System.out.println(columns.substring(0, plots[0].length+2));
	}

	//http://www.instructables.com/id/How-To-Solve-The-15-Puzzle/

}