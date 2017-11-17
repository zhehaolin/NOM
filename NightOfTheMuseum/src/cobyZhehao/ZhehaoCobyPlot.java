package cobyZhehao;

public class ZhehaoCobyPlot {

	private int row;
	private int col;
	private String contents;
	private int correct;
	
	public ZhehaoCobyPlot(int row,int col) {
		this.row=row;
		this.col=col;
	}
	public int getRow(){
		return row;
	}
	public int getCol() {
		return col;
	}
	public void move() {
		
	}
	
	public void setContents(String starting) {
		contents = starting;
	}
	
	public String getContents() {
		return this.contents;
	}
	
	public void setAnswer(int a) {
		correct = a;
	}
	
	public int getAnswer() {
		return this.correct;
	}
	
	public void setContentsNull() {
		this.contents = " ";
	}
}
