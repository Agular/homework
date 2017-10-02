import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author raluga
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        while (scanner.hasNext()) {
            count++;
            String info[] = scanner.nextLine().split(" ");
            int nChambers = Integer.parseInt(info[0]);
            int nSpecimens = Integer.parseInt(info[1]);
            double averageSum = 0;
            ArrayList<Integer> specimens = new ArrayList<>();
            for (String specimen : scanner.nextLine().split(" ")) {
                specimens.add(Integer.parseInt(specimen));
                averageSum += Double.parseDouble(specimen) / (double) nChambers;
            }
            Collections.sort(specimens);
            ArrayList<String> chambers = new ArrayList<>();
            while (chambers.size() < nChambers && specimens.size() > 0) {
                if (specimens.size() % 2 == 1) {
                    chambers.add(Integer.toString(specimens.remove(specimens.size() - 1)));
                }
                chambers.add(specimens.remove(0) + " " + specimens.remove(specimens.size() - 1));
            }
            double imbalance = 0;
            for (String chamber : chambers) {
                int chamberMass;
                if (chamber.contains(" ")) {
                    String[] chambermasses = chamber.split(" ");
                    chamberMass = Integer.parseInt(chambermasses[0]) + Integer.parseInt(chambermasses[1]);
                } else {
                    chamberMass = Integer.parseInt(chamber);
                }
                imbalance += Math.abs(chamberMass - averageSum);
            }
            System.out.format("Set #%d\n", count);
            for (int i = 0; i < chambers.size(); i++) {
                System.out.format(" %d: %s\n", i, chambers.get(i));

            }
            System.out.format("IMBALANCE = %.5f\n", imbalance);
            System.out.println();
        }
    }
}
