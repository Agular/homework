package ee.ttu.algoritmid.fibonacci;

import java.math.BigInteger;

public class AL01A {
    /**
     * Compute the Fibonacci sequence number.
     * @param n The number of the sequence to compute.
     * @return The n-th number in Fibonacci series.
     */
    public String iterativeF(int n) {
        if(n == 0) {
            return "0";
        }
        if(n < 3){
            return "1";
        } else{
            BigInteger x = new BigInteger("1");
            BigInteger y = new BigInteger("1");
            BigInteger c = new BigInteger("0");
            for (int i = 3; i<= n; i++){
               c = x.add(y);
               x = y;
               y = c;
            }
            return c.toString();
        }
    }

    public static void main(String[] args){
        AL01A generator = new AL01A();
        System.out.println(generator.iterativeF(0));
        System.out.println(generator.iterativeF(1));
        System.out.println(generator.iterativeF(2));
        System.out.println(generator.iterativeF(3));
        System.out.println(generator.iterativeF(4));
        System.out.println(generator.iterativeF(5));
        System.out.println(generator.iterativeF(6));
        System.out.println(generator.iterativeF(7));
        System.out.println(generator.iterativeF(8));
        System.out.println(generator.iterativeF(9));
        System.out.println(generator.iterativeF(10));
        System.out.println(generator.iterativeF(11));
    }
}
