package victorRemington;

public class RemingtonFrontEnd implements VictorSupport{

	private VictorBackEnd backend;
	
	public final static void main(String[] args) {
		RemingtonFrontEnd demo = new RemingtonFrontEnd();
		System.out.print("a");
		demo.play();
	}
	
	public void play() {
		System.out.print("a");
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
	
	public String revealAll() {
		String line = "";
		String chart = "";
		VictorRemingtonPlot[][] minefield = backend.getMinefield();
		for(int row = 0; row < minefield.length; row++) {
			for(int col = 0; col < minefield[0].length; col++) {
				line += minefield[row][col].getContents();
			}
			chart += line + "\n";
		}
		return chart;
	}
	
}
