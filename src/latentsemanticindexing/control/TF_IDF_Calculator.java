/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latentsemanticindexing.control;

import java.util.Vector;


/**
 *
 * @author quocn
 */
public class TF_IDF_Calculator{
    public static double[][] getTF(double[][] arr, Vector v) throws Exception{
        
        double[][] tmpArr = new double[arr.length + 1][arr[0].length];
        double[][] ans = new double[arr.length + 1][arr[0].length];
        
        for(int i=0; i<=arr.length; i++){
            if(i < arr.length){
                for(int j=0; j<arr[i].length; j++){
                    tmpArr[i][j] = arr[i][j];
                }
            }else{
                for(int j=0; j<v.size(); j++){
                    tmpArr[i][j] = Double.valueOf(v.get(j).toString()).doubleValue();
                }
            }
        }
        try{
            double[] maxTC = new double[tmpArr.length];

            for(int i=0; i<tmpArr.length; i++){
                maxTC[i] = 0;
                for(int j=0; j<tmpArr[i].length; j++){
                    maxTC[i] = Math.max(maxTC[i], tmpArr[i][j]);
                }
            }
            
            for(int i=0; i<tmpArr.length; i++){
                for(int j=0; j<tmpArr[i].length; j++){
                    if(maxTC[i] > 0){
                        ans[i][j] = tmpArr[i][j] / maxTC[i];
                    }else{
                        ans[i][j] = 0;
                    }
                }
            }
        }catch(Exception ex){}       
        return ans;
    }
    public static double[] getIDF(double[][] arr, Vector v) throws Exception{
        double[] ans = new double[arr[0].length + 1];
        
        double[][] tmpArr = new double[arr.length + 1][arr[0].length];
       
        for(int i=0; i<=arr.length; i++){
            if(i < arr.length){
                for(int j=0; j<arr[i].length; j++){
                    tmpArr[i][j] = arr[i][j];
                }
            }else{
                for(int j=0; j<v.size(); j++){
                    tmpArr[i][j] = (double) v.get(i);
                }
            }
        }
        
        try{
            int count = 0;
            for(int j=0; j<tmpArr[0].length; j++){
                count = 0;
                for(int i=0; i<tmpArr.length; i++){
                    if(tmpArr[i][j] > 0) count++;
                }
                if(count == 0) count++;
                ans[j] = Math.log10(tmpArr.length / (count));
            }
        }catch(Exception ex){}       
        return ans;
    }
}
