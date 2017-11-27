package jasonDavid;
import java.util.Scanner;
import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.Inventory;

public class DavidFrontEnd implements JasonSupport{
	
	private static JasonBackEnd backend;
	private String map;

	
	public DavidFrontEnd(int size) {
		backend = new JasonBackEnd(this, size);
	}

	public static void main(String[] arg) {
		System.out.println("You look around the room, and you see a display that asks you, what size is your magic square? (Please input an odd number between 1 and 31)");
		//int size = CaveExplorer.in.nextInt();
		Scanner sc = new Scanner(System.in);
		int size = getValidSize();
		DavidFrontEnd demo = new DavidFrontEnd(size);
		demo.play(size);
	}
	public void play(int size) {
        while(backend.stillPlaying()) {
            backend.analyzeBoard();
             displayBoard();
             if (backend.playing == false) {
                    break;
                }
             Scanner sc = new Scanner(System.in);
             String input1 = sc.nextLine();
             if (input1.equals("getgood")) {
                 backend.playing = false;
             }
             else {
                 String input2 = sc.nextLine();
                 if(backend.respondToInput(input1,input2)) {
                        backend.performSwap(input1, input2);
                    }
                 if (input1.equals("help")&&backend.isValidCoord(input2)) {
                     int coord1 = Integer.parseInt(input2.substring(0,1));
                     int coord2 = Integer.parseInt(input2.substring(2,3));
                     provideHint(coord2, coord1, size);
                 }
                 else { 
                     if(!backend.respondToInput(input1,input2)) {
                         System.out.println("Please type in valid coordinates in this format ('x,y') or type 'help' and the selected coord after.");
                 }

                 }
                 backend.analyzeBoard();
             }
    }
        printGameOverMessage();
        Inventory.Obtainkeys();
    }

	public void provideHint(int x, int y, int size) {
		int[][] table = backend.table;
		int[][] finishedtable = finishedBoard(size);
		int[] found;
		loop:{
		for (int i = 0; i<size; i++) {
			for (int j = 0; j<size; j++) {
				if (table[y][x] == finishedtable[i][j]) {
					backend.swap(table, y, x, i, j);
					System.out.println("Swapped "+y+","+x+" and "+i+","+j);
					break loop;
				}
			}
		}
		}
		}
	private static int[][] finishedBoard(int boardSize) {
        int[][] magic = new int[boardSize][boardSize];

        int row = boardSize-1;
        int col = boardSize/2;
        magic[row][col] = 1;

        for (int i = 2; i <= boardSize*boardSize; i++) {
            if (magic[(row + 1) % boardSize][(col + 1) % boardSize] == 0) {
                row = (row + 1) % boardSize;
                col = (col + 1) % boardSize;
            }
            else {
                row = (row - 1 + boardSize) % boardSize;
            }
            magic[row][col] = i;
        }
        return magic;
    }
		
	
	private void printGameOverMessage() {
		System.out.println("\nClick! A sound ringed loudly signifying the completion of the puzzle.");
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
	public static int getValidSize(){
        Scanner sc = new Scanner(System.in);
        String size = sc.nextLine();
        while(!JasonBackEnd.isNum(size) || Integer.parseInt(size)%2 != 1){
                System.out.println("Try again");
                size = sc.nextLine();
        }
        return Integer.parseInt(size);
    }
}
