import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("0 0")) {
                break;
            }
            String[] info = line.split(" ");
            double height = Double.parseDouble(info[0]);
            double workercats = Double.parseDouble(info[1]);
                for (int n = 0; n < 33; n++) {
                    if (Math.pow(workercats, n) == workercats) {
                        System.out.println(n);
                    }
                }
        }
    }
}
