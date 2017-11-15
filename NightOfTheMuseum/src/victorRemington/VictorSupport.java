package victorRemington;

public interface VictorSupport {
	void play();
		//has to draw initial blank board
		//when choosing a random tile has to account for blank tiles and reveal visiblity accordingly
		//then changes when back end responds
		


	void drawField();
		//draws out board

	
	void displayGameState();
		//returns num of bombs

	
	void getSafeTile();
		//goes through minefield and finds a non-bomb tile. This is the tile that the player initially has revealed
	
	void evaluateInput();
		//checks to see what type of tile the user clicks 
		//Ex: is it blank or does it have a bomb and responds accordingly
		
		//backend.validateInput
		
		//reveal tile-blank
		
		//reveal tile-number
		
		//if flagging display flag
	
	
	void handleVisiblity();
		//different actions depending on what tile was clicked
		//bombs should NEVER be revealed
	
	
}
