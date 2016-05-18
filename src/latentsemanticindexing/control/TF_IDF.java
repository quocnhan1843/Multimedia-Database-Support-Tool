/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package latentsemanticindexing.control;

import UI.Dictionary;
import edu.stanford.nlp.ling.CoreAnnotations;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author quocn
 */
public class TF_IDF extends JPanel{
    
    private JTabbedPane tabbedPane;
    
    private JTable tableStep1;
    private JPanel panelStep1;
    private JScrollPane scrollPaneStep1;
    
    private JTable tableStep2;
    private JPanel panelStep2;
    private JScrollPane scrollPaneStep2;
    
    private JTable tableStep3;
    private JPanel panelStep3;
    private JScrollPane scrollPaneStep3;
    
    private JTable tableStep4;
    private JPanel panelStep4;
    private JScrollPane scrollPaneStep4;
    
    private JTable tableStep5;
    private JPanel panelStep5;
    private JScrollPane scrollPaneStep5;
    
    public TF_IDF(){
        //super();
        init();
        addLis();
    }

    private void init() {
        
        tabbedPane = new JTabbedPane();
        this.setLayout(new GridLayout(1, 1));
        this.add(tabbedPane);
        
        initStep1();
        initStep2();
        initStep3();
        initStep4();
        initStep5();
        
        this.tabbedPane.add(panelStep1);
        this.tabbedPane.add(panelStep2);        
        this.tabbedPane.add(panelStep3);   
        this.tabbedPane.add(panelStep4);
        this.tabbedPane.add(panelStep5);   
        
        loadTabName();
    }
    
    private void initStep1(){
        tableStep1 = new JTable();
        panelStep1 = new JPanel();
        panelStep1.setLayout(new GridLayout(1, 1));
        setScrollPaneStep1();
        this.panelStep1.add(scrollPaneStep1);
    }
    
    private void initStep2(){
        tableStep2 = new JTable();
        panelStep2 = new JPanel();
        panelStep2.setLayout(new GridLayout(1, 1));
        setScrollPaneStep2();
        this.panelStep2.add(scrollPaneStep2);
    }
    private void initStep3(){
        tableStep3 = new JTable();
        panelStep3 = new JPanel();
        panelStep3.setLayout(new GridLayout(1, 1));
        setScrollPaneStep3();
        this.panelStep3.add(scrollPaneStep3);
    }
    private void initStep4(){
        tableStep4 = new JTable();
        panelStep4 = new JPanel();
        panelStep4.setLayout(new GridLayout(1, 1));
        setScrollPaneStep4();
        this.panelStep4.add(scrollPaneStep4);
    }
    private void initStep5(){
        tableStep5 = new JTable();
        panelStep5 = new JPanel();
        panelStep5.setLayout(new GridLayout(1, 1));
        setScrollPaneStep5();
        this.panelStep5.add(scrollPaneStep5);
    }

    private void setScrollPaneStep1() {
        scrollPaneStep1 = new JScrollPane(tableStep1
                , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                , JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tableStep1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }    
    private void setScrollPaneStep2() {
        scrollPaneStep2 = new JScrollPane(tableStep2
                , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                , JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tableStep2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
    }
    
    private void setScrollPaneStep3() {
        scrollPaneStep3 = new JScrollPane(tableStep3
                , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                , JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tableStep3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
    }  
    
    private void setScrollPaneStep4() {
        scrollPaneStep4 = new JScrollPane(tableStep4
                , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                , JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tableStep4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
    } 
    
    private void setScrollPaneStep5() {
        scrollPaneStep5 = new JScrollPane(tableStep5
                , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                , JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tableStep5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
    } 

    public void loadTable(List<DataDocument> listIdDocument, 
            List<DataTermWord> listIdTermWord, HashMap listWordQR, String databaseName) {
        
        double[][] arr = new double[listIdDocument.size()][listIdTermWord.size()];
        arr = Calculator.getFrequency(listIdDocument, listIdTermWord, databaseName);
        
        delAllCol(tableStep1);
        addTblCol(tableStep1, "Term Words/ Documents");
        for(DataTermWord ob:listIdTermWord){
            addTblCol(tableStep1, ob.getName());
        }
        
        for(int i=0; i<arr.length; i++){
            Vector vector = new Vector();
            vector.addElement(listIdDocument.get(i).getName());
            for(int j = 0; j<arr[i].length; j++){
                vector.addElement(arr[i][j]);
            }
            addTblRow(tableStep1, vector);
        }
        Vector v= new Vector();
        v.add("#QUERY");
        for(DataTermWord ob:listIdTermWord){
            String word = ob.getName();
            if(listWordQR.containsKey(ob.getName())){
                v.add( Double.valueOf(listWordQR.get(word).toString()));
            }else{
                v.add(0.0);
            }
        }
        
        
        addTblRow(tableStep1, v);
        Vector vectorQuery = new Vector();
        for(int i=1; i<v.size(); i++){
            vectorQuery.addElement(v.get(i));
        }
        
        try {
            setTableStep234(arr, vectorQuery, listIdDocument, listIdTermWord, listWordQR, databaseName);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }
    
    private void addTblCol(JTable table,String name) {
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        TableColumn col = new TableColumn(model.getColumnCount());

        col.setHeaderValue(name);
        table.addColumn(col);
        model.addColumn(name);
    };
    private void delTblCol(JTable table,int index) {
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        TableColumn col = table.getColumnModel().getColumn(index);
        table.removeColumn(col);
        table.revalidate();
    };
    
    private void delAllCol(JTable table){
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.getDataVector().removeAllElements();
        model.setColumnCount(0);
       
        table.revalidate();
    }
    
    private void addTblRow(JTable table, Vector vector){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(vector);
        table.revalidate();
    }

    private void loadTabName() {
        this.tabbedPane.setTitleAt(0, "Frequency");
        this.tabbedPane.setTitleAt(1, "tf");
        this.tabbedPane.setTitleAt(2, "idf");
        this.tabbedPane.setTitleAt(3, "if*idf");
        this.tabbedPane.setTitleAt(4, "Cosin distance");
    }
    
    private void setTableStep3(double[][] arr, Vector v, List<DataDocument> listIdDocument, String databaseName) {
        delAllCol(tableStep2);
        addTblCol(tableStep2, Dictionary.Words.SCORE.getString());
        addTblCol(tableStep2, Dictionary.Words.NAME_DOCUMENT.getString());
        addTblCol(tableStep2, Dictionary.Words.TEXT.getString());
        
        loadSizeTable2();
        
        double[] vectroQuery = new double[v.size()];
        
        for(int i=0; i<v.size(); ++i){
            vectroQuery[i] = (double) v.get(i);
        }
        DefaultTableModel model = (DefaultTableModel) tableStep2.getModel();
        
        for(int i=0; i<arr.length; ++i){
            double num = CosinDistance.getDistance(vectroQuery, arr[i]);
            
            Vector<Object> vt = new Vector<>();
            vt.addElement(num);
            vt.addElement(listIdDocument.get(i).getName());
            vt.addElement(getText(listIdDocument.get(i).getId(), databaseName));
            
            model.addRow(vt);
        }
        
        tableStep2.revalidate();
    }

    private String getText(String id, String databaseName) {
        String sql = "select text from documents where id = '" + id + "';";
        String ans = "";
        try {
            ResultSet res = Data.Data.getResultsetQuery(sql, databaseName);
            res.next();
            ans = res.getString(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return ans;
    }

    private void addLis() {
        tableStep5.addMouseListener(new java.awt.event.MouseAdapter() {
            
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem itemView = new JMenuItem(Dictionary.Words.VIEW.getString());

            popupMenu.add(itemView);


            itemView.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        int row = tableStep5.getSelectedRow();

                        String text = tableStep5.getModel().getValueAt(row, 2).toString();
                        String name = tableStep5.getModel().getValueAt(row, 1).toString();;

                        JDialog dialog = new JDialog();
                        dialog.setTitle(name);
                        dialog.setModal(true);
                        dialog.setContentPane(new JScrollPane(new JTextArea(text)));
                        dialog.setSize(700, 500);
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);
                        dialog.setFont(new Font(Dictionary.Font.DEFAULT.getString()
                                ,Font.PLAIN , 30));
                    }catch(Exception ex){}
                }
            });

            if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable ) {
                popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
            }
        });
    }
    
    private void loadSizeTable2(){
        tableStep5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableStep5.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableStep5.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableStep5.getColumnModel().getColumn(2).setPreferredWidth(5000);
    }

    @Override
    public String getName() {
        return Dictionary.TYPE.NORMAL.getString();
    }

    private void setTableStep234(double[][] arr1, Vector v2
            , List<DataDocument> listIdDocument
            , List<DataTermWord> listIdTermWord
            , HashMap listWordQR, String databaseName) throws Exception {
        
        double TF[][] = TF_IDF_Calculator.getTF(arr1, v2);
        double IDF[] = TF_IDF_Calculator.getIDF(arr1, v2);
        
        double[][] valueTF_IDF = new double[TF.length][TF[0].length];
        
        for(int i=0; i<TF.length; i++){
            for(int j=0; j<TF[i].length; j++){
                valueTF_IDF[i][j] = TF[i][j] * IDF[j];
            }
        }
        
        //=================================table 2===========
        delAllCol(tableStep2);
        addTblCol(tableStep2, "Term Words/ Documents");
        for(DataTermWord ob:listIdTermWord){
            addTblCol(tableStep2, ob.getName());
        }
        
        for(int i=0; i < TF.length; i++){
            Vector vector = new Vector();
            if(i < TF.length - 1) vector.addElement(listIdDocument.get(i).getName());
            else vector.addElement("#QUERY");
            for(int j = 0; j<TF[i].length; j++){
                vector.addElement(TF[i][j]);
            }
            addTblRow(tableStep2, vector);
        }
        //=================================table 3===========
        delAllCol(tableStep3);
        addTblCol(tableStep3, "Term Words/ Documents");
        for(DataTermWord ob:listIdTermWord){
            addTblCol(tableStep3, ob.getName());
        }
        Vector vectorIDF = new Vector();
        vectorIDF.addElement("Collection");
        for(int i=0; i < IDF.length; i++){
            vectorIDF.addElement(IDF[i]);
        }
        addTblRow(tableStep3, vectorIDF);
        //=================================table 4===========
        delAllCol(tableStep4);
        addTblCol(tableStep4, "Term Words/ Documents");
        for(DataTermWord ob:listIdTermWord){
            addTblCol(tableStep4, ob.getName());
        }
        
        for(int i=0; i < valueTF_IDF.length; i++){
            Vector vector = new Vector();
            if(i < valueTF_IDF.length - 1) vector.addElement(listIdDocument.get(i).getName());
            else vector.addElement("#QUERY");
            for(int j = 0; j<valueTF_IDF[i].length; j++){
                vector.addElement(valueTF_IDF[i][j]);
            }
            addTblRow(tableStep4, vector);
        }
        
        //===========================Bang 5 tinh cosin
        delAllCol(tableStep5);
        addTblCol(tableStep5, Dictionary.Words.SCORE.getString());
        addTblCol(tableStep5, Dictionary.Words.NAME_DOCUMENT.getString());
        addTblCol(tableStep5, Dictionary.Words.TEXT.getString());
        
        loadSizeTable5();
        
        DefaultTableModel model = (DefaultTableModel) tableStep5.getModel();
        
        for(int i=0; i<valueTF_IDF.length-1; ++i){
            double num = CosinDistance.getDistance(valueTF_IDF[i], valueTF_IDF[valueTF_IDF.length-1]);
            
            Vector<Object> vt = new Vector<>();
            vt.addElement(num);
            vt.addElement(listIdDocument.get(i).getName());
            vt.addElement(getText(listIdDocument.get(i).getId(), databaseName));
            
            model.addRow(vt);
            
            final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
                tableStep5.setRowSorter(sorter);
        }
        
        tableStep5.revalidate();
    }

    private void loadSizeTable5(){
        tableStep5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableStep5.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableStep5.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableStep5.getColumnModel().getColumn(2).setPreferredWidth(5000);
    }
}
