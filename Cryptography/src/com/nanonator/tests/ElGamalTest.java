package com.nanonator.tests;

import com.nanonator.src.ElGamal;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class ElGamalTest {
    ElGamal elGamal = new ElGamal(new BigInteger("139"), new BigInteger("3"), new BigInteger("12"));

    @Test
    void getPublicKeys() {
        BigInteger[] publicKey = elGamal.getPublicKeys();
        assertEquals(new BigInteger("139"), publicKey[0]);
        assertEquals(new BigInteger("3"), publicKey[1]);
        assertEquals(new BigInteger("44"), publicKey[2]);
    }

    @Test
    void getPrivateKey() {
        BigInteger privateKey = elGamal.getPrivateKey();
        assertEquals(new BigInteger("12"), privateKey);
    }

    @Test
    void encrypt() {
        BigInteger[] cryptotext = elGamal.encrypt(new BigInteger("100"), new BigInteger("52"));
        assertEquals(new BigInteger("38"), cryptotext[0]);
        assertEquals(new BigInteger("80"), cryptotext[1]);
    }

    @Test
    void decrypt() {
        BigInteger message = elGamal.decrypt(new BigInteger[]{new BigInteger("38"), new BigInteger("80")});
        assertEquals(new BigInteger("100"), message);
    }
}