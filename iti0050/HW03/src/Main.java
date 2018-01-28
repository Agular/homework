import ee.ttu.algoritmid.labyrinth.HW03;

import java.io.IOException;
import java.net.URISyntaxException;


public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {

        HW03 mazeSolver = new HW03("maze100.txt");
        System.out.println(mazeSolver.solve());
    }
}
