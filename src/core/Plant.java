package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Plant extends Entity {

    Hexagon hex;
    
    public Plant(HexNode location, int radius) {
        super(location);
        hex = new Hexagon(getPoint(), radius);
    }
    
    @Override
    public void draw(Graphics2D g) {
        Stroke tmpS = g.getStroke();
        Color tmpC = g.getColor();
        
        g.setColor(Color.GREEN);
        hex.draw(g, true);
        
        g.setStroke(tmpS);
        g.setColor(tmpC);
    }

}
