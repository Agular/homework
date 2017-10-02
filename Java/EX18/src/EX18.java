public class EX18 implements EX18Interface {
    @Override
    public String encrypt(String plainText, int rotation) {
        if (plainText == null) {
            return null;
        }
        String minimizedText = plainText.toLowerCase();
        String encrypt_str = "";
        for (int i = 0; i < minimizedText.length(); i++) {
            if (Character.isAlphabetic(minimizedText.charAt(i))) {
                encrypt_str += Character.toString((char) ((minimizedText.charAt(i) - 97 + rotation) % 26 + 97));
            } else {
                encrypt_str += Character.toString(minimizedText.charAt(i));
            }
        }
        encrypt_str = minimizeText(plainText);
        return encrypt_str;
    }

    @Override
    public String findMostFrequentlyOccurringLetter(String text) {
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

    @Override
    public String minimizeText(String text) {
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

    @Override
    public String decrypt(String cryptoText, int rotation) {
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
}