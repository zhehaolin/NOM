package jasonDavid;

public class JasonBackEnd implements DavidSupport{
	public JasonSupport frontend;
	public static int[][] table;
	public boolean playing;
	
	public static int[] chosen;
	public int magicNumber;
	
	public JasonBackEnd(DavidFrontEnd frontEnd, int size) {
		table = new int[size][size];
		chosen = new int[size*size];
		frontend = frontEnd;
		createTable(size);
		magicNumber = size* ((int)Math.pow(size, 2)+1)/2;
		playing = true;
	}
	/**
	 * creates a size x size table using double for loop
	 * @param size
	 */
	public void createTable(int size) {
		for (int i=0; i<size; i++) {
			for ( int j=0; j<size; j ++) {
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
		while(isRepeated(num)) {
			num = (int)(Math.random()*size*size)+1;
		}
		return num;
		
	}
	public boolean isRepeated(int num) {
		for (int i=0; i<chosen.length; i++) {
			if (chosen[i] == num) {
				return true;
			}
		}
		addNum(num);
		return false;
	}
	private void addNum(int num) {
		for (int i=0; i<chosen.length;i++) {
			if (chosen[i] == 0) {
				chosen[i] = num;
				return;
			}
		}
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
	public boolean stillPlaying() {
		return playing;
	}
	public boolean respondToInput(String input1, String input2) {
		if (isValid(input1) && isValid(input2)) {
			int x = Integer.parseInt(input1.substring(0,1));
			int y = Integer.parseInt(input1.substring(2,3));
			int x1 = Integer.parseInt(input2.substring(0,1));
			int y1 = Integer.parseInt(input2.substring(0,1));
			swap(table,x,y,x1,y1);
		}
		return false;
	}
	public void analyzeBoard() {
		playing = calculateWin();
	}
	public boolean isValid(String input) {
		return input.length()==3 &&isNum(input.substring(0,1)) && isNum(input.substring(2,3)) && input.substring(1,2).equals(",");
	}
	public static boolean isNum(String input) {
	    boolean isNum = true;
	    try {

	        Double.parseDouble(input);

	    }catch (NumberFormatException e) {
	        isNum = false;
	    }
	    return isNum;
	}
	
}

