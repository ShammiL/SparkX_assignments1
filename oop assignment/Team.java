import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;

public class Team {
    private String name;
    private Queue<Player> players;
    private int marks;

    public Team(String name) {
        this.name = name;
        this.players = new LinkedList<>();
        this.marks = 0;
    }

    public String getName() {
        return name;
    }

    public void addPlayers(int numPlayers, String teamName) {
        for (int p = 1; p <= numPlayers; p++) {
            String playerName = teamName + String.valueOf(p);
            System.out.println("Player " + String.valueOf(p) + ":" + playerName);
            Player player = new Player(playerName);
            this.players.add(player);
        }

    }

    public Queue<Player> getPlayers() {
        return players;
    }

    public void addMarks(int runs) {
        marks = marks + runs;
    }

    public int getMarks() {
        return marks;
    }
}
