package ee.ttu.algoritmid.crypto;

import java.math.BigInteger;

public class AL08 {
    private Session session;

    /**
     * Constructor.
     * See https://en.wikipedia.org/wiki/Diffie%E2%80%93Hellman_key_exchange for information.
     *
     * @param session the session, that contains information about the key exchange
     */
    public AL08(Session session) {
        this.session = session;
    }

    /**
     * Crack Alice's private key (a) using the available information from the session.
     * See the public methods in the Session class to get the necessary data.
     *
     * @return the value of a (the secret integer of Alice)
     */
    public Integer crackAlice() {
        int p = session.getP();
        int g = session.getG();
        int alicePublicKey = session.getAlicesPublicKey();
        int aliceSecretKey = 1;
        while (true) {
            int aliceCrackedPublicKey = BigInteger.valueOf(g).pow(aliceSecretKey).mod(BigInteger.valueOf(p)).intValue();
            if ((int) Math.floor(aliceCrackedPublicKey) == alicePublicKey) {
                return aliceSecretKey;
            } else {
                aliceSecretKey++;
            }
        }
    }

    /**
     * Crack Bob's private key (b) using the available information from the session.
     * See the public methods in the Session class to get the necessary data.
     *
     * @return the value of b (the secret integer of Bob)
     */
    public Integer crackBob() {
        String msg = "tere";
        int bobKey = 1;
        int p = session.getP();
        int A = session.getAlicesPublicKey();
        String cryptedMsg = null;
        try {
            cryptedMsg = session.getEncrypted(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (bobKey < p) {
            int secretKey = BigInteger.valueOf(A).pow(bobKey).mod(BigInteger.valueOf(p)).intValue();
            try {
                if (session.getEncryptedWithCustomKey(msg, secretKey).equals(cryptedMsg)) {
                    return bobKey;
                } else {
                    bobKey++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}