/**
 * Created by Ragnar on 11.02.2016.
 * And Cesar shall speak gibberish which is solved by programmers 2000 years later.
 */
public class EX03 {

    /**
     * Given text and a rotation, encrypts text.
     *
     * @param plainText plain text, readable by humanoids
     *                  with relative ease.
     * @param rotation
     * @return encrypted text
     */
    public static String encrypt(String plainText, int rotation) {
        if (plainText == null) {
            return null;
        }
        plainText = plainText.toLowerCase();
        String encrypt_str = "";
        for (int i = 0; i < plainText.length(); i++) {
            if (Character.isAlphabetic(plainText.charAt(i))) {
                encrypt_str += Character.toString((char) ((plainText.charAt(i) - 97 + rotation) % 26 + 97));
            } else {
                encrypt_str += Character.toString(plainText.charAt(i));
            }
        }
        encrypt_str = minimizeText(encrypt_str);
        return encrypt_str;
    }

    /**
     * Finds the most frequently occurring letter in text.
     *
     * @param text either plain or encrypted text.
     * @return the most frequently occurring letter in text.
     */
    public static String findMostFrequentlyOccurringLetter(String text) {
        if (text == null) {
            return null;
        }
        Integer[] char_count = new Integer[26];
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            if (Character.isAlphabetic(text.charAt(i))) {
                if (char_count[(int) text.charAt(i) - 97] == null) {
                    char_count[(int) text.charAt(i) - 97] = 1;
                } else {
                    char_count[(int) text.charAt(i) - 97] += 1;
                }
            }
        }
        int max = 0;
        int max_counter = -1;
        for (int i = 0; i < char_count.length; i++) {
            if (char_count[i] != null && char_count[i] > max) {
                max = char_count[i];
                max_counter = i;
            }
        }
        if (max_counter == -1) {
            return "";
        } else {
            char frequent_char = (char) (max_counter + 97);
            return Character.toString(frequent_char);
        }
    }

    /**
     * Removes the most prevalent letter from text.
     *
     * @param text either plain or encrypted text.
     * @return text in which the most prevalent letter has been removed.
     */
    public static String minimizeText(String text) {
        if (text == null) {
            return null;
        }
        text = text.toLowerCase();
        String del_str = findMostFrequentlyOccurringLetter(text);
        StringBuilder source = new StringBuilder(text);
        for (int i = source.length() - 1; i > -1; i--) {
            if (Character.toString(source.charAt(i)).equals(del_str)) {
                source.deleteCharAt(i);
            }
        }
        return source.toString();
    }

    /**
     * Given the initial rotation and the encrypted text, this method
     * decrypts said text.
     *
     * @param cryptoText Encrypted text.
     * @param rotation   How many letters to the right the alphabet was
     *                   shifted in order to encrypt text.
     * @return Decrypted text.
     * Lets do this.
     */
    public static String decrypt(String cryptoText, int rotation) {
        if (cryptoText == null) {
            return null;
        }
        cryptoText = cryptoText.toLowerCase();
        String decrypt_str = "";
        for (int i = 0; i < cryptoText.length(); i++) {
            if (Character.isAlphabetic(cryptoText.charAt(i))) {
                decrypt_str += Character.toString((char) ((cryptoText.charAt(i) - 97 + (26 - rotation)) % 26 + 97));
            } else {
                decrypt_str += Character.toString(cryptoText.charAt(i));
            }
        }
        return decrypt_str;
    }

    /**
     * The main method, which is the entry point of the program.
     *
     * @param args Arguments from the command line
     */
    public static void main(String[] args) {
        System.out.println(encrypt("you too Brutus?", 3)); // => zpv upp csvuvt?
        System.out.println(decrypt("find a cab", 22)); // => you too brutus?
        System.out.println(findMostFrequentlyOccurringLetter("you too Brutus?")); // => o
        System.out.println(minimizeText("my name is what, my name is who, my name is slim-shady")); // yu t brutus?
    }
}
