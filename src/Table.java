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

    // method to return individual player's points
    private Integer getPoints () { return points; }

    // method to compare two players based on their points
    @Override
    public int compareTo (Table t) {

        return this.getPoints().compareTo(t.getPoints());
    }

    // method to print points table details of the league winner
    void getLeagueWinner () throws IOException, ClassNotFoundException {

        File file = new File("table-11.txt");
        if (file.exists())
        {
            System.out.println();
            System.out.print(String.format("%10s", "Position"));
            System.out.print(String.format("%25s", "Player Name"));
            System.out.print(String.format("%15s", "Student ID"));
            System.out.print(String.format("%10s", "Played"));
            System.out.print(String.format("%10s", "Won"));
            System.out.print(String.format("%10s", "Lost"));
            System.out.print(String.format("%10s", "Drawn"));
            System.out.print(String.format("%10s", "Points"));
            System.out.println();
            FileInputStream FIS = new FileInputStream("table-11.txt");
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            @SuppressWarnings("unchecked")
            ArrayList<Table> tableOutput = (ArrayList<Table>) OIS.readObject();
            tableOutput.get(0).getPlayerStats();
            OIS.close();
        }
        else
            System.out.println("\n\nFile does not exist. Please add a player first.");
    }

    // method to print individual player's points table details
    private void getPlayerStats () {

        System.out.println();
        System.out.print(String.format("%10s", position));
        System.out.print(String.format("%25s", playerName));
        System.out.print(String.format("%15s", playerID));
        System.out.print(String.format("%10s", played));
        System.out.print(String.format("%10s", won));
        System.out.print(String.format("%10s", lost));
        System.out.print(String.format("%10s", drawn));
        System.out.print(String.format("%10s", points));
    }

    // method to save points table in a file
    private void saveTable () throws IOException {

        FileOutputStream FOS = new FileOutputStream("table-11.txt");
        ObjectOutputStream OOS = new ObjectOutputStream(FOS);
        OOS.writeObject(table);
        OOS.close();
    }

    // method to generate points table
    void generateTable () throws IOException, ClassNotFoundException {

        File file = new File("chess-11.txt");
        if (file.exists())
        {
            FileInputStream FIS = new FileInputStream("chess-11.txt");
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            @SuppressWarnings("unchecked")
            ArrayList<ChessPlayer> playersOutput = (ArrayList<ChessPlayer>) OIS.readObject();
            for (ChessPlayer item : playersOutput) {
                Table obj = new Table();
                obj.playerName = item.name;
                obj.playerID = item.studentID;
                table.add(obj);
            }
            for (int i = 0; i < table.size(); ++i)
                table.get(i).position = i + 1;  // sets initial positions of players in the points table
            saveTable();
        }
        else
            System.out.println("\n\nFile does not exist. Please add a player first.");
    }

    // method to print points table
    void showTable () throws IOException, ClassNotFoundException {

        File file = new File("table-11.txt");
        if (file.exists())
        {
            System.out.println();
            System.out.println();
            System.out.print(String.format("%10s", "Position"));
            System.out.print(String.format("%25s", "Player Name"));
            System.out.print(String.format("%15s", "Student ID"));
            System.out.print(String.format("%10s", "Played"));
            System.out.print(String.format("%10s", "Won"));
            System.out.print(String.format("%10s", "Lost"));
            System.out.print(String.format("%10s", "Drawn"));
            System.out.print(String.format("%10s", "Points"));
            System.out.println();
            System.out.println();
            FileInputStream FIS = new FileInputStream("table-11.txt");
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            @SuppressWarnings("unchecked")
            ArrayList<Table> tableOutput = (ArrayList<Table>) OIS.readObject();
            for (Table item : tableOutput)
                item.getPlayerStats();
            OIS.close();
        }
        else
            System.out.println("\n\nFile does not exist. Please add a player first.");
    }

    // method to update points table based on current match's result
    void updateTable (Schedule item) throws IOException, ClassNotFoundException {

        File file = new File("table-11.txt");
        if (file.exists()) {
            FileInputStream FIS = new FileInputStream("table-11.txt");
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            @SuppressWarnings("unchecked")
            ArrayList<Table> tableOutput = (ArrayList<Table>) OIS.readObject();
            table = new ArrayList<>(tableOutput);
            if (table.isEmpty()) {
                System.out.println("\n\nNo player has been registered yet. Please add a player first.");
                return ;
            }
                // the following if-else block assigns points to players
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
            // sorts the points table in decreasing order of points
            Collections.sort(table, Collections.reverseOrder());
            for (int i = 0; i < table.size(); ++i)
                table.get(i).position = i + 1; // updates positions of players in the points table
            saveTable();
            OIS.close();
        }
        else
            System.out.println("\n\nFile does not exist. Please add a player first.");
    }
}
