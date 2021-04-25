import java.util.List;
import java.util.Random;

public class Helpers {

    public static int randomGenerate(List<Integer> numList) {
        Random rand = new Random();
        return numList.get(rand.nextInt(numList.size()));
    }
}
