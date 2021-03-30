package com.nanonator.src;

import java.util.ArrayList;
import java.util.Arrays;

public class Util {

    // Checks if a number is prime. Returns true if it is prime.
    public static boolean isPrime(int a) {
        boolean isPrime = true;
        for (int i = 2; i <= Math.ceil(a/2.0); i++) {
            if (a % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    // Finds the next prime from the given number.
    public static int findNextPrime(int start) {
        if (Util.isPrime(start)) {
            return start;
        } else {
            while (!Util.isPrime(start)) {
                start++;
            }
        }
        return start;
    }

    // Returns the modular multiplicative inverse of a and m.
    public static int modInv(int a, int m) {
        if (gcd(a,m) != 1) {
            return -1;
        }
        int x;
        for (x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                break;
            }
        }
        return x;
    }

    // Returns the GCD of a and b
    public static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    //Finds and returns all prime numbers between start and end.
    public static ArrayList<Integer> findPrimesBetween(int start, int end) {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = start; i < end; i++) {
            if (Util.isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    //Find and return all primes up to max value. Uses the Sieve of Eratosthenes
    public static ArrayList<Integer> sieveOfEratosthenes(int maxVal) {
        boolean[] A = new boolean[maxVal];
        Arrays.fill(A, Boolean.TRUE);
        ArrayList<Integer> V = new ArrayList<>();

        for (int i = 2; i < maxVal; i++) {
            if (A[i]) {
                V.add(i);
                for (int j = (i*i); j < maxVal; j+= i) {
                    try {
                        A[j] = false;
                    } catch(Exception e) {
                        break;
                    }
                }
            }
        }
        return V;
    }

    //Old GCD method.
    public static int GCD(int x, int y) {
        ArrayList<Integer> xFactors = new ArrayList<>();
        ArrayList<Integer> yFactors = new ArrayList<>();

        for (int i = 1; i <= x; i++) {
            if (x % i == 0) xFactors.add(i);
        }

        for (int i = 1; i <= y; i++) {
            if (y % i == 0) yFactors.add(i);
        }

        int gcf = 0;
        for (int i : xFactors) {
            if (yFactors.contains(i)) {
                gcf = i;
            }
        }
        return gcf;
    }
}
