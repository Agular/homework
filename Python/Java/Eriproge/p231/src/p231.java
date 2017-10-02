import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class p231 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> rockets = new ArrayList<Integer>();
        int rocket = scanner.nextInt();
        Integer[] interceptions;
        int tests = 1;
        while (rocket != -1) {
            while (rocket != -1) {
                rockets.add(rocket);
                rocket = scanner.nextInt();
            }
            interceptions = new Integer[rockets.size()];
            for (int i = 0; i < interceptions.length; i++) {
                interceptions[i] = 1;
            }

            for (int k = rockets.size() - 1; k > -1; k--) {
                for (int i = 0; i < k; i++) {
                    if (rockets.get(i) >= rockets.get(k)) {
                        interceptions[k]++;
                    }
                }
            }
            System.out.println(Arrays.toString(interceptions));
            int counter = 0;
            int temp = 0;
            for (int i = 0; i < interceptions.length; i++) {
                if (interceptions[i] > temp ){

                    counter++;
                    temp = interceptions[i];
                }
            }
            System.out.format("Test #%d:\n" +
                    "  maximum possible interceptions: %d\n", tests, counter);
            rocket = scanner.nextInt();
            rockets.clear();
            tests++;
        }
    }
}
