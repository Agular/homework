import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Ragnar
 * */
class Main {
    public static void main(String[] args) throws IOException {
        long[] fibbonaci = new long[51];
        fibbonaci[0] = 2;
        fibbonaci[1] = 3;
        for (int i = 2; i < 51; i++) {
            fibbonaci[i] = fibbonaci[i - 1] + fibbonaci[i - 2];
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(reader.readLine());
        for (int i = 1; i < cases + 1; i++) {
            int input = Integer.parseInt(reader.readLine());
            System.out.format("Scenario #%d:\n%d\n\n", i, fibbonaci[input - 1]);
        }
    }
}
