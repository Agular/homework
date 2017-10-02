import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * p10324
 * @author Ragnar
 * */
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int counter = 0;
        String testString;
        while ((testString = scanner.readLine()) != null) {
            counter++;
            int noCases = Integer.parseInt(scanner.readLine());
            System.out.format("Case %d:\n", counter);
            for (int i = 0; i < noCases; i++) {
                String[] testCase = scanner.readLine().split(" ");
                int minIdx = Integer.parseInt(testCase[0]);
                int maxIdx = Integer.parseInt(testCase[1]);
                if (testCase[0].equals(testCase[1])) {
                    System.out.println("Yes");
                } else {
                    String testArea = testString.substring(Math.min(minIdx, maxIdx), Math.max(minIdx, maxIdx) + 1);
                    if (testArea.contains("1") && !testArea.contains("0")) {
                        System.out.println("Yes");
                    } else if (testArea.contains("0") && !testArea.contains("1")) {
                        System.out.println("Yes");
                    } else {
                        System.out.println("No");
                    }
                }
            }
        }
        //System.out.println();
    }
}
