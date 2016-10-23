package core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;

public class HexNode extends Node {

    private Hexagon hex;
    private ArrayList<HexNode> linked = new ArrayList<HexNode>();
    private int row;
    private int col;
    
    public HexNode(Point p, int radius, int row, int col) {
        super(p);
        hex = new Hexagon(this.getPoint(), radius);
        this.row = row;
        this.col = col;
    }

    public HexNode(int x, int y, int radius) {
        super(x, y);
        hex = new Hexagon(this.getPoint(), radius);
    }
    
    public void addLink(HexNode node) {
        linked.add(node);
    }
    public ArrayList<HexNode> getLinks() {
        return linked;
    }
    
    public Hexagon getHex() {
        return hex;
    }
    
    public int getLinkSize() {
        return linked.size();
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public void draw(Graphics2D g) {
        Stroke tmpS = g.getStroke();
        Color tmpC = g.getColor();
        
        g.setStroke(new BasicStroke(4, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
        getHex().draw(g, false);
        g.setStroke(tmpS);
        g.setColor(tmpC);
    }

}
