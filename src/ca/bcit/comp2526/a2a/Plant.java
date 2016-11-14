package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
/**
 * Plant class to represent edible plants for Herbivore class.
 * 
 * @author Joshua Abe
 * @version Nov.6th, 2016
 */
public class Plant extends Entity {

    Hexagon hex;
    EntityType type = EntityType.PLANT;
    /**
     * <p>
     * Plant constructor. Sets the origin, world space id,
     * world space size, and type.
     * </p>
     * 
     * @param location origin
     * @param id world space id
     * @param radius world space size
     * @param type entity type
     */
    public Plant(Point location, RoCo id, int radius, EntityType type) {
        super(location, id, type);
        hex = new Hexagon(getPoint(), radius);
    }
    /**
     * Returns the entity type.
     */
    public EntityType getType() {
        return type;
    }
    /**
     * Renders the graphics representation.
     */
    @Override
    public void draw(Graphics2D g2d) {
        Color tmpC = g2d.getColor();
        g2d.setColor(Color.GREEN);
        Stroke tmpS = g2d.getStroke();
        hex.draw(g2d, true);
        g2d.setColor(tmpC);
        g2d.setStroke(tmpS);
    }

}
