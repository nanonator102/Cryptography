package com.nanonator.tests;

import com.nanonator.src.ElGamal;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class ElGamalTest {
    ElGamal elGamal = new ElGamal(new BigInteger("2879"), new BigInteger("2585"), new BigInteger("47"));

    @Test
    void getPublicKeys() {
        BigInteger[] publicKey = elGamal.getPublicKeys();
        assertEquals(new BigInteger("2879"), publicKey[0]);
        assertEquals(new BigInteger("2585"), publicKey[1]);
        assertEquals(new BigInteger("2826"), publicKey[2]);
    }

    @Test
    void getPrivateKey() {
        BigInteger privateKey = elGamal.getPrivateKey();
        assertEquals(new BigInteger("47"), privateKey);
    }

    @Test
    void encrypt() {
        BigInteger[] cryptotext = elGamal.encrypt(new BigInteger("5"), new BigInteger("154"));
        assertEquals(new BigInteger("1309"), cryptotext[0]);
        assertEquals(new BigInteger("199"), cryptotext[1]);

        BigInteger[] cryptotext2 = elGamal.encrypt(new BigInteger("6"), new BigInteger("96"));
        assertEquals(new BigInteger("1138"), cryptotext2[0]);
        assertEquals(new BigInteger("2433"), cryptotext2[1]);
    }

    @Test
    void decrypt() {
        BigInteger message = elGamal.decrypt(new BigInteger[]{new BigInteger("1309"), new BigInteger("199")});
        assertEquals(new BigInteger("5"), message);
    }

    @Test
    void multiply() {
        BigInteger[] cryptotext = elGamal.encrypt(new BigInteger("5"), new BigInteger("154"));
        BigInteger[] cryptotext2 = elGamal.encrypt(new BigInteger("6"), new BigInteger("96"));
        BigInteger[] mult = elGamal.multiply(cryptotext, cryptotext2);

        assertEquals(new BigInteger("1199"), mult[0]);
        assertEquals(new BigInteger("495"), mult[1]);
    }

    @Test
    void decryptMultiplication() {
        BigInteger[] mult = new BigInteger[]{new BigInteger("1199"), new BigInteger("495")};
        BigInteger result = elGamal.decryptMultiplication(mult);
        assertEquals(new BigInteger("30"), result);
    }
}