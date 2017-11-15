package jasonDavid;

public class JasonBackEnd implements DavidSupport{
	public JasonSupport frontend;
	public int[][] table;
	public int[] chosen;

	public JasonBackEnd(DavidFrontEnd frontEnd, int size) {
		frontend = frontEnd;
		createTable(size);
	}
	/**
	 * creates a size x size table using double for loop
	 * @param size
	 */
	public void createTable(int size) {
		for (int i=0; i<size; i++) {
			for ( int j=0; j<size; j++) {
				table[i][j] = generateUniqueRandomNumber();
			}
		}
	}
	/**
	 * generate unique random number that is not chosen yet
	 * 
	 * 
	 * 
	 * @return
	 */
	public int generateUniqueRandomNumber() {
		return 0;
	}
	/**
	 * swaps position of the numbers
	 * @param box1
	 * @param box2
	 */
	public void swap(int[][] box1, int[][] box2) {
		
	}
	/**
	 * Formula: M=n(n^2+1)/2 M= winning number n= width of table
	 * Checks to see if winning condition is met
	 * @return
	 */
	public boolean calculateWin() {
		return true;
	}
	/**
	 * Use double for loop to check the sum of individual column/row
	 * @param length
	 * @param width
	 * @param start
	 * @return
	 */
	public int doubleForLoopCalc(int length, int width, String start) {
		return 0;
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
	public void analyzeBoard() {
		// TODO Auto-generated method stub
		
	}
	
}

