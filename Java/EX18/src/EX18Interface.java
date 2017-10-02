public interface EX18Interface {
    public String encrypt(String plainText, int rotation);

    /**
     * Finds the most frequently occurring letter in text.
     * @param text either plain or encrypted text.
     * @return the most frequently occurring letter in text.
     */
    public String findMostFrequentlyOccurringLetter(String text);

    /**
     * Removes the most prevalent letter from text.
     * @param text either plain or encrypted text.
     * @return text in which the most prevalent letter has been removed.
     */
    public String minimizeText(String text);

    /**
     * Given the initial rotation and the encrypted text, this method
     * decrypts said text.
     * @param cryptoText Encrypted text.
     * @param rotation How many letters to the right the alphabet was
     *                 shifted in order to encrypt text.
     * @return Decrypted text.
     */
    public String decrypt(String cryptoText, int rotation);
}