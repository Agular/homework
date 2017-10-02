import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Ragnar on 04.03.2016.
 * @author: raluga
 */
class p195 {
    static List<String> anagrams;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int wordsN = Integer.parseInt(reader.readLine());
        int counter = 0;
        while (counter != wordsN) {
            counter++;
            String[] chars = reader.readLine().split("");
            Arrays.sort(chars, ALPHABETICAL_ORDER);
            String word="";
            for(String c: chars){
                word+=c;
            }
            anagrams = new ArrayList<>();
            createAnagrams("", word);
        }
    }

    public static void createAnagrams(String target, String source) {
        if (source.length() == 0) {
            if (!anagrams.contains(target)) {
                System.out.print(target+" ");
                anagrams.add(target);
            }
        } else if (target.equals("")) {
            for (int i = 0; i < source.length(); i++) {
                createAnagrams(target + source.charAt(i), source.substring(0, i) + source.substring(i + 1));
            }
        } else if (source.length() >= 2) {
            createAnagrams(target + source.charAt(0), source.substring(1));
            for (int i = 1; i < source.length() - 1; i++) {
                createAnagrams(target + source.charAt(i), source.substring(0, i) + source.substring(i + 1));
            }
            createAnagrams(target + source.charAt(source.length() - 1), source.substring(0, source.length() - 1));
        } else {
            createAnagrams(target + source.charAt(0), "");
        }
    }

    private static Comparator<String> ALPHABETICAL_ORDER = new Comparator<String>() {
        public int compare(String str1, String str2) {
            if(str1.toLowerCase().equals(str2.toLowerCase())){
                return str1.compareTo(str2);
            }else{
                return str1.toLowerCase().compareTo(str2.toLowerCase());

            }
        }
    };
}