package victorRemington;

public class VictorRemingtonPlot {

	int row; 
	int col;
	boolean isVisible;
	boolean hasBomb;
	boolean isEmpty;
	boolean isFlaged;
	String contents;
	
	public VictorRemingtonPlot(int row, int col) {
		isVisible = false;
		isFlaged = false;
		this.contents = " ";
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
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public String getContents() {
		return contents;
	}
	
	public void setFlaged(boolean flaged) {
		isFlaged = flaged;
	}
}
