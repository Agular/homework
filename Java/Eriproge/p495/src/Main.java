import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Ragnar on 21.03.2016.
 * p495
 *
 * @author raluga
 */
class Main {
    public static void main(String[] args) {
        List<BigInteger> nums = new ArrayList<>();
        nums.add(BigInteger.ZERO);
        nums.add(BigInteger.ONE);
        Scanner scanner = new Scanner(System.in);
        int idx = 1;
        while (nums.size() < 5001) {
            nums.add(nums.get(idx).add(nums.get(idx - 1)));
            idx++;
        }
        while (scanner.hasNextInt()) {
            int fib = scanner.nextInt();
            System.out.format("The Fibonacci number for %d is %d\n", fib, nums.get(fib));
        }
    }
}
