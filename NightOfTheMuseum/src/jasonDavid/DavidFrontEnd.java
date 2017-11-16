package jasonDavid;
import java.util.Scanner;
import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;

public class DavidFrontEnd implements JasonSupport{
	
	private JasonBackEnd backend;
	private String map;

	
	public DavidFrontEnd(int size) {
		backend = new JasonBackEnd(this, size);
	}

	public static void main(String[] arg) {
		System.out.println("What size?");
		//int size = CaveExplorer.in.nextInt();
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		DavidFrontEnd demo = new DavidFrontEnd(size);
		demo.play();
	}
	public void play() {	
		displayBoard();
		//while(backend.stillPlaying()) {
			// displayBoard();}
			// String input = getValidUserInput();
		    // if(!backend.respondToInput(input)) {
		    //	 provideHint();
		    // }
			//
		     // backend.analyzeBoard();
		   // }
		   // printGameOverMessage();
	}

	private void provideHint() {
		
	}

	private void printGameOverMessage() {
		System.out.println("Click! A sound ringed loudly signifying the completion of the puzzle.");
	}

	private String getValidUserInput() {
		 return CaveExplorer.in.nextLine();
	}

	private void displayBoard() {
		map = " ";
		//create line across top:
		for(int i = 0; i < backend.table[0].length -1; i++) {
			map += "____";//4 underscores
		}
		map+= "___\n";//3 underscores, makes the corner look symmetrical
		for(int[] row : backend.table) {
			//3 rows of text
			for(int i = 0; i < 3; i++) {
				String text = "";
				for(int cr : row) {
						text += "|";
					//contents of room depend on what row this is
					if(i==0) {
						text+="   ";//3 spaces
					}else if(i == 1) {
						if (0 < cr && cr< 10){
						text += " "+cr+" ";
						}
						if (9<cr && cr< 100){
						text += ""+cr+" ";
						}
						if (99<cr&&cr<1000){
							text+=cr;
						}
					}else if(i == 2) {
						text += "___";
					}
				}//last caveroom in row
				text+="|";
				map += text +"\n";
			}
		}
		System.out.print(map);
	}

}
