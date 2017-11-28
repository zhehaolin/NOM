package cobyZhehao;

import java.util.Scanner;

import caveExplorer.CaveExplorer;
import caveExplorer.Inventory;

public class ZhehaoFrontEnd implements CobySupport {

	private CobyBackEnd backend;
	private int movestaken;
	public static Scanner in;
	private String cheat;
	private boolean game;
	
	public static final void main(String[] args) {
		if(Inventory.puzzleGet() == false) {
		initScanner();
		ZhehaoFrontEnd test = new ZhehaoFrontEnd();
		test.play();
		}
		else {
			System.out.println("You already finished this puzzle");
		}
	}
	
	public static void initScanner(){
		in = new Scanner(System.in);
	}
	private void play() {
		new ZhehaoGameIntro().play();
		game = backend.gameover();
		menu();
	}

	private void menu() {
		System.out.println("Enter 'r' for rules or 'p' to play.");
		String command = ZhehaoUtility.waitForLetterInput("rp");
		if(command.equals("r")){
			ZhehaoUtility.print("You have to solve the puzzle to unlock the door. In a 4 by 4 square ,you have to place the numbers in order from 1 to 15. Enter the coord of the number "
					+ "you want to move.Enter the coord of the number you want to move in this format:x,y"+"\n press enter to continue");
			in.nextLine();
			menu();
		}else{
			StartGame();
		}
		
	}


	public ZhehaoFrontEnd() {
		backend = new CobyBackEnd(this);
		movestaken=0;
		cheat="math";
	}
	
	private void StartGame() {
		 ZhehaoCobyPlot[][] plots = backend.getPlots();
		 ZhehaoCobyPlot p = null;
		 
		 while(!game) {
			 updateMap();
			 displaymovestaken(p);
			 System.out.println("Which tile do you want to move? ");
			 displayhints(p);
			 int[] coords = backend.getCoordInput();
			 if(coords !=null) {
				 if(coords[0]==9 && coords[1]==9) {
					 System.out.println("You solved the puzzle. You get a key.");
					 Inventory.Obtainkeys();
					 break;
				 }
					p = plots[coords[0]][coords[1]];
					backend.move(p);
					movestaken++;
				 }else {
					 System.out.println("Please enter a valid input");
				}
				 
			 }
		 Inventory.puzzleDone();
			 
			 
			 
		 
	}
	

	private boolean CheatUsed(String input) {
		return(input.equals(cheat));
	}

	private void displaymovestaken(ZhehaoCobyPlot p) {
		System.out.println("Moves taken: "+movestaken);
	}
	private void displayhints(ZhehaoCobyPlot p) {
		ZhehaoCobyPlot[][] plot=backend.getPlots();
		String[] possiblehint= {" -Locate tiles 1 and 2.\n -Move the tile so that the blank space is in the next position you want to move the tile to.\n -Moves the tile into the blank space.\n -Repeat this process utile tiels 1 and 2 are in the correct positions."
		,"-Move tile 4 into tiles 3's final position.\n - Move tiles 3 to be directly under tile.","-Move the blank square to the top right corner.\n -Move titles three and four like they are a snake so that they are both in their final positions.",
		"-Locate tiles 5 and 6.\n -Move tiles 5 and 6 to thier final positions in a similar way that you moves tiles 1 and 2 into their final positions.","-Move tile 8 into the final position of tile 7.\n-Move tile 7 to be directly under tile 8.","-Move the blank space to be on the right hand side of tile 8.\n -Move tiles 8 and 7 like a snake into their final positions.",
		"-Move tile 13 to be in tile 9's final position.\n -Move tile 9 to be directly to the right of tile 13.","-Rotate the 5 tiles in the bottom left of the board counter clockwise like a snake until tiles 13 and 9 are in their final positions.","-Move tile 14 to the right of tile 9 so that it is in tile 10's final position.\n -Move tile 10 to be directly to the right of tile 14.",
		"-Rotate the three tiles in the middle two columns on the bottom two rows counter clockwise like a snake until tiles 14 and 10 are in their final positions.","-Rotate tiles 11, 12, and 15 either clockwise or counter clockwise until they are all in the correct positions"};
		
		System.out.println("Hints:\n"+possiblehint[checkplot(plot)]);
		
		
	}
	private int checkplot(ZhehaoCobyPlot[][] z) {
		
		int[][] answer={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15}};
		ZhehaoCobyPlot[][] plots = backend.getPlots();
		int count=0;
		for(int row = 0; row < z.length; row++) {
			for(int col = 0; col < z[row].length; col++) {
				
				if(valid(0,col+1)) {
					if(z[row][col].getContents().equals(" ") || z[row][col+1].getContents().equals(" ")){
						if(count>11) {
							return 10;
						}else {
							return count;
						}
					}else {
						if(Integer.parseInt(z[row][col].getContents())!=answer[row][col] || Integer.parseInt(z[row][col+1].getContents())!=answer[row][col+1]) {
							if(count>11) {
								return 10;
							}else {
								return count;
							}
						}else {
							count++;
						}
					}
				}else {
					return 10;
				}
					
				
			}
		}
		return count;
		
	}
	
	private boolean valid(int row, int col) {
		return row >= 0 && row< backend.getPlots().length && col >= 0 && col < backend.getPlots()[row].length;
	}

	public void updateMap() {
		String map = " ";
	
		for(int i = 0; i < backend.plots[0].length -1; i++) {
			map += "____";
		}
		map+= "____\n";
		for(ZhehaoCobyPlot[] row : backend.plots) {
			
			for(int i = 0; i < 3; i++) {
				String text = "";
				for(ZhehaoCobyPlot cr : row) {
					
						text += "|";
					
					if(i==0) {
						text+="   ";
					}else if(i == 1) {
						if(cr.getContents().length()==1) {
							text += " "+cr.getContents()+" ";
						}else {
							text+=" "+cr.getContents();
						}
						
					}else if(i == 2) {
							text += "___";
					}
				}
				text+="|";
				map += text +"\n";
			}
			
		}
		System.out.print(map);
	}
}
