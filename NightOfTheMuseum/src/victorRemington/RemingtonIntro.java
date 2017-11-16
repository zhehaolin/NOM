package victorRemington;

public class RemingtonIntro {

	public RemingtonIntro() {
		
	}
	
	public void play(){
		displayGameScreen();
	}

	private void displayGameScreen() {
		String message = "     Welcome to:\n   MINESWEEPER\n";
		for(int i = 0 ; i < message.length(); i++){
			System.out.print(message.substring(i, i+1));
			pause(100);
		}
		System.out.println("\n- - press enter - -");
	}

	public static void pause(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}