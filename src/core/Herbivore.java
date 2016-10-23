package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Herbivore extends Animal {

    Hexagon hex;
    
    public Herbivore(HexNode location, int radius) {
        super(location);
        hex = new Hexagon(getPoint(), radius);
    }
    
    @Override
    public void draw(Graphics2D g) {
        Stroke tmpS = g.getStroke();
        Color tmpC = g.getColor();
        
        g.setColor(Color.YELLOW);
        hex.draw(g, true);
        
        g.setStroke(tmpS);
        g.setColor(tmpC);
    }

}
