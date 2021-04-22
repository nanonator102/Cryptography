package com.nanonator.src;

import java.math.BigInteger;

public class Paillier {
    private final BigInteger p, q, g, n, l, k, u;

    public Paillier(BigInteger p, BigInteger q, BigInteger g) {
        BigInteger k1;
        this.p = p;
        this.q = q;
        this.g = g;

        //Public key generation
        this.n = p.multiply(q);

        //Private key generation
        this.l = BigUtil.lcm((p.subtract(BigInteger.ONE)), (q.subtract(BigInteger.ONE)));
        k1 = g.modPow(l, (n.pow(BigInteger.TWO.intValue())));
        k1 = k1.subtract(BigInteger.ONE);
        this.k = k1.divide(n);
        this.u = k.modInverse(n);
    }

    public BigInteger[] getPublicKeys() {
        return new BigInteger[] {n, g};
    }

    public BigInteger[] getPrivateKeys() {
        return new BigInteger[] {l, u};
    }

    public BigInteger encrypt(BigInteger m, BigInteger r) {
        BigInteger c;
        c = ((g.pow(m.intValue())).multiply((r.pow(n.intValue())))).mod((n.pow(2)));
        return c;
    }

    public BigInteger decrypt(BigInteger c) {
        BigInteger bigL;
        bigL = c.modPow(l, (n.pow(2)));
        bigL = (bigL.subtract(BigInteger.ONE)).divide(n);
        BigInteger m = (bigL.multiply(u)).mod(n);
        return m;
    }

    //TODO Add paillier addition.

    public String toString() {
        return String.format("P = %s\nQ = %s\nG = %s\nN = %s\nL = %s\nK = %s\nU = %s", p, q, g, n, l, k, u);
    }
}
