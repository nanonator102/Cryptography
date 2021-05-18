package com.nanonator.tests;

import com.nanonator.src.RSA;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class RSATest {
    RSA rsa = new RSA(new BigInteger("2381"), new BigInteger("5107"), BigInteger.ZERO);
    /*
    Using p = 2381 and q = 5107 the expected results are as follows:

    Public key: n = 12159767, e = 4050769
    private key: d = 900169

    Message = 100
    Encrypted result: C = 9482666
    Decrypted result: M = 100
    */

    @Test
    public void getPublicKey() {
        BigInteger[] publicKey = rsa.getPublicKey();
        assertEquals(new BigInteger("12159767"), publicKey[0]);
        assertEquals(new BigInteger("4050769"), publicKey[1]);
    }

    @Test
    public void getPrivateKey() {
        BigInteger privateKey = rsa.getPrivateKey();
        assertEquals(new BigInteger("900169"), privateKey);
    }

    @Test
    public void encrypt() {
        BigInteger message = new BigInteger("100");
        assertEquals(new BigInteger("9482666"), rsa.encrypt(message));
    }

    @Test
    public void decrypt() {
        BigInteger cryptotext = new BigInteger("9482666");
        assertEquals(new BigInteger("100"), rsa.decrypt(cryptotext));
    }

    @Test
    public void multiply() {
        BigInteger c1 = rsa.encrypt(new BigInteger("2"));
        BigInteger c2 = rsa.encrypt(new BigInteger("5"));
        BigInteger m = rsa.decrypt(rsa.multiply(c1, c2));
        assertEquals(new BigInteger("10"), m);
    }
}