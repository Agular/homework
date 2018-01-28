import ee.ttu.algoritmid.tsp.MatrixLoader;
import ee.ttu.algoritmid.tsp.TSP;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        TSP tsp = new TSP();
        System.out.println("Best first:");
        List<Integer> result = tsp.bestFirst(MatrixLoader.loadFile("test.in", 4));
        System.out.println(result);
        System.out.println(tsp.getCheckedNodesCount());
        System.out.println("Depth first:");
        result = tsp.depthFirst(MatrixLoader.loadFile("test.in", 4));
        System.out.println(result);
        System.out.println(tsp.getCheckedNodesCount());
    }
}


