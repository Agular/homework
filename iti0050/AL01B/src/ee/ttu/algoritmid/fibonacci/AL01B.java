package ee.ttu.algoritmid.fibonacci;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class AL01B {

    /**
     * Estimate or find the exact time required to compute the n-th Fibonacci number.
     *
     * @param n The n-th number to compute.
     * @return The time estimate or exact time in YEARS.
     */
    public String timeToComputeRecursiveFibonacci(int n) {
        int testValue = 20;
        long start = System.nanoTime();
        recursiveF(testValue);
        long total = System.nanoTime() - start;
        BigDecimal estimatedTime = BigDecimal.valueOf(total).setScale(30);
        BigDecimal baseValue = estimatedTime.divide(BigDecimal.valueOf(1000000000.0), RoundingMode.DOWN)
                .divide(BigDecimal.valueOf(31536000.0), RoundingMode.DOWN)
                .divide(BigDecimal.valueOf(1.6).pow(testValue), RoundingMode.DOWN);
        BigDecimal timeNeeded = BigDecimal.valueOf(1.6).pow(n).multiply(baseValue);
        return timeNeeded.toPlainString();
    }

    /**
     * Compute the Fibonacci sequence number recursively.
     * (You need this in the timeToComputeRecursiveFibonacci(int n) function!)
     *
     * @param n The n-th number to compute.
     * @return The n-th Fibonacci number as a string.
     */
    public BigInteger recursiveF(int n) {
        if (n <= 1)
            return BigInteger.valueOf(n);
        return recursiveF(n - 1).add(recursiveF(n - 2));
    }

    public BigInteger recursiveRows(int n) {
        if (n <= 2) {
            return BigInteger.valueOf(1);
        }
        return recursiveRows(n - 1).add(recursiveRows(n - 2)).add(BigInteger.valueOf(2));
    }

    public static void main(String[] args) {
        AL01B estimator = new AL01B();
        System.out.println(estimator.timeToComputeRecursiveFibonacci(1));
        System.out.println(estimator.timeToComputeRecursiveFibonacci(50));
        System.out.println(estimator.timeToComputeRecursiveFibonacci(69));
        System.out.println(estimator.timeToComputeRecursiveFibonacci(100));
        System.out.println(estimator.timeToComputeRecursiveFibonacci(200));
    }
}