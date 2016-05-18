/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latentsemanticindexing.control;

import Jama.Matrix;
import Jama.SingularValueDecomposition;
import UI.Dictionary;
import com.sun.imageio.plugins.jpeg.JPEG;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
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
public class TF_IDF_SVD extends JPanel{

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
    
    private JTable tableStep6;
    private JPanel panelStep6;
    private JScrollPane scrollPaneStep6;
    
    private JTable tableStep7;
    private JPanel panelStep7;
    private JScrollPane scrollPaneStep7;
    
    private JTable tableStep8;
    private JPanel panelStep8;
    private JScrollPane scrollPaneStep8;
    public TF_IDF_SVD(){
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
        initStep6();
        initStep7();
        initStep8();
        
        this.tabbedPane.add(panelStep1);
        this.tabbedPane.add(panelStep2);
        this.tabbedPane.add(panelStep3);          
        this.tabbedPane.add(panelStep4);  
        this.tabbedPane.add(panelStep5);  
        this.tabbedPane.add(panelStep6);          
        this.tabbedPane.add(panelStep7);  
        this.tabbedPane.add(panelStep8);  
        
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
    
    private void initStep6(){
        tableStep6 = new JTable();
        panelStep6 = new JPanel();
        panelStep6.setLayout(new GridLayout(1, 1));
        setScrollPaneStep6();
        this.panelStep6.add(scrollPaneStep6);
    }
    
    private void initStep7(){
        tableStep7 = new JTable();
        panelStep7 = new JPanel();
        panelStep7.setLayout(new GridLayout(1, 1));
        setScrollPaneStep7();
        this.panelStep7.add(scrollPaneStep7);
    }
    
    private void initStep8(){
        tableStep8 = new JTable();
        panelStep8 = new JPanel();
        panelStep8.setLayout(new GridLayout(1, 1));
        setScrollPaneStep8();
        this.panelStep8.add(scrollPaneStep8);
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
    
    private void setScrollPaneStep6() {
        scrollPaneStep6 = new JScrollPane(tableStep6
                , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                , JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tableStep6.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
    } 
    
    private void setScrollPaneStep7() {
        scrollPaneStep7 = new JScrollPane(tableStep7
                , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                , JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tableStep7.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
    } 
    
    private void setScrollPaneStep8() {
        scrollPaneStep8 = new JScrollPane(tableStep8
                , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                , JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tableStep8.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
    } 

    public void loadTable(List<DataDocument> listIdDocument, 
            List<DataTermWord> listIdTermWord, HashMap listWordQR, String databaseName) {
        
        double[][] arr = new double[listIdDocument.size()][listIdTermWord.size()];
        arr = Calculator.getFrequency(listIdDocument, listIdTermWord, databaseName);
        int sz = 0;
        
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
        Vector v2 = new Vector();
        for(int i=1; i<v.size(); i++){
            v2.addElement(v.get(i));
        }
        
        try {
            setTableStep2(arr, v2, listIdDocument, listIdTermWord, listWordQR, databaseName);
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
        this.tabbedPane.setTitleAt(4, "T Matrix");
        this.tabbedPane.setTitleAt(5, "S Matrix");
        this.tabbedPane.setTitleAt(6, "D Matrix");
        this.tabbedPane.setTitleAt(7, "Consin");
    }
    
    private void setTableStep2(double[][] arr1, Vector v2
            , List<DataDocument> listIdDocument
            , List<DataTermWord> listIdTermWord
            , HashMap listWordQR, String databaseName) throws Exception {
        
        delAllCol(tableStep8);
        addTblCol(tableStep8, Dictionary.Words.SCORE.getString());
        addTblCol(tableStep8, Dictionary.Words.NAME_DOCUMENT.getString());
        addTblCol(tableStep8, Dictionary.Words.TEXT.getString());
        
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
        
        tableStep5.revalidate();
       
        Matrix A = new Matrix(valueTF_IDF).transpose();
        
        SingularValueDecomposition svd = A.svd();
        Matrix U = svd.getU().transpose();       
        Matrix V = svd.getV();
        double[] S = svd.getSingularValues();
        double[][] D = U.getArray();
        double[][] T = V.getArray();
        
        for(int i=0; i<T[0].length; i++){
            addTblCol(tableStep5, String.valueOf(i));
        }
        
        for(int i=0; i<T.length; i++){
            Vector row = new Vector();
            for(int j=0; j<T[0].length; j++){
                row.addElement(T[i][j]);
            }
            addTblRow(tableStep5, row);
        }
        
        for(int i=0; i<S.length; i++){
            addTblCol(tableStep6, String.valueOf(i));
        }
        
        Vector row = new Vector();
        for(double s:S){
            row.addElement(s);
        }
        addTblRow(tableStep6, row);
        
        for(int i=0; i<D[0].length; i++){
            addTblCol(tableStep7, String.valueOf(i));
        }
        
        for(int i=0; i<D.length; i++){
            row = new Vector();
            for(int j=0; j<D[0].length; j++){
                row.addElement(D[i][j]);
            }
            addTblRow(tableStep7, row);
        }
        
        double num;
        for(int i=0; i < D.length - 1; i++){
            num = CosinDistance.getDistance(D[i], D[D.length-1]);            
            Vector<Object> vt = new Vector<>();
            vt.addElement(num);
            vt.addElement(listIdDocument.get(i).getName());
            vt.addElement(getText(listIdDocument.get(i).getId(), databaseName));
            
            addTblRow(tableStep8, vt);
        }
        DefaultTableModel model = (DefaultTableModel) tableStep8.getModel();
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
            tableStep8.setRowSorter(sorter);
        loadSizeTable8();
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
        tableStep8.addMouseListener(new java.awt.event.MouseAdapter() {
            
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem itemView = new JMenuItem(Dictionary.Words.VIEW.getString());

            popupMenu.add(itemView);


            itemView.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        int row = tableStep8.getSelectedRow();

                        String text = tableStep8.getModel().getValueAt(row, 2).toString();
                        String name = tableStep8.getModel().getValueAt(row, 1).toString();;

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
    
    private void loadSizeTable8(){
        tableStep8.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableStep8.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableStep8.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableStep8.getColumnModel().getColumn(2).setPreferredWidth(5000);
    }

    public String getName() {
        return Dictionary.TYPE.NORMAL.getString();
    }

    private double[][] getFrequency(double[][] arr, Vector v) {
        double[][] tmpArr = new double[arr.length + 1][arr[0].length];
        
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
        
        return tmpArr;
    }

    private void printScreen(Matrix U, String name) {
        System.out.println(name);
        double[][] matrixU = U.getArray();
        for(int i=0; i<matrixU.length; i++){
            for(int j=0; j<matrixU[i].length; j++){
                System.out.print(" " + matrixU[i][j] + " ");
            }
            System.out.println("");
        }
    }
    private void printScreen(double [][] matrixU, String name) {
        System.out.println(name);
        for(int i=0; i<matrixU.length; i++){
            for(int j=0; j<matrixU[i].length; j++){
                System.out.print(" " + matrixU[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
