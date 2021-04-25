import java.util.List;
import java.util.Random;

public class Player {
    private String name;
    private int runs;
    private IsOut isOut;

    public Player(String name) {
        this.name = name;
        this.runs = 0;
        this.isOut = IsOut.NotOut;
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

    public IsOut getIsOut() {
        return isOut;
    }

    public void setIsOut(IsOut isOut) {
        this.isOut = isOut;
    }

    public int play(List<Integer> runList) {
        return Helpers.randomGenerate(runList);
    }
}

//out-isOut, enum

//sql---------
//use join
//use group by with count
// int size
// not null for columns
