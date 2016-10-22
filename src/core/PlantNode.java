package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

public class PlantNode extends HexNode {

    public PlantNode(Point p, int radius) {
        super(p, radius);
    }

    public PlantNode(int x, int y, int radius) {
        super(x, y, radius);
    }
    
    @Override
    public void draw(Graphics2D g) {
        Stroke tmpS = g.getStroke();
        Color tmpC = g.getColor();
        
        g.setColor(Color.GREEN);
        getHex().draw(g, true);
        g.setColor(tmpC);
    }

}
