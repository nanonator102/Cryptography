package com.nanonator.src;

import java.math.BigInteger;

public class BigUtil {

    /**
     * Checks if a number is prime. Returns true if prime.
     * @param a = BigInteger testNumber
     * @return = boolean isPrime
     */
    public static boolean isPrime(BigInteger a) {
        boolean isPrime = true;
        for (BigInteger i = new BigInteger("2"); !i.equals(a.divide(BigInteger.TWO)); i = i.add(BigInteger.ONE)) {
            if (a.mod(i).equals(BigInteger.ZERO)) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    /**
     * Finds the next prime from the given number.
     * @param start = BigInteger startingValue
     * @return = BigInteger prime
     */
    public static BigInteger findNextPrime(BigInteger start) {
        if (isPrime(start)) {
            return start;
        } else {
            while (!isPrime(start)) {
                start = start.add(BigInteger.ONE);
            }
        }
        return start;
    }

    /**
     * Returns the GCD of two numbers.
     * @param a = BigInteger a
     * @param b = BigInteger b
     * @return = BigInteger GCD(a,b)
     */
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger t = b;
            b = a.remainder(b);
            a = t;
        }
        return a;
    }

    /**
     * Returns the LCM of two numbers.
     * @param a = BigInteger a
     * @param b = BigInteger b
     * @return = BigInteger LCM(a,b)
     */
    public static BigInteger lcm(BigInteger a, BigInteger b) {
        return (a.multiply(b)).divide(gcd(a, b));
    }

    /**
     * Returns the modular inverse of a^-1 mod e. Uses inbuilt method for positive numbers and multiplication method for negative numbers.
     * @param a = BigInteger a
     * @param e = BigInteger e
     * @return = BigInteger modInv
     */
    public static BigInteger modinv(BigInteger a, BigInteger e) {
        BigInteger r;
        try {
            r = a.modInverse(e);
            return r;
        } catch (ArithmeticException exception) {
            System.out.printf("Caught exception. A = %s, E = %s\n", a, e);
            r = a.multiply(e);
            System.out.printf("Temp = %s\n", r);
            return r;
        }
    }
}
