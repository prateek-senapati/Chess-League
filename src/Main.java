import java.io.*;
import java.util.Scanner;
import java.lang.*;

public class Main {

    private void addPlayer () throws IOException {

        ChessPlayer playerInput = new ChessPlayer();
        playerInput.setData();
        boolean exists = new File("chess-league-1.0.txt").exists();
        FileOutputStream FOS = new FileOutputStream("chess-league-1.0.txt", true);
        ObjectOutputStream OOS = exists ? new ObjectOutputStream(FOS) {
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                }:new ObjectOutputStream(FOS);
        OOS.writeObject(playerInput);
        OOS.close();
    }

    private void showPlayers () throws IOException {

        FileInputStream FIS = new FileInputStream("chess-league-1.0.txt");
        ObjectInputStream OIS = new ObjectInputStream(FIS);
        while(FIS.available() > 0) {
            try {
                ChessPlayer playerOutput = (ChessPlayer) OIS.readObject();
                playerOutput.getData();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        OIS.close();
    }

    public static void main (String[] args) throws IOException {

        Main obj = new Main();
        int choice;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n\nMENU:\n");
            System.out.println("(1) Add a player");
            System.out.println("(2) Show registered players");
            System.out.println("(3) Exit");
            System.out.print("\n\nEnter your choice: ");
            choice = input.nextInt();
            switch (choice) {
                case 1: obj.addPlayer();
                        break;
                case 2: obj.showPlayers();
                        break;
            }
        } while (choice != 3);
    }
}
