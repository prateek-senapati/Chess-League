import java.util.Scanner;

class ChessPlayer extends Player {

    void setData () {

        char response;
        Scanner input = new Scanner(System.in);
        System.out.println();
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

        System.out.println("\n");
        System.out.println("Serial Number: " + serialNumber);
        System.out.println("Name: " + name);
        System.out.println("Student ID: " + studentID);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Email ID: " + emailID);
        System.out.println("Mode of Payment: " + modeOfPayment);
        System.out.print("Registration fee paid?    ");
        if(paid)
            System.out.print("Yes");
        else
            System.out.print("No");
    }
}
