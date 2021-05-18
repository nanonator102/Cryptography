package com.nanonator.src;

import java.math.BigInteger;

/**
 * ElGamal a partially homomorphic encryption system. It has 3 numbers as the public key and 1 as the private key.
 *     To create an ElGamal keypair you need 3 input numbers, of which one must be prime.
 */
public class ElGamal {

    private final BigInteger p, g, y, x;

    /**
     * Generates an ElGamal keypair using 3 given numbers.
     * @param p = Random prime number greater than 0.
     * @param g = Random number greater than 0.
     * @param x = Random number greater than 0.
     */
    public ElGamal(BigInteger p, BigInteger g, BigInteger x) {
        this.p = p;
        this.g = g;
        this.x = x;

        this.y = g.modPow(x, p);
    }

    /**
     * Initialises an ElGamal keypair using public and private keys. given x value can be BigInteger.ZERO for encryption only.
     * @param p = p
     * @param g = g
     * @param y = y
     * @param x = x
     */
    public ElGamal(BigInteger p, BigInteger g, BigInteger y, BigInteger x) {
        this.p = p;
        this.g = g;
        this.y = y;
        this.x = x;
    }

    /**
     * Returns the public key as an array of BigInteger.
     * @return = {p, g, y}
     */
    public BigInteger[] getPublicKeys() {
        return new BigInteger[]{p, g, y};
    }

    /**
     * Returns the private key as a BigInteger.
     * @return = x
     */
    public BigInteger getPrivateKey() {
        return x;
    }

    /**
     * Takes a plaintext message and random number and returns ciphertext as an array of BigInteger.
     * @param m = Message
     * @param r = Random number greater than 0
     * @return = {c1, c2}
     */
    public BigInteger[] encrypt(BigInteger m, BigInteger r) {
        BigInteger k = y.modPow(r, p);
        BigInteger[] ciphertext = new BigInteger[2];
        ciphertext[0] = g.modPow(r, p);
        ciphertext[1] = m.multiply(k).mod(p);
        return ciphertext;
    }

    /**
     * Takes an array of cryptotext and returns the plaintext message as a BigInteger
     * @param c = {c1, c2}
     * @return = m
     */
    public BigInteger decrypt(BigInteger[] c) {
        BigInteger k = c[0].modPow(x, p);
        BigInteger invK = k.modInverse(p);
        return invK.multiply(c[1]).mod(p);
    }

    /**
     * Takes two pairs of ciphertext and multiplies them. Returns the result as an array of BigInteger
     * @param C1 = {c11, c12}
     * @param C2 = {c21, c22}
     * @return = {c31, c32}
     */
    public BigInteger[] multiply(BigInteger[] C1, BigInteger[] C2) {
     BigInteger[] c = new BigInteger[2];
     c[0] = C1[0].multiply(C2[0]).mod(p);
     c[1] = C1[1].multiply(C2[1]).mod(p);
     return c;
    }

    /**
     * Takes a pair of previously multiplied ciphertext and decrypts it. Returns the result as a Biginteger.
     * @param ciphertext = {c1, c2}
     * @return = m
     */
    public BigInteger decryptMultiplication(BigInteger[] ciphertext) {
        BigInteger a1 = ciphertext[1].mod(p);
        BigInteger a2 = (ciphertext[0].pow(x.intValue())).modInverse(p);
        return a1.multiply(a2).mod(p);
    }

    /**
     * Returns the public and private keys as a string.
     * @return = keys
     */
    public String toString() {
        return String.format("P = %s, G = %s, X = %s, Y = %s", p.toString(), g.toString(), x.toString(), y.toString());
    }

    /**
     * Generates a signature for the given message.
     * @param m = Message
     * @param k = Nonce
     * @return
     */
    public BigInteger[] genSignature(BigInteger m, BigInteger k) {
        BigInteger r;
        r = g.modPow(k, p);
        BigInteger t = m.subtract(x.multiply(r));
        BigInteger s = BigUtil.modinv(k, p.subtract(BigInteger.ONE));
        s = s.multiply(t);
        s = s.mod(p.subtract(BigInteger.ONE));
        return new BigInteger[] {r, s};
    }

    /**
     * Verifies the signature s of given message m. Returns true if signature matches.
     * @param m = Message
     * @param s = Signature[]{r, s}
     * @return
     */
    public boolean verifySignature(BigInteger m, BigInteger[] s) {
        BigInteger v = g.modPow(m, p);
        BigInteger t1 = y.pow(s[0].intValue());
        BigInteger t2 = s[0].pow(s[1].intValue());
        BigInteger t3 = t1.multiply(t2);
        BigInteger w = t3.mod(p);
        return v.equals(w);
    }
}
