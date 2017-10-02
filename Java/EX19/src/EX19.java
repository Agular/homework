/**
 * Created by Ragnar on 10.05.2016.
 * The Schrodinger dog.
 */
public class EX19 {
    /**
     * @param something The input string
     * @return Array containing the number of alive and dead dogs.
     */
    public static int[] testDog(String something) {
        int[] dogs = new int[]{0, 0};
        if (something == null) {
            return dogs;
        }
        for (String string : something.split(" ")) {
            if (string.contains("Koer")) {
                dogs[0]++;
            }
            if (string.contains("koer")) {
                dogs[1]++;
            }
        }
        return dogs;
    }

    public static void main(String[] args) {
        String test = "bfaf--afafaplpolllnmvqweiioi";
        System.out.println(test.replaceAll("[a-k]+", ""));
    }
}
