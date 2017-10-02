import java.util.Scanner;
/**
 * @author: raluga
 * */
public class p443 {
    public static void main(String[] args) {
        int[] humble_numbers = new int[5842];
        humble_numbers[0] = 1;
        int two = 0;
        int three = 0;
        int five = 0;
        int seven = 0;
        for (int i = 1; i < 5842; i++) {
            humble_numbers[i] = Math.min(Math.min(2 * humble_numbers[two], 3 * humble_numbers[three]),
                    Math.min(5 * humble_numbers[five], 7 * humble_numbers[seven]));
            if (humble_numbers[i] == 2 * humble_numbers[two]) {
                two++;
            }
            if (humble_numbers[i] == 3 * humble_numbers[three]) {
                three++;
            }
            if (humble_numbers[i] == 5 * humble_numbers[five]) {
                five++;
            }
            if (humble_numbers[i] == 7 * humble_numbers[seven]) {
                seven++;
            }
        }
        String suffix = "";
        Scanner scanner = new Scanner(System.in);
        int nth = scanner.nextInt();
        while (nth != 0) {
            if (nth % 10 == 1 && (nth / 10) % 10 != 1) {
                suffix = "st";
            } else if (nth % 10 == 2 && (nth / 10) % 10 != 1) {
                suffix = "nd";
            } else if (nth % 10 == 3 && (nth / 10) % 10 != 1){
                suffix = "rd";
            }
            else{
                suffix = "th";
            }
            System.out.format("The %d%s humble number is %d.\n", nth, suffix, humble_numbers[nth - 1]);
            nth = scanner.nextInt();
        }
    }
}
