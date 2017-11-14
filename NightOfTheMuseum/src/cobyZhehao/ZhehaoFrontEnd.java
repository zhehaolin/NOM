package cobyZhehao;

public class ZhehaoFrontEnd implements CobySupport {

	private ZhehaoSupport backend;
	private int[][] ZhehaoCobyPlot;
	
	public static final void main(String[] args) {
		ZhehaoFrontEnd test = new ZhehaoFrontEnd();
		test.play();
	}
	
	
	private void play() {
		
	}

	public ZhehaoFrontEnd() {
		backend = new CobyBackEnd(this);
	}
	
	private void StartGame() {
		 ZhehaoCobyPlot[][] plots = backend.getPlots();
		 ZhehaoCobyPlot p = null;
		 while(!finished()) {
			 displayField(plots);
			 System.out.println("Which tile do you want to move?");
			 int[] coords = backend.getCoordInput();
			 p = plots[coords[0]][coords[1]];
			 backend.move(p);
		 }
	}
}
