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
		int size = CaveExplorer.in.nextInt();
		DavidFrontEnd demo = new DavidFrontEnd(size);
		demo.play();
	}
	public void play() {	
		while(backend.stillPlaying()) {
			 displayBoard();
			 String input = getValidUserInput();
		     if(!backend.respondToInput(input)) {
		    	 provideHint();
		     }
			
		      backend.analyzeBoard();
		    }
		    printGameOverMessage();
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
		for(int i = 0; i < CaveExplorer.caves[0].length -1; i++) {
			map += "____";//4 underscores
		}
		map+= "___\n";//3 underscores, makes the corner look symmetrical
		for(CaveRoom[] row : CaveExplorer.caves) {
			//3 rows of text
			for(int i = 0; i < 3; i++) {
				String text = "";
				for(CaveRoom cr : row) {
					//if door is open, leave open
					if(cr.getDoor(CaveRoom.WEST) != null &&
							cr.getDoor(CaveRoom.WEST).isOpen()) {
						text += " ";
					}else {
						text += "|";
					}
					//contents of room depend on what row this is
					if(i==0) {
						text+="   ";//3 spaces
					}else if(i == 1) {
						text += " "+cr.getContents()+" ";
					}else if(i == 2) {
						//draw space if door to south is open
						if(cr.getDoor(CaveRoom.SOUTH) != null && 
						cr.getDoor(CaveRoom.SOUTH).isOpen()){
							text+="   ";//3 spaces
						}else {
							text += "___";
						}
					}
				}//last caveroom in row
				text+="|";
				map += text +"\n";
			}
		}
	}

}
