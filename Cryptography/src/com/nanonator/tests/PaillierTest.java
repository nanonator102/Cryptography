package com.nanonator.tests;

import com.nanonator.src.Paillier;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaillierTest {
    Paillier paillier = new Paillier(new BigInteger("11"), new BigInteger("13"), new BigInteger("5652"));

    @Test
    public void getPublicKeys() {
        BigInteger[] publicKeys = paillier.getPublicKeys();
        assertEquals(new BigInteger("143"), publicKeys[0]);
        assertEquals(new BigInteger("5652"), publicKeys[1]);
    }

    @Test
    public void getPrivateKeys() {
        BigInteger[] privateKeys = paillier.getPrivateKeys();
        assertEquals(new BigInteger("60"), privateKeys[0]);
        assertEquals(new BigInteger("53"), privateKeys[1]);
    }

    @Test
    public void encrypt() {
        BigInteger m = new BigInteger("100"), r = new BigInteger("29");
        assertEquals(new BigInteger("1366"), paillier.encrypt(m, r));
    }

    @Test
    public void decrpyt() {
        BigInteger c = new BigInteger("1366");
        assertEquals(new BigInteger("100"), paillier.decrypt(c));
    }

    @Test
    public void add() {
        Paillier paillierAdd = new Paillier(new BigInteger("5"), new BigInteger("7"), new BigInteger("164"));
        BigInteger c1 = paillierAdd.encrypt(new BigInteger("4"), new BigInteger("6"));
        BigInteger c2 = paillierAdd.encrypt(new BigInteger("1"), new BigInteger("17"));
        BigInteger c = paillierAdd.add(c1, c2);
        assertEquals(new BigInteger("5"), paillierAdd.decrypt(c));
    }
}
