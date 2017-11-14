package cobyZhehao;

public class CobyBackEnd implements ZhehaoSupport{

	private CobySupporter frontend;
	private int[] randNums;
	private ZhehaoCobyPlot[][] plots;
	
	public CobyBackEnd(CobySupport frontend) {
		this.frontend = frontend;
		plots = new ZhehaoCobyPlot[4][4];
		createPlots();
	}

	private void createPlots() {
		for(int row = 0; row < plots.length; row++) {
			for(int col = 0; col < plots[row].length; col++) {
				plots[row][col] = new ZhehaoCobyPlot(row, col);
			}
		}
		randNums = new int[15];
		for(int i = 1; i < randNums.length; i++) {
			randNums[i-1] = i;
		}
	}
	
	public void move(ZhehaoCobyPlot p) {
		p.move();
	}
	

}
