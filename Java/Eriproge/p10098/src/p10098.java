import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Ragnar on 08.03.2016.
 *
 * @author raluga
 */
class p10098 {
    static SortedSet<String> anagrams;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int wordsN = Integer.parseInt(reader.readLine());
        int counter = 0;
        String[] wordArray;
        while (counter < wordsN) {
            counter++;
            wordArray = reader.readLine().split("");
            anagrams = new TreeSet<>();
            createAnagrams("", wordArray);
            for (String anagram : anagrams) {
                System.out.println(anagram);
            }
            System.out.println();
        }
    }

    public static void createAnagrams(String target, String[] source) {
        if (source.length == 0) {
            if (!anagrams.contains(target)) {
                anagrams.add(target);
            }
        } else {
            for (int i = 0; i < source.length; i++) {
                createAnagrams(target + source[i], deleteStringIdx(source, i));
            }
        }
    }

    public static String[] deleteStringIdx(String[] source, int idx) {
        String[] target = new String[source.length - 1];
        int j = -1;
        for (int i = 0; i < source.length; i++) {
            if (idx != i) {
                j++;
                target[j] = source[i];
            }
        }
        return target;
    }
}
