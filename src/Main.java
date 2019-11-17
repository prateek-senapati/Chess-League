import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private Schedule scheduleObject = new Schedule();
    private Table tableObject = new Table();
    private int totalMatches = 0, matchesPlayed = 0;

    private ArrayList<ChessPlayer> chessPlayers = new ArrayList<>();

    private void setChessPlayers () throws IOException, ClassNotFoundException {

        File file = new File("chess-6.txt");
        if (file.exists())
        {
            FileInputStream FIS = new FileInputStream("chess-6.txt");
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            @SuppressWarnings("unchecked")
            ArrayList<ChessPlayer> playersOutput = (ArrayList<ChessPlayer>) OIS.readObject();
            chessPlayers = new ArrayList<>(playersOutput);
            OIS.close();
        }
    }

    private void addPlayer () throws IOException {

        ChessPlayer player = new ChessPlayer();
        player.setDetails();
        player.serialNumber = chessPlayers.size() + 1;
        chessPlayers.add(player);
        saveDetails();
    }

    private void saveDetails () throws IOException {

        FileOutputStream FOS = new FileOutputStream("chess-6.txt");
        ObjectOutputStream OOS = new ObjectOutputStream(FOS);
        OOS.writeObject(chessPlayers);
        OOS.close();
    }

    private void showPlayers () {

        if (chessPlayers.isEmpty()) {
            System.out.println("No player has been registered yet. Please add a player first.");
            return ;
        }
        System.out.println();
        System.out.println("Serial Number\tPlayer Name\tStudent ID\tContact Number" +
                "\tEmail ID\tMode of Payment\tPaid? (Yes/No)");
        System.out.println();
        for (ChessPlayer item : chessPlayers)
            item.getDetails();
    }

    private void editPlayerDetails (int serialNumber) throws IOException {

        if (chessPlayers.isEmpty()) {
            System.out.println("No player has been registered yet. Please add a player first.");
            return ;
        }
        if (serialNumber < 1 || serialNumber > chessPlayers.size()) {
            System.out.println("\nInvalid serial number entered.");
            return ;
        }
        ChessPlayer tempPlayer = chessPlayers.get(serialNumber - 1);
        System.out.println("\nDetails to be edited/updated:");
        tempPlayer.getDetails();
        System.out.println("\nEnter new details:");
        tempPlayer.setDetails();
        chessPlayers.set(serialNumber - 1, tempPlayer);
        saveDetails();
    }

    private void findPlayer (String keyID) {

        boolean found = false;
        if (chessPlayers.isEmpty()) {
            System.out.println("No player has been registered yet. Please add a player first.");
            return ;
        }
        for (ChessPlayer item : chessPlayers) {
            if (item.studentID.equals(keyID)) {
                item.getDetails();
                found = true;
                break;
            }
        }
        if (!found)
            System.out.println("Invalid student ID entered.\n");
    }

    private void removePlayer (int serialNumber) throws IOException {

        if (chessPlayers.isEmpty()) {
            System.out.println("No player has been registered yet. Please add a player first.");
            return ;
        }
        if (serialNumber < 1 || serialNumber > chessPlayers.size()) {
            System.out.println("\nInvalid serial number entered.");
            return ;
        }
        System.out.println("\nDetails to be deleted:");
        chessPlayers.get(serialNumber - 1).getDetails();
        chessPlayers.remove(serialNumber - 1);
        for (int i = serialNumber - 1; i < chessPlayers.size(); ++i) {
            chessPlayers.get(i).serialNumber = i + 1;
        }
        saveDetails();
    }
    
    private void coordinatorsPage () {

        int choice, serialNumber, matchNumber;
        String keyID;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n\nCOORDINATORS:\n");
            System.out.println("(1) Add a player");
            System.out.println("(2) Show registered players");
            System.out.println("(3) Edit/Update registered players");
            System.out.println("(4) Remove a player");
            System.out.println("(5) Find a player");
            System.out.println("(6) Generate fixtures");
            System.out.println("(7) Update fixtures");
            System.out.println("(8) Generate points table");
            System.out.println("(9) Show fixtures");
            System.out.println("(10) Show points table");
            System.out.println("(11) Exit");
            System.out.print("\nEnter your choice: ");
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
                case 3: System.out.print("Enter serial number of the player: ");
                    serialNumber = input.nextInt();
                    try {
                        editPlayerDetails(serialNumber);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4: System.out.print("Enter serial number of the player: ");
                    serialNumber = input.nextInt();
                    try {
                        removePlayer(serialNumber);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5: input.nextLine();
                    System.out.print("Enter student ID: ");
                    keyID = input.nextLine();
                    findPlayer(keyID);
                    break;
                case 6: try {
                    totalMatches = scheduleObject.generateSchedule();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                    break;
                case 7: ++matchesPlayed;
                    if (matchesPlayed == totalMatches + 1) {
                        try {
                            System.out.println("\nAll the league matches have been completed. " +
                                    "Here is the final league standings:");
                            tableObject.showTable();
                            System.out.println("\nLeague Winner Details: ");
                            tableObject.getLeagueWinner();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        System.out.print("Enter match number: ");
                        matchNumber = input.nextInt();
                        try {
                            scheduleObject.updateSchedule(matchNumber);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 8:try {
                    tableObject.generateTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                    break;
                case 9: try {
                    scheduleObject.showSchedule();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                    break;
                case 10:if (matchesPlayed == totalMatches + 1) {
                    try {
                        System.out.println("\nAll the league matches have been completed. " +
                                "Here is the final league standings:");
                        tableObject.showTable();
                        System.out.println("\nLeague Winner Details: ");
                        tableObject.getLeagueWinner();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
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
            System.out.println("\n\nFIXTURES AND POINTS TABLE:\n");
            System.out.println("(1) Show fixtures");
            System.out.println("(2) Show points table");
            System.out.println("(3) Go Back");
            System.out.print("\nEnter your choice: ");
            choice = input.nextInt();
            switch (choice) {
                case 1: try {
                        scheduleObject.showSchedule();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                case 2: if (matchesPlayed == totalMatches + 1) {
                    try {
                        System.out.println("\nAll the league matches have been completed. " +
                                "Here is the final league standings:");
                        tableObject.showTable();
                        System.out.println("\nLeague Winner Details: ");
                        tableObject.getLeagueWinner();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
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
            System.out.println("\n\nMENU:\n");
            System.out.println("(1) Coordinators");
            System.out.println("(2) Fixtures and Points Table");
            System.out.println("(3) Exit");
            System.out.print("\nEnter your choice: ");
            choice = input.nextInt();
            switch (choice) {
                case 1: // login
                        input.nextLine();
                        System.out.print("\nEnter username: ");
                        userNameInput = input.nextLine();
                        System.out.print("\nEnter password: ");
                        passwordInput = input.nextLine();
                        if (userNameInput.equals(userName) && passwordInput.equals(password))
                            obj.coordinatorsPage();
                        else
                            System.out.println("\nWrong username or password entered.");
                        break;
                case 2: obj.fixturesAndPointsTablePage();
                        break;
            }
        } while (choice != 3);
    }
}
