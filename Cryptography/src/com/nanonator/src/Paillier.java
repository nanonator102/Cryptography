package com.nanonator.src;

import java.math.BigInteger;

public class Paillier {
    private final BigInteger n, g, l, u;

    /**
     * Constructor to generate a Paillier keypair. Value p must be prime. q and g are random numbers.
     * @param p = BigInteger p
     * @param q = BigInteger q
     * @param g = BigInteger g
     */
    public Paillier(BigInteger p, BigInteger q, BigInteger g) {
        //Public key generation
        this.g = g;
        this.n = p.multiply(q);

        //Private key generation
        this.l = BigUtil.lcm((p.subtract(BigInteger.ONE)), (q.subtract(BigInteger.ONE)));
        BigInteger k;
        k = g.modPow(l, (n.pow(BigInteger.TWO.intValue())));
        k = k.subtract(BigInteger.ONE);
        k = k.divide(n);
        this.u = k.modInverse(n);
    }

    /**
     * Constructor to initialize a Paillier keypair using a given public key set and given private key. Private key can be BigInteger.ZERO for encryption only.
     * @param publicKeys = BigInteger[]{n, g}
     * @param privateKeys = BigInteger[]{l, u}
     */
    public Paillier(BigInteger[] publicKeys, BigInteger[] privateKeys) {
        this.n = publicKeys[0];
        this.g = publicKeys[1];
        this.l = privateKeys[0];
        this.u = privateKeys[1];
    }

    /**
     * Returns the public keys.
     * @return = BigInteger[]{n, g}
     */
    public BigInteger[] getPublicKeys() {
        return new BigInteger[] {n, g};
    }

    /**
     * Returns the private keys.
     * @return = BigInteger[]{l, u}
     */
    public BigInteger[] getPrivateKeys() {
        return new BigInteger[] {l, u};
    }

    /**
     * Encrypts the given message and returns the ciphertext.
     * @param m = BigInteger message
     * @param r = BigInteger nonce
     * @return = BigInteger ciphertext
     */
    public BigInteger encrypt(BigInteger m, BigInteger r) {
        BigInteger c;
        c = ((g.pow(m.intValue())).multiply((r.pow(n.intValue())))).mod((n.pow(2)));
        return c;
    }

    /**
     * Decrypts the given ciphertext and returns the cleartext. If either private key is BigInteger.ZERO this will return BigInteger.ZERO
     * @param c = BigInteger ciphertext
     * @return = BigInteger cleartext
     */
    public BigInteger decrypt(BigInteger c) {
        if (l.equals(BigInteger.ZERO) || u.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        } else {
            BigInteger bigL = c.modPow(l, (n.pow(2)));
            bigL = (bigL.subtract(BigInteger.ONE)).divide(n);
            return (bigL.multiply(u)).mod(n);
        }
    }

    /**
     * Homomorphically adds two ciphertexts and returns the result.
     * @param c1 = BigInteger ciphertext1
     * @param c2 = BigInteger ciphertext2
     * @return = BigInteger resultCiphertext
     */
    public BigInteger add(BigInteger c1, BigInteger c2) {
        return c1.multiply(c2);
    }

    /**
     * Returns a string containing the public and private keys.
     * @return = String keys
     */
    public String toString() {
        return String.format("G = %s\nN = %s\nL = %s\nU = %s", g, n, l, u);
    }
}