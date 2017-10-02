import java.util.Scanner;

/**
 * @author marthu
 * @author sisams
 * @author raluga
 */
public class P412 {

    public static void main(String[] args) {

        int input;
        double pair, notPair;
        Scanner scanner = new Scanner(System.in);
        int[] numbers;
        while (scanner.hasNext()) {
            input = scanner.nextInt();
            if (input == 0) {
                break;
            }
            numbers = new int[input];
            pair = 0;
            notPair = 0;
            for (int i = 0; i < input; i++) {
                numbers[i] = scanner.nextInt();
            }

            for (int i = 0; i < input - 1; i++) {
                for (int j = i + 1; j < input; j++) {
                    if (gcd(numbers[i], numbers[j])) {
                        pair++;
                    } else {
                        notPair++;
                    }
                }
            }
            if (pair == 0) {
                System.out.println("No estimate for this data set.");
            } else {
                System.out.printf("%.6f\n", Math.pow(6.0 * (pair + notPair) / pair, .5));
            }
        }

    }

    private static boolean gcd(int a, int b) {
        int r;
        while (b > 0) {
            r = a % b;
            a = b;
            b = r;
        }

        return a == 1;
    }

}
