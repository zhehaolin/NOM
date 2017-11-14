package victorRemington;

public class RemingtonFrontEnd implements VictorSupport{

	private VictorBackEnd backend;
	
	public final static void main(String[] args) {
		RemingtonFrontEnd demo = new RemingtonFrontEnd();
		demo.play();
	}
	
	private void play() {
		//has to draw initial blank board
		//when choosing a random tile has to account for blank tiles and reveal visiblity accordingly
		//then changes when back end responds
		
	}

	public RemingtonFrontEnd(){
		backend = new VictorBackEnd(this);
	}
	
	public void drawField() {
		//draws out board
	}
	
	public void displayGameState() {
		//returns num of bombs
	}
	
	public void getSafeTile() {
		//goes through minefield and finds a non-bomb tile. This is the tile that the player initially has revealed
	}
	
	public int calculateAdjacentBombs() {
		//checks number of bombs next to the tile the player clicks
		return 0;
	}
	
	public void evaluateInput() {
		//checks to see what type of tile the user clicks 
		//Ex: is it blank or does it have a bomb and responds accordingly
		
		//backend.validateInput
		
		//reveal tile-blank
		
		//reveal tile-number
		
		//reveal tile-bomb
	}
	
	public void handleVisiblity() {
		//different actions depending on what tile was clicked
	}
}
