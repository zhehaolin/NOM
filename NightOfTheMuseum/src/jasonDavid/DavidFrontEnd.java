package jasonDavid;
import java.util.Scanner;
import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;

public class DavidFrontEnd implements JasonSupport{
	
	private JasonBackEnd backend;
	private String map;

	
	public DavidFrontEnd(int size) {
		backend = new JasonBackEnd(this, size);
	}

	public static void main(String[] arg) {
		System.out.println("What size?");
		//int size = CaveExplorer.in.nextInt();
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		DavidFrontEnd demo = new DavidFrontEnd(size);
		demo.play();
	}
	public void play() {	
		while(backend.stillPlaying()) {
			 displayBoard();
			 Scanner sc = new Scanner(System.in);
			 String input1 = sc.nextLine();
			 String input2 = sc.nextLine();
		     if(!backend.respondToInput(input1,input2)) {
		    	 provideHint();
		     }else {
		    	backend.respondToInput(input1,input2); 
		    	displayBoard();
		     }
		      //backend.analyzeBoard();
		    }
		  // printGameOverMessage();
			//:)
	}

	private void provideHint() {
		
	}

	private void printGameOverMessage() {
		System.out.println("Click! A sound ringed loudly signifying the completion of the puzzle.");
	}

	private String getValidUserInput() {
		 return CaveExplorer.in.nextLine();
	}

	private void displayBoard() {
		map = " ";
		//create line across top:
		for(int i = 0; i < backend.table[0].length -1; i++) {
			map += "____";//4 underscores
		}
		map+= "___\n";//3 underscores, makes the corner look symmetrical
		for(int[] row : backend.table) {
			//3 rows of text
			for(int i = 0; i < 3; i++) {
				String text = "";
				for(int cr : row) {
					text += "|";
					//contents of room depend on what row this is
					if(i==0) {
						text+="   ";//3 spaces
					}else if(i == 1) {
						if (0 < cr && cr< 10){
						text += " "+cr+" ";
						}
						if (9<cr && cr< 100){
						text += ""+cr+" ";
						}
						if (99<cr&&cr<1000){
							text+=cr;
						}
					}else if(i == 2) {
						text += "___";
					}
				}//last caveroom in row
				text+="|";
				map += text +"\n";
			}
		}
		System.out.print(map);
		System.out.print(SumOfArrays(backend.table));
	}

	private String SumOfArrays(int[][] table) {
		String rows="";
		String columns="";
		int rowcount = 1;
		int columncount = 1;
		for (int[] row : table){
			int valuer = 0;
			for (int cr : row){
				valuer += cr;
			}
		rows += "Row "+rowcount+" sum:"+valuer+"\n";
		rowcount+=1;
		}
		for (int i=0; i<table.length; i++) {
			int valuec = 0;
			for (int[] row : table) {
				valuec += row[i];
			}
			columns+= "Columns "+columncount+" sum:"+valuec+"\n";
			columncount++;
		}
		int valuetl = 0;
		for (int i=0; i<table.length;i++) {
			valuetl += table[i][i];
		}
		String tl = "Top left to bottom right sum:"+valuetl;
		int valuetr = 0;
		int j = table.length-1;
		for (int i=0; i<table.length;i++) {
				valuetr += table[i][j];
				j-=1;
		}
		String tr = "Top right to bottom left sum:"+valuetr;
		String finito = rows+"\n"+columns+"\n"+tl+"\n"+tr;
		return finito;
	}
	private boolean checkForNext(int xcoord, int ycoord) {
		int[][] table = backend.table;
		if (table[ycoord-1][xcoord] < 0) {
			if (table[table.length-1][xcoord+1] < 0){
				if (table[table.length-1][0] == table[ycoord][xcoord]+1) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if (table[table.length-1][xcoord+1]==table[ycoord][xcoord]+1) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		if (table[ycoord][xcoord+1]<0) {
			if (table[ycoord-1][0]<0) {
				if(table[table.length-1][0]==table[ycoord][xcoord]+1) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if (table[ycoord-1][0] == table[ycoord][xcoord]+1) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		if (table[ycoord][xcoord] == 1+table[ycoord-1][xcoord+1]) {
			return true;
		}
			return false;
	}
}
