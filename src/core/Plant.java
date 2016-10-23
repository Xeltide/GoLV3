package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

public class Plant extends Entity {

    Hexagon hex;
    EntityType type = EntityType.PLANT;
    
    public Plant(Point location, RoCo id, int radius, EntityType type) {
        super(location, id, type);
        hex = new Hexagon(getPoint(), radius);
    }
    
    public EntityType getType() {
        return type;
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
