import java.util.Scanner;

class ChessPlayer extends Player {

    ChessPlayer () {

        serialNumber = 0;
        name = contactNumber = emailID = modeOfPayment = studentID = "TBD";
    }

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

    void getDetails () {

        System.out.print("\n\n\t\t" + serialNumber + "\t\t");
        System.out.print(name + "\t\t");
        System.out.print(studentID + "\t\t");
        System.out.print(contactNumber + "\t\t");
        System.out.print(emailID + "\t\t");
        System.out.print(modeOfPayment + "\t\t");
        if (paid)
            System.out.print("Yes");
        else
            System.out.print("No");
    }
}
