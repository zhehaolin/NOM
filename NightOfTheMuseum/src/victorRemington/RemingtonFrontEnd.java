package victorRemington;

public class RemingtonFrontEnd implements VictorSupport{

	private VictorBackEnd backend;
	
	public final static void main(String[] args) {
		RemingtonFrontEnd demo = new RemingtonFrontEnd();
		demo.play();
	}
	
	public void play() {
		
		
	}

	public RemingtonFrontEnd(){
		backend = new VictorBackEnd(this);
	}
	
	public void drawField() {
		
	}
	
	public void displayGameState() {
	
	}
	
	public void getSafeTile() {
		
	}
	
	public int calculateAdjacentBombs() {
	
		return 0;
	}
	
	public void evaluateInput() {
		
	}
	
	public void handleVisiblity() {
		
	}
}
