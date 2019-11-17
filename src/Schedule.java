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

    private void updateMatchDetails () {

        Scanner input = new Scanner(System.in);
        System.out.print("\nWho won the match? (black/white/draw) or (b/w/d): ");
        result = input.nextLine();
    }

    private void getMatchDetails () {

        System.out.println("\nMatch Number: " + matchNumber);
        System.out.println("Black: " + black);
        System.out.println("Black ID: " + blackID);
        System.out.println("White: " + white);
        System.out.println("White ID: " + whiteID);
        System.out.println("Result: " + result);
    }

    private void saveSchedule () throws IOException {

        FileOutputStream FOS = new FileOutputStream("schedule-6.txt");
        ObjectOutputStream OOS = new ObjectOutputStream(FOS);
        OOS.writeObject(schedule);
        OOS.close();
    }

    int generateSchedule () throws IOException, ClassNotFoundException {

        File file = new File("chess-6.txt");
        if (file.exists())
        {
            FileInputStream FIS = new FileInputStream("chess-6.txt");
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            @SuppressWarnings("unchecked")
            ArrayList<ChessPlayer> playersOutput = (ArrayList<ChessPlayer>) OIS.readObject();
            Collections.shuffle(playersOutput);
            int n = playersOutput.size();
            // your code here

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
            Collections.shuffle(schedule);
            for (int i = 0; i < schedule.size(); ++i)
                schedule.get(i).matchNumber = i + 1;
            saveSchedule();
            return (n * (n - 1)) / 2;
        }
        else {
            System.out.println("\nFile does not exist. Please add a player first.");
            return 0;
        }
    }

    void showSchedule () throws IOException, ClassNotFoundException {

        File file = new File("schedule-6.txt");
        if (file.exists())
        {
            FileInputStream FIS = new FileInputStream("schedule-6.txt");
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            @SuppressWarnings("unchecked")
            ArrayList<Schedule> scheduleOutput = (ArrayList<Schedule>) OIS.readObject();
            for (Schedule item : scheduleOutput)
                item.getMatchDetails();
            OIS.close();
        }
        else
            System.out.println("\nFile does not exist. Please add a player first.");
    }

    void updateSchedule (int matchNumber) throws IOException, ClassNotFoundException {

        File file = new File("schedule-6.txt");
        if (file.exists())
        {
                FileInputStream FIS = new FileInputStream("schedule-6.txt");
                ObjectInputStream OIS = new ObjectInputStream(FIS);
                @SuppressWarnings("unchecked")
                ArrayList<Schedule> scheduleOutput = (ArrayList<Schedule>) OIS.readObject();
                schedule = new ArrayList<>(scheduleOutput);
                if (schedule.isEmpty()) {
                    System.out.println("No player has been registered yet. Please add a player first.");
                    return;
                }
                if (matchNumber < 1 || matchNumber > schedule.size()) {
                    System.out.println("\nInvalid match number entered.");
                    return;
                }
                schedule.get(matchNumber - 1).updateMatchDetails();
                saveSchedule();
                Table t = new Table();
                t.updateTable(schedule.get(matchNumber - 1));
                OIS.close();
        }
        else
            System.out.println("\nFile does not exist. Please add a player first.");
    }
}
