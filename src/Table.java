import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class Table implements Serializable, Comparable<Table> {

    private int position, played, won, lost, drawn, points;
    private String playerName, playerID;
    private ArrayList<Table> table = new ArrayList<>();

    Table () {

        position = played = won = lost = drawn = points = 0;
        playerName = playerID = "TBD";
    }

    private Integer getPoints () { return points; }

    @Override
    public int compareTo (Table t) {

        return this.getPoints().compareTo(t.getPoints());
    }

    void getLeagueWinner () throws IOException, ClassNotFoundException {

        File file = new File("table-6.txt");
        if (file.exists())
        {
            FileInputStream FIS = new FileInputStream("table-6.txt");
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            @SuppressWarnings("unchecked")
            ArrayList<Table> tableOutput = (ArrayList<Table>) OIS.readObject();
            tableOutput.get(0).getPlayerStats();
            OIS.close();
        }
        else
            System.out.println("\nFile does not exist. Please add a player first.");
    }

    private void getPlayerStats () {

        System.out.println("\nPosition: " + position);
        System.out.println("Player Name: " + playerName);
        System.out.println("Player Student ID: " + playerID);
        System.out.println("Matches played: " + played);
        System.out.println("Matches won: " + won);
        System.out.println("Matches lost: " + lost);
        System.out.println("Matches drawn: " + drawn);
        System.out.println("Points: " + points);
    }

    private void saveTable () throws IOException {

        FileOutputStream FOS = new FileOutputStream("table-6.txt");
        ObjectOutputStream OOS = new ObjectOutputStream(FOS);
        OOS.writeObject(table);
        OOS.close();
    }

    void generateTable () throws IOException, ClassNotFoundException {

        File file = new File("chess-6.txt");
        if (file.exists())
        {
            FileInputStream FIS = new FileInputStream("chess-6.txt");
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            @SuppressWarnings("unchecked")
            ArrayList<ChessPlayer> playersOutput = (ArrayList<ChessPlayer>) OIS.readObject();
            // your code here
            for (ChessPlayer item : playersOutput) {
                Table obj = new Table();
                obj.playerName = item.name;
                obj.playerID = item.studentID;
                table.add(obj);
            }
            for (int i = 0; i < table.size(); ++i)
                table.get(i).position = i + 1;
            saveTable();
        }
        else
            System.out.println("\nFile does not exist. Please add a player first.");
    }

    void showTable () throws IOException, ClassNotFoundException {

        File file = new File("table-6.txt");
        if (file.exists())
        {
            FileInputStream FIS = new FileInputStream("table-6.txt");
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            @SuppressWarnings("unchecked")
            ArrayList<Table> tableOutput = (ArrayList<Table>) OIS.readObject();
            for (Table item : tableOutput)
                item.getPlayerStats();
            OIS.close();
        }
        else
            System.out.println("\nFile does not exist. Please add a player first.");
    }

    void updateTable (Schedule item) throws IOException, ClassNotFoundException {

        File file = new File("table-6.txt");
        if (file.exists()) {
            FileInputStream FIS = new FileInputStream("table-6.txt");
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            @SuppressWarnings("unchecked")
            ArrayList<Table> tableOutput = (ArrayList<Table>) OIS.readObject();
            table = new ArrayList<>(tableOutput);
            if (table.isEmpty()) {
                System.out.println("No player has been registered yet. Please add a player first.");
                return ;
            }
                if (item.result.equalsIgnoreCase("black")
                        || item.result.equalsIgnoreCase("b")) {
                    for (Table object : table) {
                        if (object.playerID.equals(item.blackID)) {
                            ++object.played;
                            ++object.won;
                            object.points += 3;
                        }
                        else if (object.playerID.equals(item.whiteID)) {
                            ++object.played;
                            ++object.lost;
                        }
                    }
                }
                else if (item.result.equalsIgnoreCase("white")
                        || item.result.equalsIgnoreCase("w")) {
                    for (Table object : table) {
                        if (object.playerID.equals(item.whiteID)) {
                            ++object.played;
                            ++object.won;
                            object.points += 3;
                        }
                        else if (object.playerID.equals(item.blackID)) {
                            ++object.played;
                            ++object.lost;
                        }
                    }
                }
                else if (item.result.equalsIgnoreCase("draw")
                        || item.result.equalsIgnoreCase("d")) {
                    for (Table object : table) {
                        if (object.playerID.equals(item.blackID)
                                || object.playerID.equals(item.whiteID)) {
                            ++object.played;
                            ++object.drawn;
                            object.points += 1;
                        }
                    }
                }
            Collections.sort(table, Collections.reverseOrder());
            for (int i = 0; i < table.size(); ++i)
                table.get(i).position = i + 1;
            saveTable();
            OIS.close();
        }
        else
            System.out.println("\nFile does not exist. Please add a player first.");
    }
}
