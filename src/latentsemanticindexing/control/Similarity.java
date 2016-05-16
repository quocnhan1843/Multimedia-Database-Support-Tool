/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latentsemanticindexing.control;

/**
 *
 * @author quocn
 */
public class Similarity {
    public static double getNum(double[] D1, double[] D2){
        
        for(double d1:D1){
            System.out.print(d1 + " ");
        }
        System.out.println("");
        for(double d2:D2){
            System.out.print(d2 + " ");
        }
        
        try{
            double num = 0.0;
            for(int i=0; i< D1.length; i++){
                System.out.println(String.valueOf(D1[i]) + "*" + String.valueOf(D2[i]) + " = " + D1[i] * D2[i]);
                num += (D1[i] * D2[i]);
                System.out.println("num = " + num);
            }
            System.out.println("num = " + num);
            return num;
        }catch(Exception ex){
            ex.printStackTrace();
            return 0.0;
        }
    }
}
