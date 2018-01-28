package ee.ttu.algoritmid.stamps;

import java.util.*;

public class Stamps {
    private static List<Integer> stamps;
    private static boolean[] primes;

    public static List<Integer> findStamps(int sum, List<Integer> stampOptions) throws IllegalArgumentException {
        stamps = stampOptions;
        if (stamps.isEmpty()) {
            throw new IllegalArgumentException();
        }
        calculatePrimes(Collections.max(stamps));
        Collections.sort(stamps, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o2 == 1) {
                    return 1;
                } else if (o1 == 1) {
                    return -1;
                } else if (o1 % 10 == 0 && o2 % 10 == 0) {
                    return o1 > o2 ? 1 : -1;
                } else if (o1 % 10 == 0 && o2 % 10 != 0) {
                    return -1;
                } else if (o1 % 10 != 0 && o2 % 10 == 0) {
                    return 1;
                } else if (o1 % 2 == 0 && o2 % 2 == 0) {
                    return o1 > o2 ? -1 : 1;
                } else if (o1 % 2 == 1 && o2 % 2 == 1) {
                    return o1 > o2 ? -1 : 1;
                } else if (o1 % 2 != 0 && o2 % 2 == 0) {
                    return 1;
                } else if (o1 % 2 == 0 && o2 % 2 != 0) {
                    return -1;
                } else {
                    return -1;
                }
            }
        });
/*        for (int i = 0; i < primes.length; i++) {
            System.out.println(i + " " + primes[i]);
        }*/
        System.out.println(stamps);
        int[] M = new int[sum + 1];
        int[] V = new int[sum + 1];
        for (int i = 1; i <= sum; i++) {
            M[i] = Integer.MAX_VALUE - 1;
            for (int j = stamps.size() - 1; j >= 0; j--) {
                int stampOption = stamps.get(j);
                if (i >= stampOption && (M[i] > M[i - stampOption] + 1)) {
                    M[i] = M[i - stampOption] + 1;
                    V[i] = stampOption;
                }
            }
        }

        List<Integer> answer = new ArrayList<>();
        int n = sum;
        while (n > 0) {
            answer.add(V[n]);
            n = n - V[n];
        }
        return answer;
    }

    // the algorithm was written from https://www.mkyong.com/java/how-to-determine-a-prime-number-in-java/
    // not directly but in a similar way
    private static void calculatePrimes(int lastElement) {
        primes = new boolean[lastElement + 1];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for (int i = 2; i < lastElement; i++) {
            if (primes[i]) {
                for (int j = 2; i * j < primes.length; j++) {
                    primes[i * j] = false;
                }
            }
        }
    }
}