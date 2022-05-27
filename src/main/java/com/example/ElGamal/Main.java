package com.example.ElGamal;

import java.io.FileNotFoundException;
import java.io.ObjectInputStream.GetField;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
        GenKeysElGamal keyPair = new GenKeysElGamal(1024);

        String a = Fichier.getContentFichier("/Users/nael/Desktop/cryptoAsym/exemples/text2.txt");
        // remplacer [/Users/nael/Desktop] par votre chemin absolu inhérent à votre ordinateur

        byte[] By = Fichier.stringToBytesArray(a);
        //String s = new String(By);
        BigInteger mesg = new BigInteger(By);

        System.out.println("Message clair (String) : " + a);
        System.out.println("Message clair (BigInteger) : " + mesg);

        CoupleChiffre chiffrementMesg = keyPair.chiffrement(mesg.toByteArray(),keyPair.getPublicKey().getP(),keyPair.getPublicKey().getH(),keyPair.getPublicKey().getG());
        System.out.println("Message chiffré C1 (BigInteger) " + chiffrementMesg.getC1());
        System.out.println("Message chiffré C2 (BigInteger) " + chiffrementMesg.getC2());


        BigInteger msgDechiffre = keyPair.dechiffrement(chiffrementMesg,keyPair.getPrivateKey().getX(),keyPair.getPrivateKey().getP());
        System.out.println("Message dechiffré : " + msgDechiffre );
        System.out.println("Message dechiffré : " + new String(msgDechiffre.toByteArray()));

        DigitalSig dg = new DigitalSig(1024);
        BigInteger[] params = dg.getParams();
        BigInteger p = params[0];
        BigInteger g = params[1];
        BigInteger x = params[2];
        //System.out.println("p : "+p);
        //System.out.println("g : "+g);
        //System.out.println("x : "+x);


        BigInteger[] signature = dg.sign(By, p, g, x);
        System.out.println("r is "+signature[0]);
        System.out.println("s is "+signature[1]);
        BigInteger y = g.modPow(x, p);

        //signature[0] = new BigInteger("5566678434343434436899976554322125"); si on uncomment alors la signature est altéree et elle devient evidemment incorrecte
        
        boolean isGood = dg.verify(p, g, y, signature, By);
        if(isGood){
            System.out.println("la signature est correcte");
        }else{
            System.out.println("la signature est pas correcte");
        }



    }
}
