import java.util.List;
import java.util.Random;

public class Player {
    private String name;
    private int runs;
    private String out;

    public Player(String name) {
        this.name = name;
        this.runs = 0;
        this.out = "not out";
    }

    public String getName() {
        return name;
    }

    public void addRuns(int run) {
        runs = runs + run;
    }

    public int getRuns() {
        return runs;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public int play(List<Integer> runList) {
        Random rand = new Random();
        return runList.get(rand.nextInt(runList.size()));
    }
}
