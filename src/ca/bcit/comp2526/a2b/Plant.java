package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.Iterator;
/**
 * Plant class to represent edible plants for Herbivore class.
 * 
 * @author Joshua Abe
 * @version Nov.6th, 2016
 */
public class Plant extends Entity implements HerbEdible {

    Hexagon hex;
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
    public Plant(Point location, int radius) {
        super(location);
        hex = new Hexagon(getPoint(), radius);
        setHealth(10);
        setColor(Color.GREEN);
    }
    /**
     * Renders the graphics representation.
     */
    @Override
    public void draw(Graphics2D g2d) {
        Color tmpC = g2d.getColor();
        g2d.setColor(getColor());
        Stroke tmpS = g2d.getStroke();
        hex.draw(g2d, true);
        g2d.setColor(tmpC);
        g2d.setStroke(tmpS);
    }
    
    public void die() {
        Iterator<Entity> location = getCurrent().getEntities().iterator();
        while (location.hasNext()) {
            Entity current = location.next();
            if (current instanceof Plant) {
                location.remove();
                break;
            }
        }
    }
    @Override
    public void takeTurn() {
        setHealth(getHealth() - 1);
        setColor(getColor().darker());
    }

}
