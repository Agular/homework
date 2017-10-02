import java.util.Scanner;

/**
 * Created by Ragnar on 29.02.2016.
 *
 * @author: raluga
 */
class p729 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer cases = Integer.parseInt(scanner.nextLine());
        String line;
        int length, hamming, maxValue;
        int counter = 0;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (!line.equals("")) {
                counter++;
                String[] info = line.split(" ");
                length = Integer.parseInt(info[0]);
                hamming = Integer.parseInt(info[1]);
                maxValue = (int) Math.pow(2.0, (double) length);
                String format = "%" + length + "s";
                for (int i = 0; i < maxValue; i++) {
                    if (Integer.bitCount(i) == hamming) {
                        System.out.println(String.format(format, Integer.toBinaryString(i)).replace(' ', '0'));
                    }
                }
                if (counter < cases) {
                    System.out.println();
                }
            }

        }
    }
}
