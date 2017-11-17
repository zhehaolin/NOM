package cobyZhehao;

public class ZhehaoCobyPlot {

	private int row;
	private int col;
	private int contents;
	private int correct;
	
	public ZhehaoCobyPlot(int row,int col) {
		this.row=row;
		this.col=col;
	}
	public int getRow()
	{
		return row;
	}
	public int getCol() {
		return col;
	}
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
	public void setContents(int s) {
		contents = s;
	}
	
	public int getContents() {
		return this.contents;
	}
	
	public void setAnswer(int a) {
		correct = a;
	}
	
	public int getAnswer() {
		return this.correct;
	}
}
