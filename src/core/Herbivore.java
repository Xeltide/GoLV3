package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

public class Herbivore extends Animal {

    EntityType type = EntityType.HERBIVORE;
    
    public Herbivore(Point p, RoCo id, int radius, EntityType type) {
        super(p, id, type, radius);
    }
    
    public EntityType getType() {
        return type;
    }
    
    @Override
    public void draw(Graphics2D g) {
        Stroke tmpS = g.getStroke();
        Color tmpC = g.getColor();
        
        g.setColor(Color.YELLOW);
        getHex().draw(g, true);
        
        g.setStroke(tmpS);
        g.setColor(tmpC);
    }
}
