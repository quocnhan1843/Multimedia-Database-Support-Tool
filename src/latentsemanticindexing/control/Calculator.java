/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latentsemanticindexing.control;

import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author quocn
 */
public class Calculator {

    public static double[][] getFrequency(List<DataDocument> listIdDocument
            , List<DataTermWord> listIdTermWord
            , String databaseName) {
        double[][] arr = new double[listIdDocument.size()][listIdTermWord.size()];
        int sz = 0;
        
        for(DataDocument idDocument: listIdDocument){
            Vector vec = new Vector();
            for(DataTermWord idTermWord:listIdTermWord){
                String id= idTermWord.getId();
                if(!id.equals("0")){
                    double num = getNumber(id,idDocument, databaseName);
                    vec.add(num);
                }else{
                    vec.add(0.0);
                }
            }
            for(int i=0; i<vec.size(); i++){
                arr[sz][i] = Double.valueOf(vec.get(i).toString());
            }
            sz++;
        }
        return arr;
    }
    private static double getNumber(String id, DataDocument idDocument, String databaseName) {
        String sql = "select count from term_document where id_term = '"
                   + id + "' and id_document = '" + idDocument.getId() + "'";
        try{
            ResultSet res = Data.Data.getResultsetQuery(sql, databaseName);
            if(res.next()){
                return res.getDouble(1);
            }
        }catch(Exception ex){
            return 0;
        }
        return 0;
    }
}
