package cobyZhehao;

public class ZhehaoFrontEnd implements CobySupport {

	private ZhehaoSupport backend;
	
	public static final void main(String[] args) {
		ZhehaoFrontEnd test = new ZhehaoFrontEnd();
		test.play();
	}
	public ZhehaoFrontEnd() {
		backend = new CobyBackEnd(this);
	}
	private void StartGame() {
		 ZhehaoCobyPlot[][] plots= backend.getPlots();
		 ZhehaoCobyPlot p =null;
		 while(gameover()) {
			 displayField(plots);
			 displayScore(p);
			 System.out.println("Where do you want to swipe?");
			 
		 }
	}
}
