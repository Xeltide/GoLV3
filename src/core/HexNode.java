package core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

public class HexNode extends Node {

    private Hexagon hex;
    private HexNode innerHex;
    
    public HexNode(Point p, int radius) {
        super(p);
        hex = new Hexagon(this.getPoint(), radius);
    }

    public HexNode(int x, int y, int radius) {
        super(x, y);
        hex = new Hexagon(this.getPoint(), radius);
    }
    
    public HexNode getInnerNode() {
        return innerHex;
    }
    
    public void setInnerNode(HexNode node) {
        innerHex = node;
    }
    
    public Hexagon getHex() {
        return hex;
    }
    
    public void draw(Graphics2D g) {
        Stroke tmpS = g.getStroke();
        Color tmpC = g.getColor();
        
        g.setStroke(new BasicStroke(4, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
        getHex().draw(g, false);
        g.setStroke(tmpS);
    }

}
