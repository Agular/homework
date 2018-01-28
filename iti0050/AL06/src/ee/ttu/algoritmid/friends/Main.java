package ee.ttu.algoritmid.friends;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AL06 friendGraph = new AL06();
        List<SimpleEntry<Integer, Integer>> friendEdges = Arrays.asList(
                new SimpleEntry<Integer, Integer>(1, 2),
                new SimpleEntry<Integer, Integer>(2, 3),
                new SimpleEntry<Integer, Integer>(3, 4)
        );
        SimpleEntry<Integer, List<Integer>> result =
                friendGraph.buildGraphAndFindLink(friendEdges, new SimpleEntry<Integer, Integer>(1, 4));

        System.out.println("Distance: " + result.getKey());
        System.out.println("Edges: " + result.getValue());
    }
}
