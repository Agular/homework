import java.util.*;

/**
 * Created by Ragnar on 12.04.2016.
 *
 * @author raluga
 * @author jakell
 */
class Dna implements Comparable {
    int sortedness;
    String name;

    Dna(String name, int sortedness
    ) {
        this.name = name;
        this.sortedness = sortedness;
    }

    @Override
    public int compareTo(Object o) {
        Dna otherDna = (Dna) o;
        if (this.sortedness >= otherDna.sortedness) {
            return 1;
        } else {
            return -1;
        }
    }
}

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int datasets = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < datasets; i++) {
            scanner.nextLine();
            String[] info = scanner.nextLine().split(" ");
            int len_word = Integer.parseInt(info[0]);
            int n_words = Integer.parseInt(info[1]);
            SortedSet<Dna> dnad = new TreeSet<>();
            for (int j = 0; j < n_words; j++) {
                int sortedness = 0;
                String dnaname = scanner.nextLine();
                for (int k = 0; k < len_word - 1; k++) {
                    char first = dnaname.charAt(k);
                    for (int l = k + 1; l < len_word; l++) {
                        if (first > dnaname.charAt(l)) {
                            sortedness++;
                        }
                    }
                }
                //peale iga s]na
                Dna dna = new Dna(dnaname, sortedness);
                dnad.add(dna);
                //System.out.println(dna.name+" "+dna.sortedness);
            }
            for (Dna dna : dnad) {
                System.out.println(dna.name);
            }

            if (i != datasets - 1) {
                System.out.println();
            }
        }
    }
}
