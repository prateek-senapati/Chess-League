import java.util.Scanner;
import java.io.*;

public class ChessPlayer extends Player {

    void setData () {

        char response;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter player name: ");
        name = input.nextLine();
        System.out.print("Enter student ID: ");
        studentID = input.nextLine();
        System.out.print("Enter contact number: ");
        contactNumber = input.nextLine();
        System.out.print("Enter email ID: ");
        emailID = input.nextLine();
        System.out.print("Enter mode of payment: ");
        modeOfPayment = input.nextLine();
        System.out.print("Did the player pay the registration fee? (Y/N): ");
        response = input.next().charAt(0);
        paid = (response == 'Y');
    }

    void getData () {

        System.out.println();
        System.out.println("Name: " + name);
        System.out.println("Student ID: " + studentID);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Email ID: " + emailID);
        System.out.println("Mode of Payment: " + modeOfPayment);
        System.out.println("Registration fee paid?    ");
        if(paid)
            System.out.print("Yes");
        else
            System.out.println("No");
    }

    public static void main (String[] args) throws IOException, ClassNotFoundException {

        ChessPlayer playerInput = new ChessPlayer();
        playerInput.setData();
        FileOutputStream FOS = new FileOutputStream("chess-league.ser");
        ObjectOutputStream OOS = new ObjectOutputStream(FOS);
        OOS.writeObject(playerInput);
        OOS.close();

        FileInputStream FIS = new FileInputStream("chess-league.ser");
        ObjectInputStream OIS = new ObjectInputStream(FIS);
        ChessPlayer playerOutput = (ChessPlayer) OIS.readObject();
        OIS.close();
        playerOutput.getData();
    }
}
