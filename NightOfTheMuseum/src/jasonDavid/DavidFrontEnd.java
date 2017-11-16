package jasonDavid;
import java.util.Scanner;
import caveExplorer.CaveExplorer;

public class DavidFrontEnd implements JasonSupport{
	
	private DavidSupport backend;

	
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
		// TODO Auto-generated method stub
		
	}

	private void printGameOverMessage() {
		System.out.println("Click! A sound ringed loudly signifying the completion of the puzzle.");
	}

	private String getValidUserInput() {
		 return CaveExplorer.in.nextLine();
	}

	private void displayBoard() {
		
	}

}
