package com.nanonator.src;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        BigInteger p = new BigInteger("97");
        BigInteger q = new BigInteger("101");
        RSA rsa = new RSA(p, q);
        BigInteger c = rsa.encrypt(new BigInteger("100"));
        BigInteger mm = rsa.decrypt(c);
        System.out.printf("C = %d, M = %d\n", c, mm);
        System.out.print(rsa.toString());
        BigInteger[] publicKey = rsa.getPublicKey();

        BigInteger c1 = rsa.encrypt(new BigInteger("2"));
        BigInteger c2 = rsa.encrypt(new BigInteger("5"));
        System.out.println("2 * 5 = " + rsa.decrypt(rsa.multiply(c1, c2)));

    }


}
