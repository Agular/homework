import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * p374.
 *
 * @author raluga
 * @author jakell
 * @author ansuku
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            long b = Long.parseLong(line);
            long p = Long.parseLong(br.readLine());
            long m = Long.parseLong(br.readLine());
            br.readLine();
            ArrayList<Long> osavastused = new ArrayList<>();
            long aste = 1;
            long alus = b % m;
            osavastused.add(alus);
            while (2 * aste <= p) {
                alus = (alus * alus) % m;
                osavastused.add(alus);
                aste = 2 * aste;
            }
            long vastus = 1;
            int counter = 0;
            while (p != 0) {
                if (p % 2 == 1) {
                    p = p / 2;
                    vastus = (vastus * osavastused.get(counter)) % m;
                    counter++;
                } else {
                    p = p / 2;
                    counter++;
                }
            }
            System.out.println(vastus % m);
        }
    }
}