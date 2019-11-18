import java.util.Scanner;

class ChessPlayer extends Player {

    ChessPlayer () {

        serialNumber = 0;
        name = contactNumber = emailID = modeOfPayment = studentID = "TBD";
    }

    // method to set individual player's registration details
    void setDetails () {

        String response;
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter player name: ");
        name = input.nextLine();
        System.out.print("Enter student ID: ");
        studentID = input.nextLine();
        System.out.print("Enter contact number: ");
        contactNumber = input.nextLine();
        System.out.print("Enter email ID: ");
        emailID = input.nextLine();
        System.out.print("Enter mode of payment (Cash/GPay/PayTM): ");
        modeOfPayment = input.nextLine();
        System.out.print("Did the player pay the registration fee? (y/n) or (yes/no): ");
        response = input.nextLine();
        paid = (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y"));
    }

    // method to print individual player's registration details
    void getDetails () {

        System.out.println();
        System.out.print(String.format("%20s", serialNumber));
        System.out.print(String.format("%25s", name));
        System.out.print(String.format("%20s", studentID));
        System.out.print(String.format("%20s", contactNumber));
        System.out.print(String.format("%30s", emailID));
        System.out.print(String.format("%20s", modeOfPayment));
        if (paid)
            System.out.print("\t\t\t\tYes");
        else
            System.out.print("\t\t\t\tNo");
    }
}
