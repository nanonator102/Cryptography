package com.nanonator.src;

import java.math.BigInteger;

public class RSA {
    private final BigInteger n, phiN, d, e;


    //Constructor to generate the public and private keys when given 2 prime numbers. Primality is not enforced here but is required.
    public RSA(BigInteger p, BigInteger q) {
        //Calculate n and phiN
        this.n = p.multiply(q);
        System.out.println("n = " + n.toString());
        this.phiN = (p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE)));

        //Generates the value e by diving phiN by 3 and finding the next prime from that value. Probably not the most secure but allows generating of e automatically with very little effort.
        this.e = BigUtil.findNextPrime(phiN.divide(new BigInteger("3")));

        this.d = e.modInverse(phiN);
    }


    //Constructor for giving the public and private keys. Can also be used for just the public keys if d is BigInteger.ZERO
    public RSA(BigInteger n, BigInteger e, BigInteger d) {
        this.n = n;
        this.e = e;
        this.d = d;
        this.phiN = BigInteger.ZERO;
    }

    //Returns the public keys as an array of BigInteger with a length of 2.
    public BigInteger[] getPublicKey() {
        return new BigInteger[]{n, e};
    }

    //Should not actually be public
    public BigInteger getPrivateKey() {
        return d;
    }

    //Encrypt the message, m and return the cryptotext.
    public BigInteger encrypt(BigInteger m) {
        return m.modPow(e, n);
    }

    //Decrypt the cryptotext, c and return the message. if D is 0 then this will return 0.
    public BigInteger decrypt(BigInteger c) {
        if (d.equals(BigInteger.ZERO)) {
            return d;
        } else {
            return c.modPow(d, n);
        }
    }

    //Multiply two cryptotexts and return the result.
    public BigInteger multiply(BigInteger c1, BigInteger c2) {
        return c1.multiply(c2);
    }

    //Return a string containing all variables. Not really useful except for printing to the console for testing purposes.
    public String toString() {
        return String.format("N = %s, phiN = %s, E = %s, D = %s", n.toString(), phiN.toString(), e.toString(), d.toString());
    }

    public BigInteger genSignature(BigInteger m) {
        BigInteger s;
        s = m.modPow(d, n);
        return s;
    }

    public boolean checkSignature(BigInteger m, BigInteger s) {
        BigInteger i;
        i = s.modPow(e, n);
        return i.equals(s);
    }

}
