package com.nanonator.src;

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RSA {
    private final BigInteger n, d;
    private BigInteger e;

    /**
     * Constructor to generate an RSA keypair. Values p and q must be prime numbers. e can be preset or set to BigInteger.ZERO to be automatically generated.
     * @param p = prime 1
     * @param q = prime 2
     */
    public RSA(BigInteger p, BigInteger q) {
        this.n = p.multiply(q);
        BigInteger phiN = (p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE)));

        this.e = phiN.divide(new BigInteger("3"));
        while (!BigUtil.gcd(this.e, phiN).equals(BigInteger.ONE)) {
            this.e = this.e.add(BigInteger.ONE);
        }

        Scanner scan = new Scanner(System.in);
        BigInteger input;
        System.out.printf("E value required. Suggested value is %s. Please enter a value.\n", this.e);
        try {
            input = scan.nextBigInteger();
            BigInteger oldE = this.e;
            this.e = input;
            if (!BigUtil.gcd(this.e, phiN).equals(BigInteger.ONE)) {
                System.out.printf("Input E value is not Co-Prime to PhiN (%s). Using %s as E value.\n", phiN, oldE);
                this.e = oldE;
            }
        } catch (InputMismatchException exception) {
            scan.reset();
            System.out.printf("Input could not be parsed. Using %s for E.\n", this.e);
        }

        this.d = e.modInverse(phiN);
    }

    /**
     * Constructor to initialize an RSA keypair using a given public key set and given private key. Private key can be BigInteger.ZERO for encryption/signature verification only.
     * @param publicKey = BigInteger[]{n, e}
     * @param privateKey = BigInteger d
     */
    public RSA(BigInteger[] publicKey, BigInteger privateKey) {
        this.n = publicKey[0];
        this.e = publicKey[1];
        this.d = privateKey;
    }

    /**
     * Returns the public key as an array of BigInteger.
     * @return = BigInteger[]{n, e}
     */
    public BigInteger[] getPublicKey() {
        return new BigInteger[]{n, e};
    }

    /**
     * Returns the private key as a BigInteger.
     * @return = BigInteger d
     */
    public BigInteger getPrivateKey() {
        return d;
    }

    /**
     * Encrypts the given message and returns the ciphertext.
     * @param m = BigInteger message
     * @return = BigInteger ciphertext
     */
    public BigInteger encrypt(BigInteger m) {
        return m.modPow(e, n);
    }

    /**
     * Decrypts the given ciphertext and returns the cleartext. If the private key is BigInteger.ZERO this will return BigInteger.ZERO
     * @param c = BigInteger ciphertext
     * @return = BigInteger cleartext
     */
    public BigInteger decrypt(BigInteger c) {
        if (d.equals(BigInteger.ZERO)) {
            return d;
        } else {
            return c.modPow(d, n);
        }
    }

    /**
     * Multiplies two ciphertexts and returns the resulting ciphertext.
     * @param c1 = BigInteger ciphertext1
     * @param c2 = BigInteger ciphertext2
     * @return = BigInteger ciphertext3
     */
    public BigInteger multiply(BigInteger c1, BigInteger c2) {
        return c1.multiply(c2);
    }

    /**
     * Gives a string containing the public and private keys.
     * @return = String keys
     */
    public String toString() {
        return String.format("N = %s, E = %s, D = %s", n.toString(), e.toString(), d.toString());
    }

    /**
     * Generates and returns an RSA signature of the given message.
     * @param m = BigInteger message
     * @return = BigInteger signature
     */
    public BigInteger genSignature(BigInteger m) {
        BigInteger s;
        s = m.modPow(d, n);
        return s;
    }

    /**
     * Checks the given signature against the given message. Returns true if signature matches.
     * @param m = BigInteger message
     * @param s = BigInteger signature
     * @return = boolean match
     */
    public boolean checkSignature(BigInteger m, BigInteger s) {
        return decrypt(s).equals(m);
    }

}
