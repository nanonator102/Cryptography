package com.nanonator.src;

import java.math.BigInteger;

public class ElGamal {
    private final BigInteger p, g, y, x;

    public ElGamal(BigInteger p, BigInteger g, BigInteger x) {
        //p, g and x are provided by the user. p is a prime number where g and x are random numbers.
        this.p = p;
        this.g = g;
        this.x = x;

        this.y = g.modPow(x, p);
    }

    //Constructor to initialise ElGamal using the public and private keys. x can be set to BigInteger.ZERO for encryption only.
    public ElGamal(BigInteger p, BigInteger g, BigInteger y, BigInteger x) {
        this.p = p;
        this.g = g;
        this.y = y;
        this.x = x;
    }


    //Returns the public keys as an array. Order is p, g, y.
    public BigInteger[] getPublicKeys() {
        return new BigInteger[]{p, g, y};
    }

    //Returns the private key, x
    public BigInteger getPrivateKey() {
        return x;
    }

    public BigInteger[] encrypt(BigInteger m, BigInteger r) {
        BigInteger k = y.modPow(r, p);
        System.out.println("K = " + k.toString());
        BigInteger[] cryptos = new BigInteger[2];
        cryptos[0] = g.modPow(r, p);
        cryptos[1] = m.multiply(k).mod(p);
        return cryptos;
    }

    public BigInteger decrypt(BigInteger[] c) {
        BigInteger k = c[0].modPow(x, p);
        BigInteger invK = k.modInverse(p);

        return invK.multiply(c[1]).mod(p);
    }

    public String toString() {
        return String.format("P = %s, G = %s, X = %s, Y = %s", p.toString(), g.toString(), x.toString(), y.toString());
    }
}
