package cobyZhehao;

import java.util.Scanner;

import caveExplorer.CaveExplorer;

public class ZhehaoFrontEnd implements CobySupport {

	private ZhehaoSupport backend;
	private int[][] ZhehaoCobyPlot;
	private int movestaken;
	public static Scanner in;
	
	public static final void main(String[] args) {
		initScanner();
		ZhehaoFrontEnd test = new ZhehaoFrontEnd();
		test.play();
	}
	
	public static void initScanner(){
		in = new Scanner(System.in);
	}
	private void play() {
		new ZhehaoGameIntro().play();
		menu();
	}

	private void menu() {
		System.out.println("Enter 'r' for rules or 'p' to play.");
		String command = ZhehaoUtility.waitForLetterInput("rp");
		if(command.equals("r")){
			ZhehaoUtility.print("You have to solve the puzzle to unlock the door. In a 4 by 4 square ,you have to place the numbers in order from 1 to 15. Enter the coord of the number "
					+ "you want to move.");
			in.nextLine();
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
			 if(coords !=null) {
				 p = plots[coords[0]][coords[1]];
				 backend.move(p);
				 movestaken++;
			 }else {
				 System.out.println("Please enter a valid input");
			 }
			 
		 }
		 System.out.println("You solve the puzzle! The door is now unclocked!");
	}
	private void displaymovestaken(ZhehaoCobyPlot p) {
		System.out.println("Moves taken: "+movestaken);
	}
	private void displayhints(ZhehaoCobyPlot p) {
		ZhehaoCobyPlot[][] plot=backend.getPlots();
		String[] possiblehint= {"-Locate tiles 1 and 2.\n -Move the tile so that the blank space is in the next position you want to move the tile to.\n -Moves the tile into the blank space.\n -Repeat this process utile tiels 1 and 2 are in the correct positions."
		,"-Move tile 4 into tiles 3's final position.\n - Move tiles 3 to be directly under tile.","-Move the blank square to the top right corner.\n -Move titles three and four like they are a snake so that they are both in their final positions.",
		"-Locate tiles 5 and 6.\n -Move tiles 5 and 6 to thier final positions in a similar way that you moves tiles 1 and 2 into their final positions.","-Move tile 8 into the final position of tile 7.\n-Move tile 7 to be directly under tile 8.","-Move the blank space to be on the right hand side of tile 8.\n -Move tiles 8 and 7 like a snake into their final positions.",
		"-Move tile 13 to be in tile 9's final position.\n -Move tile 9 to be directly to the right of tile 13.","-Rotate the 5 tiles in the bottom left of the board counter clockwise like a snake until tiles 13 and 9 are in their final positions.","-Move tile 14 to the right of tile 9 so that it is in tile 10's final position.\n -Move tile 10 to be directly to the right of tile 14.",
		"-Rotate the three tiles in the middle two columns on the bottom two rows counter clockwise like a snake until tiles 14 and 10 are in their final positions.","-Rotate tiles 11, 12, and 15 either clockwise or counter clockwise until they are all in the correct positions"};
		
		
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

	

}
