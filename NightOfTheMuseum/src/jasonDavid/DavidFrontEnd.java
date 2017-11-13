package jasonDavid;

public class DavidFrontEnd implements JasonSupport{
	private DavidSupport backend;
	
	public DavidFrontEnd() {
		backend = new JasonBackEnd(this);
	}
	public static void main(String[] arg) {
		DavidFrontEnd demo = new DavidFrontEnd();
	}
}
