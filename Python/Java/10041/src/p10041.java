import java.util.Arrays;
import java.util.Scanner;
/**
 * @author: raluga
 * */
class p10041 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = Integer.parseInt(scanner.nextLine());
        String[] str_numbers;
        Integer[] int_numbers;
        int median;
        for (int i = 0; i < testCases; i++) {
            int sum = 0;
            str_numbers = scanner.nextLine().split(" ");
            int_numbers = new Integer[str_numbers.length - 1];
            for (int k = 1; k < str_numbers.length; k++) {
                int_numbers[k - 1] = Integer.parseInt(str_numbers[k]);
            }
            Arrays.sort(int_numbers);
            median = int_numbers[int_numbers.length / 2];
            for (int j = 0; j < int_numbers.length; j++) {
                sum += Math.abs(median - int_numbers[j]);
            }
            System.out.println(sum);
        }
    }
}
