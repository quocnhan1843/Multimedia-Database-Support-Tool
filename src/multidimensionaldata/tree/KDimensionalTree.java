/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensionaldata.tree;

import multidimensionaldata.tree.process.Process;
import UI.Dictionary;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Vector;
import multidimensionaldata.control.ControlTreePanel;
import multidimensionaldata.tree.process.ShowText;

/**
 *
 * @author ASUS
 */
public class KDimensionalTree extends Tree{
    
    private KDimensionalNode root;

    public KDimensionalTree() {
        super();
        this.root = null;
        super.setNumOfDimension(2);
    }
    
    public KDimensionalTree(int k) {
        super();
        this.root = null;
        super.setNumOfDimension(k);
    }
    
    @Override
    public void setNumOfDimension(int num){
        super.setNumOfDimension(num);
        this.root = null;
    }
    
    public KDimensionalNode getRoot() {
        return root;
    }

    public void setRoot(KDimensionalNode root) {
        this.root = root;
    }

    @Override
    public void insertNode(String label, Point point, boolean paint) {
        Node node = new KDimensionalNode(label, point);
        if(paint) addTextDisplay("\n=>THÊM NÚT " + getStringNode(label, point));
        super.setSizeUp();
        if(this.searchNodePreInsert(node)) return;
        if(this.root == null){
            this.setRoot(new KDimensionalNode(label, point));
            this.getRoot().setLevel(0);
            this.getRoot().setPos(12*3000 + 500, 50);
            if(paint){
                addTextDisplay("Vì cây rỗng nên nút " + getStringNode(label, point) + " trở thành nút gốc." );
            }
            return;
        }
        goChild(this.root, new KDimensionalNode(node, 0, null, null), paint);
    }
    private String getStringNode(String label, Point point){
        String ans = "";
        ans += label + "(";
        Vector vt = (Vector) point.getLocation();
        int sz = vt.size();
        for(int i=0; i<sz; i++){
            if(i > 0) ans += ",";
            ans += vt.get(i);
        }
        return ans + ")";
    }
    private void goChild(KDimensionalNode current,  KDimensionalNode tempNode, boolean paint){

        if(tempNode.greaterNode(current, current.getLevel() % super.getNumOfDimension())){
            updateNode(current.getLeftChild(), -60);
            if(paint){
                String str = getStringNode(current.getLabel(), current.getPoint());
                String tmp = getStringNode(tempNode.getLabel(), tempNode.getPoint());
                String text = "Nút " +  tmp + " có giá trị tọa độ thứ " 
                        + (int )(current.getLevel() % super.getNumOfDimension() + 1)
                        + " lớn hơn giá trị của " + str + " nên ta rẽ phải";
                addTextDisplay(text);
            }
            if(current.getRightChild() == null){
                
                tempNode.setPos(current.getxPos() + 60, current.getyPos() + 100);
                tempNode.setLevel(current.getLevel() + 1);
                
                current.setRightChild(tempNode);
                
                if(paint){
                    runAnimation(current.getxPos(), current.getyPos()
                            , current.getxPos() + 60, current.getyPos() + 100, true
                            , Dictionary.Words.TURN_RIGHT.getString());
                }
                if(paint){
                    String cur = getStringNode(current.getLabel(), current.getPoint());
                    String tmp = getStringNode(tempNode.getLabel(), tempNode.getPoint());
                    String text = "Gặp null, tạo nút biểu diễn cho  " +  tmp 
                            + " đây là nút con phải của " + cur;
                    addTextDisplay(text);
                }
            }else{
                if(paint){
                    runAnimation(current.getxPos(), current.getyPos()
                            , current.getRightChild().getxPos()
                            , current.getRightChild().getyPos(), false
                            , Dictionary.Words.TURN_RIGHT.getString());
                }
                goChild(current.getRightChild(),tempNode, paint);
            }
        }else{
            if(paint){
                String str = getStringNode(current.getLabel(), current.getPoint());
                String tmp = getStringNode(tempNode.getLabel(), tempNode.getPoint());
                String text = "Nút " +  tmp + " có giá trị tọa độ thứ " 
                        + (int )(current.getLevel() % super.getNumOfDimension() + 1)
                        + " nhỏ hơn giá trị của " + str + " nên ta rẽ trái";
                addTextDisplay(text);
            }
            updateNode(current.getRightChild(), 60);
            if(current.getLeftChild() == null){
                tempNode.setPos(current.getxPos() - 60, current.getyPos() + 100);
                tempNode.setLevel(current.getLevel() + 1);
                
                current.setLeftChild(tempNode);
                
                if(paint){
                    runAnimation(current.getxPos(), current.getyPos()
                            , current.getxPos() - 60, current.getyPos() + 100, true
                            , Dictionary.Words.TURN_LEFT.getString());
                }
                if(paint){
                    String cur = getStringNode(current.getLabel(), current.getPoint());
                    String tmp = getStringNode(tempNode.getLabel(), tempNode.getPoint());
                    String text = "Gặp null, tạo nút biểu diễn cho  " +  tmp 
                            + " đây là nút con trái của " + cur;
                    addTextDisplay(text);
                }
            }else{
                if(paint){
                    runAnimation(current.getxPos(), current.getyPos()
                            , current.getLeftChild().getxPos()
                            , current.getLeftChild().getyPos(), false
                            , Dictionary.Words.TURN_LEFT.getString());
                }
                goChild(current.getLeftChild(), tempNode, paint);
            }
        }
    }
    
    private void runAnimation(int xs, int ys, int xf, int yf, boolean isLeave, String string){

        int u1 = (xf - xs);
        int u2 = (yf - ys);
        for(double t = 0.0; t <= 1.0; t+= 0.001){
            int x = (int) (xs + t*u1);
            int y = (int) (ys + t*u2);
            
            Process.addPointInsert(new Point2D(x, y), string);
        }
        
        if(!isLeave)
        for(int i=0; i<100; i++){
            Process.addPointInsert(new Point2D(xf, yf),string);
        }
    }
    private void addTextDisplay(String string){
        ShowText.getInstace().addText(string);
    }

    @Override
    public void deleteNode(String label, Point point, boolean paint) {
        try{
            KDimensionalNode kDimensionalNode  = new KDimensionalNode(label, point);
            if(paint){
                addTextDisplay("\n=>XÓA NÚT " + getStringNode(label, point));
            }            
            KDimensionalNode current = searchNode(this.root, kDimensionalNode, paint);
            if(current == null){
                return;
            }
            if(current.isLeaves()){
                Process.setNodeDelete(new InfoNode(label, point), new InfoNode(label, point));
                Process.addPointSwap(new Point2D(0, 0), new Point2D(1, 1));
                
                if(current.equals(root)){
                    this.root = null;
                    super.setSize(0);
                    return;
                }
                if(current.isLeftChild(current.getParent())){
                    current.getParent().setLeftChild(null);
                    super.setSizeDown();
                }else{
                    current.getParent().setRightChild(null);
                    super.setSizeDown();
                }
                return;
            }
            removeNode(current, current.getLevel() % super.getNumOfDimension(), paint);
            if(this.root != null)
                updatePositionNode(this.root,this.root.getxPos(), this.root.getyPos());
        }catch(NullPointerException nullPointerException){
            
        }
    }
    
    private void updatePositionNode(KDimensionalNode kDimensionalNode, int xPos, int yPos){
        if(kDimensionalNode == null) return;
        kDimensionalNode.setPos(xPos, yPos);
        updatePositionNode(kDimensionalNode.getLeftChild(), xPos 
                - (60*(countChild(kDimensionalNode.getRightChild()) + 1)), yPos + 100);
        updatePositionNode(kDimensionalNode.getRightChild(), xPos
                + (60*(countChild(kDimensionalNode.getLeftChild()) + 1)), yPos + 100);
    }
    
    private int countChild(KDimensionalNode kDimensionalNode){
        if(kDimensionalNode == null) return 0;
        return 1 + countChild(kDimensionalNode.getLeftChild()) 
                + countChild(kDimensionalNode.getRightChild());
    }

    @Override
    public String getName() {
        return Dictionary.Words.NAME_KDIMENSIONALTREE.getString();
    }

    @Override
    public void setEmpty() {
        this.root = null;
        super.setSize(0);
    }
    
    private boolean searchNodePreInsert(Node node) {
        return searchNodePreInsert(this.getRoot(), node);
    }

    @Override
    public boolean isEmpty() {
        return this.getRoot() == null;
    }

    private void removeNode(KDimensionalNode current, int k, boolean paint) {
        if(current.isLeaves()){
            if(current.equals(root)){
                this.root = null;
                super.setSize(0);
                return;
            }
            if(current.isLeftChild(current.getParent())){
                ControlTreePanel.removeRow(current);
                current.getParent().setLeftChild(null);
                super.setSizeDown();
            }else{
                ControlTreePanel.removeRow(current);
                current.getParent().setRightChild(null);
                super.setSizeDown();
            }
            return;
        }
        KDimensionalNode minNode = new KDimensionalNode();
        if(current.getRightChild() != null){ // Truong hop co con phai
            if(paint)
                addTextDisplay("Trường hợp có con phải. Tìm gá trị minnode nhánh phải");
            minNode = searchMinNode(current.getRightChild(), k);
            if(paint){
                runAnimationSwap(current.getxPos(), current.getyPos(), minNode.getxPos(), minNode.getyPos());
            }
        }else{
            if(paint)// Truong hop khong co con phai
                addTextDisplay("Trường hợp không có con phải (RightChild = null). Tìm gá trị minnode nhánh trái");
            minNode = searchMinNode(current.getLeftChild(), k);
            if(paint){                
                runAnimationSwap(current.getxPos(), current.getyPos(), minNode.getxPos(), minNode.getyPos());
            }
            if(paint)
                addTextDisplay("Chuyển con trái thành con phải");
            current.setRightChild(current.getLeftChild());
            current.setLeftChild(null);
        }
        Process.setNodeDelete(new InfoNode(current.getLabel(), current.getPoint())
                                                ,new InfoNode(minNode.getLabel(), minNode.getPoint()));
        
        ControlTreePanel.swapValue(current.getLabel(),minNode.getLabel());
//        Process.treePaint.setColor(current);
//        Process.treePaint.setColor(minNode);
        if(paint)
            addTextDisplay("Hoán đổi giá trị nut " 
                + getStringNode(current.getLabel(), current.getPoint()) 
                + " và " + getStringNode(minNode.getLabel(), minNode.getPoint()));
        current.swapNodeKD(minNode);
        k = current.getLevel() % super.getNumOfDimension();
        removeNode(minNode, k , paint);
    }
    
    private void updatePosAfterSwap(KDimensionalNode current, int distance){
        
        if(current == null) return;
        
        current.setxPos(current.getxPos() + 2*distance);
        updatePosAfterSwap(current.getLeftChild(), distance);
        updatePosAfterSwap(current.getRightChild(), distance);
    }
    
    private void runAnimationSwap(int xs, int ys, int xf, int yf){

        int u1 = (xf - xs);
        int u2 = (yf - ys);
        
        for(double t = 0.0; t <= 1.0; t+= 0.001){
            int x1 = (int) (xs + t*u1);
            int y1 = (int) (ys + t*u2);
            
            int x2 = (int) (xf - t*u1);
            int y2 = (int) (yf - t*u2);
            
            Process.addPointSwap(new Point2D(x1, y1), new Point2D(x2, y2));
        }
        
        Process.addPointSwap(new Point2D(-1000, -1000), new Point2D(-1000, -1000));
    }
    private void runAnimationSearch(int xs, int ys, int xf, int yf, boolean isLeave){

        int u1 = (xf - xs);
        int u2 = (yf - ys);
        
        for(double t = 0.0; t <= 1.0; t+= 0.001){
            int x1 = (int) (xs + t*u1);
            int y1 = (int) (ys + t*u2);
            
            Process.addPointSearch(new Point2D(x1, y1));
        }
        
        
        //if(!isLeave)
        for(int i=0; i<500; i++){
            Process.addPointSearch(new Point2D(xf, yf));
        }
    }

    private KDimensionalNode searchMinNode(KDimensionalNode current, int k) {
        if(current == null) return null;
        if(current.isLeaves()) return current;
        
        KDimensionalNode nodeLeft = searchMinNode(current.getLeftChild(), k);
        KDimensionalNode nodeRight = searchMinNode(current.getRightChild(), k);
        
        if(nodeLeft != null && nodeRight != null){
            if(current.less(nodeLeft, k) && current.less(nodeRight, k)){
                return current;
            }else{
                if(nodeLeft.less(nodeRight, k)){
                    return nodeLeft;
                }
                return nodeRight;
            }
        }else if(nodeLeft != null){
            if(nodeLeft.greaterNode(current, k)){
                return current;
            }
            return nodeLeft;
        }else if(nodeRight != null){
            if(current.less(nodeRight, k)){
                return current;
            }
            return nodeRight;
        }
        return current;
    }

    private boolean searchNodePreInsert(KDimensionalNode current, Node node) {
        if(current == null) return false;
        if(node.equals(current)) return true;
        if(node.greaterNode(current, current.getLevel() % super.getNumOfDimension())){
            return searchNodePreInsert(current.getRightChild(), node);
        }return searchNodePreInsert(current.getLeftChild(), node);
    }

    private KDimensionalNode searchNode(KDimensionalNode current, KDimensionalNode node, boolean paint){
        if(current == null){
            if(paint)
                addTextDisplay("Nút = null, không tìm gặp nút " 
                    + getStringNode(current.getLabel(), current.getPoint()) + " có trên cây");
            Process.setNodeSearch(new InfoNode(node.getLabel(), node.getPoint()));
            return null;
        }
        if(current.equalNode(node)){
            if(paint)
                addTextDisplay("Tìm gặp nút " 
                    + getStringNode(current.getLabel(), current.getPoint()) + " có trên cây");
            Process.setNodeSearch(new InfoNode(node.getLabel(), node.getPoint()));
            return current;
        }
        if(node.greaterNode(current, current.getLevel()% super.getNumOfDimension())){
            
            if(paint){
                
                String str = getStringNode(current.getLabel(), current.getPoint());
                String tmp = getStringNode(node.getLabel(), node.getPoint());
                String text = "Nút " +  tmp + " có giá trị tọa độ thứ " 
                        + (int )(current.getLevel() % super.getNumOfDimension() + 1)
                        + " lớn hơn giá trị của " + str + " nên ta rẽ phải";
                addTextDisplay(text);
                
                if(current.getRightChild()!= null){
                    runAnimationSearch(current.getxPos(), current.getyPos()
                                    , current.getRightChild().getxPos(), current.getRightChild().getyPos(), paint);
                }
            }
            
            return searchNode(current.getRightChild(), node, paint);
        }
        if(paint){
            
            String str = getStringNode(current.getLabel(), current.getPoint());
                String tmp = getStringNode(node.getLabel(), node.getPoint());
                String text = "Nút " +  tmp + " có giá trị tọa độ thứ " 
                        + (int )(current.getLevel() % super.getNumOfDimension() + 1)
                        + " nhỏ hơn giá trị của " + str + " nên ta rẽ trái";
                addTextDisplay(text);
            
            if(current.getLeftChild() != null){
                runAnimationSearch(current.getxPos(), current.getyPos()
                    , current.getLeftChild().getxPos(), current.getLeftChild().getyPos(), paint);
            }
        }
        return searchNode(current.getLeftChild(), node, paint);
    }

    @Override
    public void paintTree() {
        Graphics2D g2 = super.getGraphics2D();         
        if(!this.isEmpty()){
            paint(g2,this.getRoot());
        }else{
            g2.setColor(Dictionary.COLOR.DEFAULT.getColor());
            g2.setFont(new Font(Dictionary.Words.DEFAULT_FONT.getString()
                    , Font.BOLD,Dictionary.Font_Size.TREE_TEXT.getValue()) );
            g2.drawString(Dictionary.Words.EMPTY_TREE.getString(),12*3000 + 500, 50);
        }
    }

    private void paint(Graphics2D g2, KDimensionalNode node) {
        if(node == null) return;
        paintNode(g2, node);
        paint(g2, node.getLeftChild());
        paint(g2, node.getRightChild());
    }

    private void paintNode(Graphics2D g, KDimensionalNode node) {
        node.paint(g, super.getNumOfDimension());
    }
    
    private void updateNode(KDimensionalNode node, int value){
        if(node == null) return;

        node.setxPos(node.getxPos() + value);		

        updateNode(node.getLeftChild(), value);
        updateNode(node.getRightChild(), value);
    }

    @Override
    public Node findNode(MouseEvent e) {
        KDimensionalNode ans = findNode(this.getRoot(), e);
        if(ans == null) return null;
        return ans;
    }
    private KDimensionalNode findNode(KDimensionalNode current, MouseEvent ev){
        if(current == null) return null;
        if(current.contains(ev)){
                return current;
        }
        KDimensionalNode l = findNode(current.getLeftChild(), ev);
        KDimensionalNode r = findNode(current.getRightChild(), ev);
        if(l != null) return l;
        if(r != null) return r;
        return null;
    }

    @Override
    public void searchNodeAndPaint(String label, Point point, boolean paint) {
        try{
            KDimensionalNode kDimensionalNode = new KDimensionalNode(label, point);
            addTextDisplay("\n=>TÌM KIẾM NÚT " + getStringNode(label, point));
            KDimensionalNode nodeSearch = searchNode(this.root, kDimensionalNode, paint);
            nodeSearch.setColor(Dictionary.COLOR.BACKGROUND_NODE_WHEN_CHOOSE.getColor());
        }catch(NullPointerException nullPointerException){
            //nullPointerException.printStackTrace();
        }
    }

    @Override
    public void resetColor() {
        resetColor(this.root);
    }
    
    public void resetColor(KDimensionalNode node) {
        if(node == null) return;
        node.setColor(Dictionary.COLOR.BACKGROUND_NODE.getColor());
        resetColor(node.getLeftChild());
        resetColor(node.getRightChild());
    }
}
