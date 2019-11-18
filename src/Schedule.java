import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Schedule implements Serializable {

    private int matchNumber;
    private String black, white;
    String result, blackID, whiteID;
    private ArrayList<Schedule> schedule = new ArrayList<>();

    Schedule () {

        matchNumber = 0;
        black = white = blackID = whiteID = result = "TBD";
    }

    // method to store the result of the current match
    private void updateMatchDetails () {

        Scanner input = new Scanner(System.in);
        System.out.print("\n\nWho won the match? (black/white/draw) or (b/w/d): ");
        result = input.nextLine();
    }

    // method to print a particular match's details
    private void getMatchDetails () {

        System.out.println();
        System.out.print(String.format("%15s", matchNumber));
        System.out.print(String.format("%15s", blackID));
        System.out.print(String.format("%25s", black));
        System.out.print(String.format("%25s", white));
        System.out.print(String.format("%15s", whiteID));
        System.out.print(String.format("%10s", result));
    }

    // method to save the fixtures in a file
    private void saveSchedule () throws IOException {

        FileOutputStream FOS = new FileOutputStream("schedule-11.txt");
        ObjectOutputStream OOS = new ObjectOutputStream(FOS);
        OOS.writeObject(schedule);
        OOS.close();
    }

    // method to generate the fixtures
    int generateSchedule () throws IOException, ClassNotFoundException {

        File file = new File("chess-11.txt");
        if (file.exists())
        {
            FileInputStream FIS = new FileInputStream("chess-11.txt");
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            @SuppressWarnings("unchecked")
            ArrayList<ChessPlayer> playersOutput = (ArrayList<ChessPlayer>) OIS.readObject();
            // shuffles the list of players to maintain randomness in the selection of black/white players
            Collections.shuffle(playersOutput);
            int n = playersOutput.size();
            // the following for loop generates the fixtures
            for (int i = 0; i < playersOutput.size() - 1; ++i) {
                for (int j = i + 1; j < playersOutput.size(); ++j) {
                    Schedule item = new Schedule();
                    item.black = playersOutput.get(i).name;
                    item.blackID = playersOutput.get(i).studentID;
                    item.white = playersOutput.get(j).name;
                    item.whiteID = playersOutput.get(j).studentID;
                    schedule.add(item);
                }
            }
            // shuffles the list of fixtures to maintain randomness in the schedule
            Collections.shuffle(schedule);
            for (int i = 0; i < schedule.size(); ++i)
                schedule.get(i).matchNumber = i + 1; // sets match number for the generated schedule
            saveSchedule();
            return (n * (n - 1)) / 2; // returns the total number of matches to be played
        }
        else {
            System.out.println("\n\nFile does not exist. Please add a player first.");
            return 0;
        }
    }

    // method to print the complete schedule
    void showSchedule () throws IOException, ClassNotFoundException {

        File file = new File("schedule-11.txt");
        if (file.exists())
        {
            System.out.println();
            System.out.println();
            System.out.print(String.format("%15s", "Match No"));
            System.out.print(String.format("%15s", "Black ID"));
            System.out.print(String.format("%25s", "Black"));
            System.out.print(String.format("%25s", "White"));
            System.out.print(String.format("%15s", "White ID"));
            System.out.print(String.format("%10s", "Result"));
            System.out.println();
            FileInputStream FIS = new FileInputStream("schedule-11.txt");
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            @SuppressWarnings("unchecked")
            ArrayList<Schedule> scheduleOutput = (ArrayList<Schedule>) OIS.readObject();
            for (Schedule item : scheduleOutput)
                item.getMatchDetails();
            OIS.close();
        }
        else
            System.out.println("\n\nFile does not exist. Please add a player first.");
    }

    // method to update the details of the current match
    void updateSchedule (int matchNumber) throws IOException, ClassNotFoundException {

        File file = new File("schedule-11.txt");
        if (file.exists())
        {
                FileInputStream FIS = new FileInputStream("schedule-11.txt");
                ObjectInputStream OIS = new ObjectInputStream(FIS);
                @SuppressWarnings("unchecked")
                ArrayList<Schedule> scheduleOutput = (ArrayList<Schedule>) OIS.readObject();
                schedule = new ArrayList<>(scheduleOutput);
                if (schedule.isEmpty()) {
                    System.out.println("\n\nNo player has been registered yet. Please add a player first.");
                    return;
                }
                // checks the validity of the match number
                if (matchNumber < 1 || matchNumber > schedule.size()) { 
                    System.out.println("\n\nInvalid match number entered.");
                    return;
                }
                schedule.get(matchNumber - 1).updateMatchDetails();
                saveSchedule();
                Table t = new Table();
                // updates the points table based on the current match's result
                t.updateTable(schedule.get(matchNumber - 1)); 
                OIS.close();
        }
        else
            System.out.println("\n\nFile does not exist. Please add a player first.");
    }
}
