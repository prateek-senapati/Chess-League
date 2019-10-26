import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    private ArrayList<ChessPlayer> chessPlayers = new ArrayList<>();

    private void addPlayer () {

        ChessPlayer playerInput = new ChessPlayer();
        playerInput.setData();
        playerInput.serialNumber = chessPlayers.size() + 1;
        chessPlayers.add(playerInput);
    }

    private void saveDetails () throws IOException {
        try {
        	FileOutputStream FOS = new FileOutputStream("chess-league.txt");
        	ObjectOutputStream OOS = new ObjectOutputStream(FOS);
        	OOS.writeObject(chessPlayers);
	}
	catch( IOException e ) {
		e.printStackTrace();
	}
	finally {
		OOS.close();
	}
    }

    private void showPlayers () throws IOException, ClassNotFoundException {
	try {
        	FileInputStream FIS = new FileInputStream("chess-league.txt");
        	ObjectInputStream OIS = new ObjectInputStream(FIS);
        	@SuppressWarnings("unchecked")
        	ArrayList<ChessPlayer> playerOutput = (ArrayList<ChessPlayer>) OIS.readObject();
        	for(ChessPlayer item : playerOutput) {
            		item.getData();
        	}
	}
		catch(ClassNotFoundException e) {
			e.printStackTrace(); 
		}
	}
	catch(IOException e) {
		e.printStackTrace();
	}
	finally {
        	OIS.close();
	}
    }

    private void editPlayerDetails (int serialNumber) {

        ChessPlayer tempPlayer = chessPlayers.get(serialNumber - 1);
        System.out.print("\nDetails to be edited/updated:");
        tempPlayer.getData();
        System.out.println("\n\nEnter new details:");
        tempPlayer.setData();
        chessPlayers.set(serialNumber - 1, tempPlayer);
    }

    public static void main (String[] args) throws IOException {

        Main obj = new Main();
        int choice, serialNumber;
	try {
        	Scanner input = new Scanner(System.in);
        	do {
           	 System.out.println("\n\nMENU:\n");
            	System.out.println("(1) Add a player");
            	System.out.println("(2) Show registered players");
            	System.out.println("(3) Edit/Update registered players");
            	System.out.println("(4) Exit");
            	System.out.print("\n\nEnter your choice: ");
            	choice = input.nextInt();
            	switch (choice) {
                	case 1: obj.addPlayer();
                        	obj.saveDetails();
                        	break;
                	case 2: try {
                            	obj.showPlayers();
                        	} catch (ClassNotFoundException e) {
                            	e.printStackTrace();
                        }
                        	break;
                	case 3: System.out.print("Enter serial number of the player: ");
                        	serialNumber = input.nextInt();
                        	obj.editPlayerDetails(serialNumber);
                        	obj.saveDetails();
                        	break;
            	}
       	   } while (choice != 4);
    	}
}
