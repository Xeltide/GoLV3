package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

public class HerbivoreNode extends HexNode {

    public HerbivoreNode(Point p, int radius) {
        super(p, radius);
    }

    public HerbivoreNode(int x, int y, int radius) {
        super(x, y, radius);
    }
    
    @Override
    public void draw(Graphics2D g) {
        Stroke tmpS = g.getStroke();
        Color tmpC = g.getColor();
        
        g.setColor(Color.YELLOW);
        getHex().draw(g, true);
        g.setColor(tmpC);
    }

}
