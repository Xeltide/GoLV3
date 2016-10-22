package core;

import java.awt.Graphics2D;
import java.awt.Point;

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
    
    public void draw(Graphics2D g) {
        hex.draw(g, false);
    }

}
