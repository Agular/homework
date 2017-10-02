import java.util.Scanner;

/**
 * Created by Ragnar on 22.03.2016.
 *
 * @author raluga
 * @author siduun
 */
class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine().split(" +");
            String[] piano_raw = line[0].split(",");
            double maxPiano = Math.max(Integer.parseInt(piano_raw[0]), Integer.parseInt(piano_raw[1])) +
                    2 * Math.min(Integer.parseInt(piano_raw[0]), Integer.parseInt(piano_raw[1]));
            String answer = "";
            for (int i = 1; i < line.length; i++) {
                String[] corners = line[i].split(",");
                if (Math.sqrt(2) * (Integer.parseInt(corners[0]) + Integer.parseInt(corners[1])) > maxPiano) {
                    answer += "Y";
                } else {
                    answer += "N";
                }
            }
            System.out.println(answer);
        }
    }
}
