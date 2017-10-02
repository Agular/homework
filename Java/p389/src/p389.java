import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String[] info = scanner.nextLine().trim().replaceAll(" +", " ").split(" ");
            int inBase = Integer.parseInt(info[1]);
            int outBase = Integer.parseInt(info[2]);
            int number10 = 0;
            int numLenght = info[0].length();
            for (int i = 0; i < info[0].length(); i++) {
                char c = info[0].charAt(numLenght - i - 1);
                if (c == 'A') {
                    number10 += Math.pow(inBase, i) * 10;
                } else if (c == 'B') {
                    number10 += Math.pow(inBase, i) * 11;
                } else if (c == 'C') {
                    number10 += Math.pow(inBase, i) * 12;
                } else if (c == 'D') {
                    number10 += Math.pow(inBase, i) * 13;
                } else if (c == 'E') {
                    number10 += Math.pow(inBase, i) * 14;
                } else if (c == 'F') {
                    number10 += Math.pow(inBase, i) * 15;
                } else {
                    number10 += Math.pow(inBase, i) * Character.getNumericValue(c);
                }
            }
            if (outBase == 10) {
                if (Integer.toString(number10).length() <= 7) {
                    System.out.format("%7s\n", number10);
                } else {
                    System.out.format("%7s\n", "ERROR");
                }
            } else {
                String outNumber = "";
                int mod;
                while (number10 != 0) {
                    mod = number10 % outBase;
                    if (mod > 9) {
                        if (mod == 10) {
                            outNumber += "A";
                        } else if (mod == 11) {
                            outNumber += "B";
                        } else if (mod == 12) {
                            outNumber += "C";
                        } else if (mod == 13) {
                            outNumber += "D";
                        } else if (mod == 14) {
                            outNumber += "E";
                        } else {
                            outNumber += "F";
                        }
                    } else {
                        outNumber += mod;
                    }
                    number10 /= outBase;
                }
                outNumber = new StringBuilder(outNumber).reverse().toString();
                if (outNumber.equals("")) {
                    System.out.format("%7s\n", 0);
                } else if (outNumber.length() <= 7) {
                    System.out.format("%7s\n", outNumber);
                } else {
                    System.out.format("%7s\n", "ERROR");
                }
            }
        }
    }
}
