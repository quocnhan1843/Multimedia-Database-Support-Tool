/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latentsemanticindexing.control;

import UI.Dictionary;
import de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author quocn
 */
public class AddQuery extends javax.swing.JFrame {

    /**
     * Creates new form AddQuery
     */
    private UI.TextDatabase lsa;
    
    public AddQuery() {
        initComponents();
        setLang();
    }
    
    public AddQuery(UI.TextDatabase lsa) {
        this.lsa = lsa;
        initComponents();
        setLang();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitle = new javax.swing.JLabel();
        buttonOk = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaQuery = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        labelTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("ADD QUERY");

        buttonOk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonOk.setText("OK");
        buttonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkActionPerformed(evt);
            }
        });

        textAreaQuery.setColumns(20);
        textAreaQuery.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textAreaQuery.setRows(5);
        jScrollPane1.setViewportView(textAreaQuery);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(labelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonOk)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonOk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkActionPerformed
        // TODO add your handling code here:
        String text = textAreaQuery.getText();
        Vector listTermWord = RemoveStopWord.getList(text);
        HashMap map = new HashMap<String, Integer>();
        for(int i=0; i<listTermWord.size(); ++i){
            String word = (String) listTermWord.get(i);
            if(map.containsKey(word)){
                map.put(word, (int) map.get(word) + 1 );
            }else{
                map.put(word, 1 );
            }
        }        
        lsa.loadDataTable(map);
        this.dispose();
    }//GEN-LAST:event_buttonOkActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel());
        } catch (ParseException | UnsupportedLookAndFeelException ex) {
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddQuery().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonOk;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JTextArea textAreaQuery;
    // End of variables declaration//GEN-END:variables

    private void setLang() {
        this.setTitle(Dictionary.Words.ADD_QUERY.getString());
        this.labelTitle.setText(Dictionary.Words.ADD_QUERY.getString());
    }

}