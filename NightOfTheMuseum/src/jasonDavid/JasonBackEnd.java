package jasonDavid;

public class JasonBackEnd implements DavidSupport{
	private JasonSupport frontend;
	private int[][] table;
	private int[] chosen;

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
	 * @return
	 */
	private int generateUniqueRandomNumber() {
		return 0;
	}
	
}

