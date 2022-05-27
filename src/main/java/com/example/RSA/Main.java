package com.example.RSA;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException,NoSuchAlgorithmException{
        GenKeysRSA keyPair = new GenKeysRSA(1024); // ne pas hesiter 
        String a = Fichier.getContentFichier("/Users/nael/Desktop/cryptoAsym/exemples/text1.txt");
        // remplacer [/Users/nael/Desktop] par votre chemin absolu inhérent à votre ordinateur
        byte[] By = Fichier.stringToBytesArray(a);
        String s = new String(By);
        BigInteger mesg = new BigInteger(By);

        System.out.println("Message clair (String) : " + a);
        System.out.println("Message clair (bytes) : " + s );
        System.out.println("Message clair (BigInteger) : " + mesg);

        BigInteger msgChiffre = keyPair.chiffrement(mesg);
        System.out.println("Message chiffré (BigInteger) " + msgChiffre);

        BigInteger msgDechiffre = keyPair.dechiffrement(msgChiffre);
        System.out.println("Message dechiffré : " + msgDechiffre );

        DigitalSig sv = new DigitalSig(1024);
        RSAPublicKey pK = sv.getPublicKey();
        RSAPrivateKey sK = sv.getPrivateKey();

        BigInteger sig;
        boolean b;
        sig = sv.signature(msgChiffre.toByteArray(),sK,pK);
        System.out.println("Signature generée : " + sig);

        //sig = sig.add(new BigInteger("32")); //si on uncomment cette ligne alors la signature deviendra evidemment incorrecte
        
        b = sv.verification(msgChiffre.toByteArray(), sig, pK);
        if(b){
            System.out.println("Signature correcte !");
        }else{
            System.out.println("Signature erronée ! ");
        }
    }
}
