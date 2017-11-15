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
		int size = sc.nextInt();
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
		// TODO Auto-generated method stub
		
	}

	private String getValidUserInput() {
		// TODO Auto-generated method stub
		return null;
	}

	private void displayBoard() {
		// TODO Auto-generated method stub
		
	}

}
