import ee.ttu.algoritmid.tsp.GreedyTSP;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Arrays.stream(GreedyTSP.greedySolution(new int[][]{
                {0, 4, 1},
                {3, 0, 5},
                {2, 6, 0}
        })).forEach(System.out::println);
    }
}
