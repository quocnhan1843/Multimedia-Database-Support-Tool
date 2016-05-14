/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latentsemanticindexing.control;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author quocn
 */
public class test {
    public static void main(String[] args) {
        Set s = new TreeSet();
        String s1 = "Nguyen Quoc Nhan";
        String s2 = "Nguyen Quoc Nhan";
        String s3 = "Lan la con heo";
        
        System.out.println(s.add(s1));
        System.out.println(s.add(s2));
        System.out.println(s.add(s3));
        
        System.out.println("--------");
        for(Object ss:s){
            System.out.println(ss);
        }
        
        System.out.println(s.size());
                        
    }
}
