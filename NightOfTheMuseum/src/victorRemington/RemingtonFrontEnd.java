package victorRemington;

public class RemingtonFrontEnd implements VictorSupport{

	private VictorBackEnd backend;
	
	public final static void main(String[] args) {
		RemingtonFrontEnd demo = new RemingtonFrontEnd();
		demo.play();
	}
	
	private void play() {
		//send startPosition to the backEnd
		//has to draw initial blank board
		//then changes when back end responds
		
	}

	public RemingtonFrontEnd(){
		backend = new VictorBackEnd(this);
	}
	
	public void drawField() {
		//draws out board
	}
	
	public void setVisibility() {
		//makes spaces visible when needed
	}
	
	public void displayGameState() {
		//returns num of bombs
	}
	
	
}
