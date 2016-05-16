/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latentsemanticindexing.control;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author quocn
 */
public class WindowsUI extends JPanel{
    
    private ClassAnlysis topPanel;
    private JPanel centerPanel;
    
    private Frequency frequency_Type;
    private TF_IDF tf_idf_Type;
    private TF_IDF_SVD tf_idf_svd_Type;
    private SVD svd_Type;
    
    public WindowsUI(){
        init();
        setLayout();
        setComponent();
    }
    
    private void init(){
        topPanel = new ClassAnlysis(this);
        frequency_Type = new Frequency();
        tf_idf_Type = new TF_IDF();
        tf_idf_svd_Type = new TF_IDF_SVD();
        svd_Type = new SVD();
        centerPanel = new JPanel();
        
    }
    
    private void setLayout(){
        //this.setLayout(new GridLayout(2, 1));
        this.setLayout(new BorderLayout());
    }
    
    private void setComponent(){
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(frequency_Type);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }
    
    public void loadTable(List listIdDocument, List listIdTermWord, HashMap listWordQR, String databaseName){
        try{
            frequency_Type.loadTable(listIdDocument, listIdTermWord, listWordQR, databaseName);
            tf_idf_Type.loadTable(listIdDocument, listIdTermWord, listWordQR, databaseName);
            tf_idf_svd_Type.loadTable(listIdDocument, listIdTermWord, listWordQR, databaseName);
            svd_Type.loadTable(listIdDocument, listIdTermWord, listWordQR, databaseName);
        }catch(NullPointerException ex){
            //ex.printStackTrace();
        }
    }

    public void loadTable(int index) {
        try{
            centerPanel.removeAll();
            setCenterPanel(index);
            centerPanel.revalidate();
            centerPanel.repaint();
        }catch(Exception ex){
        }finally{            
        }
    }

    private void setCenterPanel(int index) {
        if(index == 0){
            centerPanel.add(frequency_Type);
        }else if(index == 1){
            centerPanel.add(tf_idf_Type);
        }else if(index == 2){
            centerPanel.add(svd_Type);
        }else{
            centerPanel.add(tf_idf_svd_Type);
        }
    }
}
