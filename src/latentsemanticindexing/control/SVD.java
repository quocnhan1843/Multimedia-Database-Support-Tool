/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latentsemanticindexing.control;

import UI.Dictionary;
import com.sun.imageio.plugins.jpeg.JPEG;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author quocn
 */
public class SVD extends JPanel{

    public void loadTable(List<DataDocument> listIdDocument, List<DataTermWord> listIdTermWord, HashMap listWordQR, String databaseName) {
    }

    public String getName() {
        return Dictionary.TYPE.SVD.getString();
    }

   

    
}
