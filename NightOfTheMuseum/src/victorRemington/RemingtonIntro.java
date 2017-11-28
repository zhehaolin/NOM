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
		System.out.println("You are in the WWI exhibit. On either side of the room there are soldier models with guns firing at eachother. \nYou see the key in the middle of the room, but the floor is littered with mines. \nDuring a lapse in combat you test your luck and set out for the key.");
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