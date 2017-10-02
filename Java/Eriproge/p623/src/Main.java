import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Created by Ragnar on 29.04.2016.
 *
 * @author raluga
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BigInteger[] factorials = new BigInteger[1001];
        factorials[0] = new BigInteger("1");
        for (int i = 1; i < 1001; i++) {
            factorials[i] = factorials[i - 1].multiply(BigInteger.valueOf(i));
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line + "!");
            System.out.println(factorials[Integer.parseInt(line)]);
        }
    }
}
