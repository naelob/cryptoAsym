package com.example.ElGamal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Fichier {
    public static String getContentFichier(String fichier) throws FileNotFoundException{
        FileReader fr = new FileReader(fichier); 
        int i;
        String s ="";
        try {
            while((i=fr.read()) != -1 ){
                s+=(char)i;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static byte[] stringToBytesArray(String a){
        return a.getBytes();
    }
    /*public static void main(String[] args) throws FileNotFoundException {
        String a = getContentFichier("/Users/nael/Desktop/PROJET MATH_INFO/exemples/text1.txt");
        byte[] By = stringToBytesArray(a);
        for(byte b:By)
            System.out.print(b);
        System.out.println();
    }*/
}
