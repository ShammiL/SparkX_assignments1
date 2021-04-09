import java.lang.reflect.Array;

public class Team {
    private String name;
    private Player[] players;
    private int marks;

    public Team(String name) {
        this.name = name;
        this.players = new Player[6];
        this.marks = 0;
    }

    public String getName() {
        return name;
    }

    public void addPlayers(Player player, int p) {
        players[p] = player;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void addMarks(int runs) {
        marks = marks + runs;
    }

    public int getMarks() {
        return marks;
    }
}
