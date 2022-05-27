package com.example.RSA;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigitalSig {

    public RSAPublicKey pK;
    public RSAPrivateKey sK;
    public static Params param;

    public DigitalSig(int SECURITY_PARAM){
        GenKeysRSA gk = new GenKeysRSA(SECURITY_PARAM);
        pK = gk.getPublicKey();
        sK = gk.getPrivateKey();
    }  
    public RSAPrivateKey getPrivateKey(){
        return sK;
    }
    public RSAPublicKey getPublicKey(){
        return pK;
    }
    public BigInteger signature(byte[] messageASigner, RSAPrivateKey privateKey, RSAPublicKey publicKey) throws NoSuchAlgorithmException{
        BigInteger publicN = pK.getN();
        BigInteger privateD = sK.getD(); 
        //appliquer une fonction de hashage au message messageASigner
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(messageASigner, 0, messageASigner.length);
        BigInteger bi = new BigInteger(1,md.digest());
        return bi.modPow(privateD,publicN);
    }
    
    public boolean verification(byte[] messageASigner,BigInteger signatureRecue,RSAPublicKey publicKey) throws NoSuchAlgorithmException{
        BigInteger e = publicKey.getE();
        BigInteger pKN = publicKey.getN();
        BigInteger hashSignatureRecue = signatureRecue.modPow(e, pKN);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(messageASigner, 0, messageASigner.length);
        BigInteger bi = new BigInteger(1,md.digest());
        return bi.equals(hashSignatureRecue);

    }
}
