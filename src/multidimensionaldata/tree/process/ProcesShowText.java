/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensionaldata.tree.process;

import java.util.Vector;


/**
 *
 * @author quocn
 */
public class ProcesShowText {
    private Vector vector;
    private int index;
    
    public ProcesShowText(){
        vector = new Vector();
    }
    public void insertText(String text){
        vector.add(text);
    }
    public void clearText(){
        vector.clear();
    }
    public void next(){
        if(index >= vector.size()) return;
        
    }
    public void reset(){
        index = 0;
    }
    
}
