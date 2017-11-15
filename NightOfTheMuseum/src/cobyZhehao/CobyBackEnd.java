package cobyZhehao;

public class CobyBackEnd implements ZhehaoSupport{

	private CobySupport frontend;
	private int[] randNums;
	private int[][] answer= {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15}};
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
	public boolean gameover() {
		for(int i=0;i<answer.length;i++) {
			for(int j=0;j<answer[i].length; j++) {
				if(ZhehaoCobyPlot[i][j] != answer[i][j]) {
					return false;
				}
			}
		}
		return true;
		
		
	}
	
	public void move(ZhehaoCobyPlot p) {
		p.move();
	}
	

}
