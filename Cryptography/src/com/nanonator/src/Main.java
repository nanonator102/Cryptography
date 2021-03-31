package com.nanonator.src;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        rsaExample();
        elGamalExample();
    }

    public static void rsaExample() {
        System.out.println("=====RSA Example=====");
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
        System.out.print("\n\n\n");
        RSA rsa2 = new RSA(new BigInteger("551"), new BigInteger("59"), new BigInteger("299"));

        System.out.println("=====End RSA Example=====\n\n");
    }

    public static void elGamalExample() {
        System.out.println("=====ElGamal Example=====");
        BigInteger eP = new BigInteger("139");
        BigInteger eG = new BigInteger("3");
        BigInteger eX = new BigInteger("12");
        ElGamal elGamal = new ElGamal(eP, eG, eX);
        System.out.println(elGamal.toString());
        BigInteger[] eC = elGamal.encrypt(new BigInteger("100"), new BigInteger("52"));
        System.out.println("Cryptotext(M) = " + eC[0].toString() + ", " + eC[1].toString());
        BigInteger m = elGamal.decrypt(eC);
        System.out.println("Message(C) = " + m.toString());
        System.out.print("\n\n\n");

        ElGamal elGamal1 = new ElGamal(new BigInteger("139"), new BigInteger("3"), new BigInteger("44"), new BigInteger("12"));
        System.out.println("=====End ElGamal Example=====\n\n");

        ElGamal elGamal2 = new ElGamal(new BigInteger("2879"), new BigInteger("2585"), new BigInteger("47"));
        System.out.println(elGamal2.toString());
        BigInteger[] eC1 = elGamal2.encrypt(new BigInteger("5"), new BigInteger("154"));
        BigInteger[] eC2 = elGamal2.encrypt(new BigInteger("6"), new BigInteger("96"));
        System.out.printf("C1: %s, %s\nC2: %s, %s\n", eC1[0].toString(), eC1[1].toString(), eC2[0].toString(), eC2[1].toString());
        BigInteger[] cmult = elGamal2.multiply(eC1, eC2);
        System.out.printf("CMult: %s, %s\n", cmult[0].toString(), cmult[1].toString());
        BigInteger mult = elGamal2.decryptMultiplication(cmult);
        System.out.printf("Mult result: %s\n", mult.toString());
    }


}
