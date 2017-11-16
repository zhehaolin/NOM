package jasonDavid;

public class JasonBackEnd implements DavidSupport{
	public JasonSupport frontend;
	public int[][] table;
	
	public int[] chosen;
	public int magicNumber;

	public JasonBackEnd(DavidFrontEnd frontEnd, int size) {
		frontend = frontEnd;
		createTable(size);
		magicNumber = size* ((int)Math.pow(size, 2)+1)/2;
		
	}
	/**
	 * creates a size x size table using double for loop
	 * @param size
	 */
	public void createTable(int size) {
		for (int i=0; i<size; i++) {
			for ( int j=0; j<size; j++) {
				table[i][j] = generateUniqueRandomNumber(size);
			}
		}
	}
	/**
	 * generate unique random number that is not chosen yet
	 * @return
	 */
	public int generateUniqueRandomNumber(int size) {
		int num = (int)(Math.random()*size*size)+1;
		while(isRepeated(chosen, num)) {
			num = (int)(Math.random()*size*size)+1;
		}
		return num;
		
	}
	public boolean isRepeated(int[] arr, int num) {
		for (int i=0; i<arr.length; i++) {
			if (arr[i] == num) {
				return true;
			}
		}
		return false;
	}
	/**
	 * swaps position of the numbers
	 */
	public void swap(int[][] box, int x1, int y1, int x2, int y2){
		int holder = box[x1][y1];
		box[x1][y1] = box[x2][y2];	
		box[x2][y2] = holder;
	}
		
	/**
	 * Formula: M=n(n^2+1)/2 M= winning number n= width of table
	 * Checks to see if winning condition is met
	 * @return
	 */
	public boolean calculateWin() {
		for(int i=0; i<table.length;i++) {
			if(doubleForLoopCalc(i,"row") != magicNumber) {
				return false;
			}
		}
		for (int j=0; j<table[0].length; j++) {
			if (doubleForLoopCalc(j,"column") != magicNumber) {
				return false;
			}
		}
		if (calcDiagonal("left") != magicNumber || calcDiagonal("right") != magicNumber) {
			return false;
		}
		return true;
	}
	/**
	 * Use double for loop to check the sum of individual column/row
	 * @param num
	 * @param start
	 * @return
	 */
	public int doubleForLoopCalc(int num, String start) {
		int count =0;
		if (start.equals("row")) {
			for (int i=0; i < table[num].length;i++) {
				count += table[num][i];
			}
		}
		else {
			for (int i=0; i<table.length; i++) {
				count+= table[i][num];
			}
		}
		return count;
	}
	public int calcDiagonal(String start) {
		int count =0;
		if (start.equals("left")) {
			int x =0;
			int y =0;
			while (x < table.length && y < table[0].length) {
				count+= table[x][y];
				x++;
				y++;
			}
		}
		else {
			int x= 0;
			int y= table.length-1;
			while (x < table.length && y >= 0) {
				count+=count+= table[x][y];
				x++;
				y--;
			}
		}
		return count;
	}
	@Override
	public boolean stillPlaying() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean respondToInput(String input) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean analyzeBoard() {
		return true;
	}
	
}

