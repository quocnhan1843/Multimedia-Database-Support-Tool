/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensionaldata.tree.process;

import java.awt.Graphics2D;
import multidimensionaldata.tree.InfoNode;
import multidimensionaldata.tree.Point2D;
import multidimensionaldata.tree.Tree;

/**
 *
 * @author ASUS
 */
public class ProcessDeleteMatrixQuad implements ProcessDelete{

    private boolean haveObject;
    private InfoNode infoObject;

    public ProcessDeleteMatrixQuad() {
        haveObject = false;
    }

    @Override
    public void setInfo(InfoNode nodeObject, InfoNode nodeTarget) {
    }
    
    @Override
    public void setInfo(InfoNode infoNode) {
        infoObject = infoNode;
    }

    @Override
    public void reset() {
        haveObject = false;                 
        Process.stateRun = Process.STATE.WAITING;
    }

    @Override
    public boolean isComplete() {
        return true;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void go(Tree treePaint) {
        try{
            if(haveObject)
                treePaint.deleteNode(infoObject.getLabel(), infoObject.getPoint(), false);
            reset();
        }catch(NullPointerException ex){
            
        }
    }

    @Override
    public void addPoint(Point2D pointObject, Point2D pointTarget) {
    }

    @Override
    public void addPoint(Point2D point2D) {
        haveObject = true;
    }

    @Override
    public void paint(Graphics2D g2d) {
    }

    @Override
    public boolean canNext() {
        return haveObject;
    }
}
