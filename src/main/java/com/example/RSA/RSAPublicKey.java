package com.example.RSA;

import java.math.BigInteger;
import java.security.interfaces.RSAKey;

public class RSAPublicKey{
    public BigInteger N;
    public BigInteger e;

    public RSAPublicKey(BigInteger N, BigInteger e){
        this.N = N;
        this.e = e;
    }
    public BigInteger getN(){
        return N;
    }
    public BigInteger getE(){
        return e;
    }
    
}
