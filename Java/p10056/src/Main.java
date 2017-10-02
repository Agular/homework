import java.util.Scanner;
/**
 * @author raluga
 * @author jakell
 * @author kaspar.metsa
 * */
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();
        for (int i = 0; i < cases; i++) {
            int n = scanner.nextInt();
            double p = scanner.nextDouble();
            int m = scanner.nextInt();
            int counter = 0;
            double probability = p * Math.pow(1 - p, m - 1);
            while (true) {
                counter++;
                double subProbability = p * Math.pow(1 - p, counter * n + m - 1);
                if (subProbability < Math.pow(10, -7)) {
                    probability += subProbability;
                    break;
                }
                probability += subProbability;
            }
            System.out.format("%.4f\n", probability);
        }
    }
}