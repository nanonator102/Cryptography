package com.nanonator.src;

import java.math.BigInteger;

public class BigUtil {

    // Checks if a number is prime. Returns true if it is prime.
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

    // Finds the next prime from the given number.
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

    public static BigInteger lcm(BigInteger a, BigInteger b) {
        return (a.multiply(b)).divide(gcd(a, b));
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger t = b;
            b = a.remainder(b);
            a = t;
        }
        return a;
    }

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
