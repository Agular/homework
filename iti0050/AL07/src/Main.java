import ee.ttu.algoritmid.stamps.Stamps;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Stamps.findStamps(200, Arrays.asList(36, 1, 2, 8, 17, 13, 7, 15, 21, 32, 24, 26, 33, 10, 27)));
        System.out.println(Stamps.findStamps(100, Arrays.asList(1, 10, 22, 24, 33, 36)));
    }
}
