package cobyZhehao;

public class ZhehaoCobyPlot {

	private int row;
	private int col;
	private String contents;
	private String correct;
	
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
	
	public void setAnswer(String a) {
		correct = a;
	}
	
	public String getAnswer() {
		return this.correct;
	}
	
	public void setContentsNull() {
		this.contents = " ";
	}
}
