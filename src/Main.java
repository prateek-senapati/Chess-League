import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private Schedule scheduleObject = new Schedule();
    private Table tableObject = new Table();
    private int totalMatches = 0, matchesPlayed = 0;

    private ArrayList<ChessPlayer> chessPlayers = new ArrayList<>();

    // method to initialize the list of chess players with already stored data (if the file already exists)
    private void setChessPlayers () throws IOException, ClassNotFoundException {

        File file = new File("chess-11.txt");
        if (file.exists())
        {
            FileInputStream FIS = new FileInputStream("chess-11.txt");
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            @SuppressWarnings("unchecked")
            ArrayList<ChessPlayer> playersOutput = (ArrayList<ChessPlayer>) OIS.readObject();
            chessPlayers = new ArrayList<>(playersOutput);
            OIS.close();
        }
    }

    // method to add a player to the list
    private void addPlayer () throws IOException {

        ChessPlayer player = new ChessPlayer();
        player.setDetails();
        player.serialNumber = chessPlayers.size() + 1; // sets the serial number of the player being added
        chessPlayers.add(player);
        saveDetails();
    }

    // method to save the list of registered players in a file
    private void saveDetails () throws IOException {

        FileOutputStream FOS = new FileOutputStream("chess-11.txt");
        ObjectOutputStream OOS = new ObjectOutputStream(FOS);
        OOS.writeObject(chessPlayers);
        OOS.close();
    }

    // method to print the list of registered players
    private void showPlayers () {

        if (chessPlayers.isEmpty()) {
            System.out.println("\n\nNo player has been registered yet. Please add a player first.");
            return ;
        }
        System.out.println();
        System.out.println();
        System.out.print(String.format("%20s", "Serial Number"));
        System.out.print(String.format("%25s", "Player Name"));
        System.out.print(String.format("%20s", "Student ID"));
        System.out.print(String.format("%20s", "Contact Number"));
        System.out.print(String.format("%30s", "Email ID"));
        System.out.print(String.format("%20s", "Mode of Payment"));
        System.out.print(String.format("%25s", "Fee paid? (Yes/No)"));
        System.out.println();
        for (ChessPlayer item : chessPlayers)
            item.getDetails();
    }

    // method to edit/update a particular player's registration details
    private void editPlayerDetails (int serialNumber) throws IOException {

        if (chessPlayers.isEmpty()) {
            System.out.println("\n\nNo player has been registered yet. Please add a player first.");
            return ;
        }
        // checks the validity of the serial number
        if (serialNumber < 1 || serialNumber > chessPlayers.size()) {
            System.out.println("\n\nInvalid serial number entered.");
            return ;
        }
        ChessPlayer tempPlayer = chessPlayers.get(serialNumber - 1);
        System.out.println("\n\nDetails to be edited/updated:");
        System.out.println();
        System.out.println();
        System.out.print(String.format("%20s", "Serial Number"));
        System.out.print(String.format("%25s", "Player Name"));
        System.out.print(String.format("%20s", "Student ID"));
        System.out.print(String.format("%20s", "Contact ID"));
        System.out.print(String.format("%30s", "Email ID"));
        System.out.print(String.format("%20s", "Mode of Payment"));
        System.out.print(String.format("%25s", "Fee paid? (Yes/No)"));
        System.out.println();
        tempPlayer.getDetails();
        System.out.println("\n\nEnter new details:");
        tempPlayer.setDetails();
        chessPlayers.set(serialNumber - 1, tempPlayer);
        saveDetails();
    }

    // method to find a particular player in the registration list
    private void findPlayer (String keyID) {

        boolean found = false;
        if (chessPlayers.isEmpty()) {
            System.out.println("\n\nNo player has been registered yet. Please add a player first.");
            return ;
        }
        for (ChessPlayer item : chessPlayers) {
            if (item.studentID.equals(keyID)) {
                System.out.println();
                System.out.println();
                System.out.print(String.format("%20s", "Serial Number"));
                System.out.print(String.format("%25s", "Player Name"));
                System.out.print(String.format("%20s", "Student ID"));
                System.out.print(String.format("%20s", "Contact ID"));
                System.out.print(String.format("%30s", "Email ID"));
                System.out.print(String.format("%20s", "Mode of Payment"));
                System.out.print(String.format("%25s", "Fee paid? (Yes/No)"));
                System.out.println();
                item.getDetails();
                found = true;
                break;
            }
        }
        if (!found)
            System.out.println("\n\nInvalid student ID entered.");
    }

    // method to remove a particular player from the registration list
    private void removePlayer (int serialNumber) throws IOException {

        if (chessPlayers.isEmpty()) {
            System.out.println("\n\nNo player has been registered yet. Please add a player first.");
            return ;
        }
        // checks the validity of the serial number
        if (serialNumber < 1 || serialNumber > chessPlayers.size()) {
            System.out.println("\n\nInvalid serial number entered.");
            return ;
        }
        System.out.println("\n\nDetails to be deleted:");
        System.out.println();
        System.out.println();
        System.out.print(String.format("%20s", "Serial Number"));
        System.out.print(String.format("%25s", "Player Name"));
        System.out.print(String.format("%20s", "Student ID"));
        System.out.print(String.format("%20s", "Contact ID"));
        System.out.print(String.format("%30s", "Email ID"));
        System.out.print(String.format("%20s", "Mode of Payment"));
        System.out.print(String.format("%25s", "Fee paid? (Yes/No)"));
        System.out.println();
        chessPlayers.get(serialNumber - 1).getDetails();
        chessPlayers.remove(serialNumber - 1);
        // the following for loop updates the serial numbers of the remaining players
        for (int i = serialNumber - 1; i < chessPlayers.size(); ++i) {
            chessPlayers.get(i).serialNumber = i + 1;
        }
        saveDetails();
    }
    
    // method to invoke the Coordinators' Page
    private void coordinatorsPage () {

        int choice, serialNumber, matchNumber;
        String keyID;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println();
            System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tCOORDINATORS:\n");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(1) Add a player");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(2) Show registered players");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(3) Edit/Update registered players");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(4) Remove a player");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(5) Find a player");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(6) Generate fixtures");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(7) Generate points table");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(8) Update fixtures");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(9) Show fixtures");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(10) Show points table");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(11) Log out");
            System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter your choice: ");
            choice = input.nextInt();
            switch (choice) {
                case 1: try {
                            addPlayer();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                case 2: try {
                            showPlayers();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                case 3: System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                        "Enter serial number of the player: ");
                        serialNumber = input.nextInt();
                        try {
                            editPlayerDetails(serialNumber);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                case 4: System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                        "Enter serial number of the player: ");
                        serialNumber = input.nextInt();
                        try {
                            removePlayer(serialNumber);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                case 5: input.nextLine();
                        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                            "Enter student ID: ");
                        keyID = input.nextLine();
                        findPlayer(keyID);
                        break;
                case 6: if (chessPlayers.size() == 1) {
                            System.out.println("\n\nFixtures can't be generated with only one player. " +
                                    "Please add at least one more player.");
                            break;
                        }
                        try {
                            totalMatches = scheduleObject.generateSchedule();
                            System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                                "Fixtures have been generated.");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                case 7: if (chessPlayers.size() == 1) {
                            System.out.println("\n\nPoints table can't be generated with only one player. " +
                                "Please add at least one more player.");
                            break;
                        }
                        try {
                            tableObject.generateTable();
                            System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                                "Points table has been generated.");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                case 8: if (chessPlayers.isEmpty()) {
                            System.out.println("\n\nNo player has been registered yet. Please add a player first.");
                            break ;
                        }
                        ++matchesPlayed;
                        // the following if statement checks if all the matches have been played/completed
                        if (matchesPlayed == totalMatches + 1) {
                        try {
                            System.out.println("\n\nAll the league matches have been completed. " +
                                    "Here is the final league standings:");
                            System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                                    "POINTS TABLE");
                            tableObject.showTable();
                            System.out.println("\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t" +
                                    "League Winner Details: ");
                            tableObject.getLeagueWinner();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        }
                        else {
                            System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                                "Enter match number: ");
                            matchNumber = input.nextInt();
                            try {
                                scheduleObject.updateSchedule(matchNumber);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                case 9: try {
                            System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                                "FIXTURES");
                            scheduleObject.showSchedule();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                case 10:// the following if statement checks if all the matches have been played/completed
                        if (matchesPlayed == totalMatches + 1) {
                            try {
                                System.out.println("\n\nAll the league matches have been completed. " +
                                    "Here is the final league standings:");
                                System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                                    "POINTS TABLE");
                                tableObject.showTable();
                                System.out.println("\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                                    "League Winner Details: ");
                                tableObject.getLeagueWinner();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            try {
                                System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                                    "POINTS TABLE");
                                tableObject.showTable();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
            }
        } while (choice != 11);
    }
    
    private void fixturesAndPointsTablePage () {

        int choice;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tFIXTURES AND POINTS TABLE:\n");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(1) Show fixtures");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(2) Show points table");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(3) Go back");
            System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter your choice: ");
            choice = input.nextInt();
            switch (choice) {
                case 1: try {
                            System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                                "FIXTURES");
                            scheduleObject.showSchedule();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                case 2: // the following if statement checks if all the matches have been played/completed
                        if (matchesPlayed == totalMatches + 1) {
                            try {
                                System.out.println("\n\nAll the league matches have been completed. " +
                                    "Here is the final league standings:");
                                System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                                    "POINTS TABLE");
                                tableObject.showTable();
                                System.out.println("\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                                    "League Winner Details: ");
                                tableObject.getLeagueWinner();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            try {
                                System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                                    "POINTS TABLE");
                                tableObject.showTable();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
            }
        } while (choice != 3);
    }

    public static void main (String[] args) {
        
        Main obj = new Main();
        try {
            obj.setChessPlayers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int choice;
        String userName = "chess_league", password = "ch3ckM@t3", userNameInput, passwordInput;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMENU:\n");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(1) Coordinators");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(2) Fixtures and Points Table");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(3) Exit");
            System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter your choice: ");
            choice = input.nextInt();
            switch (choice) {
                case 1: input.nextLine();
                        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                                "Enter username: ");
                        userNameInput = input.nextLine();
                        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter password: ");
                        passwordInput = input.nextLine();
                        // the following if statement checks the validity of the username and the password
                        if (userNameInput.equals(userName) && passwordInput.equals(password))
                            obj.coordinatorsPage();
                        else
                            System.out.println("\n\nWrong username or password entered.");
                        break;
                case 2: obj.fixturesAndPointsTablePage();
                        break;
            }
        } while (choice != 3);
    }
}
