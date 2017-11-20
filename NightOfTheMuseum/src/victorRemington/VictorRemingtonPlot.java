package victorRemington;

public class VictorRemingtonPlot {

	private int row; 
	private int col;
	private boolean isVisible;
	private boolean hasBomb;
	private boolean isEmpty;
	private boolean isFlaged;
	private String contents;
	private String tempContents;
	
	public VictorRemingtonPlot(int row, int col) {
		isVisible = false;
		isFlaged = false;
		this.contents = "";
		this.tempContents = "";
		this.row = row;
		this.col = col;
	}

	public void setHasBomb(boolean hasBomb) {
		this.hasBomb = hasBomb;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean visibility) {
		this.isVisible = visibility;
	}

	public boolean hasBomb() {
		return hasBomb;
	}

	public void setContents(String contents) {
		this.contents = contents;
		tempContents = contents;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public String getTempContents() {
		return tempContents;
	}
	
	public void setFlaged(boolean flaged) {
		isFlaged = flaged;
		if(flaged) {
			tempContents = "F";
		}else {
			tempContents = contents;
		}
		
	}
}
