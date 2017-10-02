import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Uva 478
 *
 * @author raluga
 */
class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Double[]> figures = new ArrayList<>();
        String line;
        while (input.hasNext()) {
            line = input.nextLine();
            if (line.equals("*")) {
                break;
            }
            String[] rawInput = line.split(" ");
            Double[] data = new Double[rawInput.length - 1];
            for (int i = 1; i < rawInput.length; i++) {
                data[i - 1] = Double.parseDouble(rawInput[i]);
            }

            figures.add(data);
        }
        int lineNr = 0;
        while (input.hasNext()) {
            boolean found = false;
            lineNr++;
            line = input.nextLine();
            if (line.equals("9999.9 9999.9")) {
                break;
            }
            String[] rawPoint = line.split(" ");
            double pointX = Double.parseDouble(rawPoint[0]);
            double pointY = Double.parseDouble(rawPoint[1]);
            int figureNr = 0;
            for (Double[] figure : figures) {
                figureNr++;
                if (figure.length == 4) {
                    if (figure[0] < pointX && pointX < figure[2]
                            && figure[1] > pointY && pointY > figure[3]) {
                        System.out.format("Point %d is contained in figure %d\n", lineNr, figureNr);
                        found = true;
                    }
                } else if (figure.length == 3) {
                    Double x = figure[0], y = figure[1], r = figure[2];
                    if (Math.sqrt(Math.pow(pointX - x, 2) + Math.pow(pointY - y, 2)) < r) {
                        System.out.format("Point %d is contained in figure %d\n", lineNr, figureNr);
                        found = true;
                    }
                } else if (figure.length == 6) {
                    double x1 = figure[0], y1 = figure[1], x2 = figure[2], y2 = figure[3], x3 = figure[4], y3 = figure[5];
                    double dot1 = (y2 - y1) * (pointX - x1) + (-x2 + x1) * (pointY - y1);
                    double dot2 = (y3 - y2) * (pointX - x2) + (-x3 + x2) * (pointY - y2);
                    double dot3 = (y1 - y3) * (pointX - x3) + (-x1 + x3) * (pointY - y3);
                    if (dot1 > 0 && dot2 > 0 && dot3 > 0 || dot1 < 0 && dot2 < 0 && dot3 < 0) {
                        System.out.format("Point %d is contained in figure %d\n", lineNr, figureNr);
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.printf("Point %d is not contained in any figure\n", lineNr);
            }
        }
    }
}
