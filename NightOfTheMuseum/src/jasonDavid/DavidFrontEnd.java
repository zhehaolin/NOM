package jasonDavid;
import java.util.Scanner;

public class DavidFrontEnd implements JasonSupport{
	private DavidSupport backend;
	public static Scanner sc = new Scanner(System.in);
	public static boolean win = false;
	
	public DavidFrontEnd(int size) {
		backend = new JasonBackEnd(this, size);
	}
	public static void main(String[] arg) {
		System.out.println("What size?");
		int size = sc.nextInt();
		DavidFrontEnd demo = new DavidFrontEnd(size);
		demo.play();
	}
	private void play() {	
		while(!win) {
			//while the player didnt win yet
		}
	}
}
