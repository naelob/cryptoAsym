package com.example.Performance;

import com.example.RSA.GenKeysRSA;
import com.example.ElGamal.GenKeysElGamal;


import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;


public class Main {

    public static long[] getTimeGenKeysRsa(int[] tab){
        long [] res = new long[tab.length];
        int j=0;
        for(int i : tab){
            long startTime = System.nanoTime();
            GenKeysRSA gk = new GenKeysRSA(i); 
            long stopTime = System.nanoTime();
            res[j] = stopTime - startTime;
            j++;
        }
        return res;
    }

    public static long[] getTimeGenKeysElGamal(int[] tab){
        long [] res = new long[tab.length];
        int j=0;
        for(int i : tab){
            long startTime = System.nanoTime();
            GenKeysElGamal gk = new GenKeysElGamal(i); 
            long stopTime = System.nanoTime();
            res[j] = stopTime - startTime;
            j++;
        }
        return res;
    }

    public static void launchGraph(int[] xTab, long[] yTab, String title, String xAxis, String yAxis){
        double[] xData = new double[xTab.length];
        int j=0;
        for(int e : xTab){
            xData[j] = (double) e;
            j++;
        }

        double[] yData = new double[yTab.length]; //new double[] { (double) a, (double) b, (double) c};
        int i=0;
        for(long e : yTab){
            yData[i] = (double) e;
            i++;
        }
        
        // Create Chart
        XYChart chart = QuickChart.getChart(title, xAxis, yAxis, "y(x)", xData, yData);
        
        // Show it
        new SwingWrapper(chart).displayChart();

    }
    public static void main(String[] args) {

        //RSA generation de clefs et parametres 
        int[] values1 = {1024,2048,3072,4096};
        long[] t = getTimeGenKeysRsa(values1);
        launchGraph(values1,t,"Temps d'exécution de la génération de clefs et paramètres RSA en fonction du paramètre de sécurité","paramètre de sécurité (bits)","temps (s) ");

        //El Gamal generation de clefs et parametres 
        int[] values = {1024,2048,3072,4096};
        long[] l = getTimeGenKeysElGamal(values);
        launchGraph(values,l,"Temps d'exécution de la génération de clefs et paramètres El Gamal en fonction du paramètre de sécurité","paramètre de sécurité (bits)","temps (s) ");


    }
}
