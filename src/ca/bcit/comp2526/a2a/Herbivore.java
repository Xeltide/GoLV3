package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * <p>
 * Defines the Herbivore class. Later iteration plans to have behaviors
 * appropriately placed within class instead of handled by the world.
 * </p>
 * 
 * @author Joshua Abe
 * @version Nov.6th, 2016
 */
public class Herbivore extends Animal {

    EntityType type = EntityType.HERBIVORE;
    BufferedImage img;
    /**
     * <p>
     * Constructor for Herbivore. Sets the origin point,
     * world space size, and entity type.
     * </p>
     * 
     * @param origin world space origin
     * @param id world space id
     * @param radius occupied world space
     * @param type entity type
     */
    public Herbivore(Point origin, RoCo id, int radius, EntityType type) {
        super(origin, id, type, radius);
        init();
    }
    /**
     * Attempts to initialize herbivore graphic.
     */
    private void init() {
        try {
            img = ImageIO.read(new File("herbIcon.png"));
        } catch (IOException e) {
            System.err.println("Icon missing");
        }
        setHealth(5);
    }
    /**
     * Returns the entity type.
     */
    public EntityType getType() {
        return type;
    }
    /**
     * <p>
     * Draws the Herbivore to the graphics context
     * using the hexgaonal representation.
     * </p>
     */
    @Override
    public void draw(Graphics2D g2d) {
        Color tmpC = g2d.getColor();
        g2d.setColor(Color.YELLOW);
        getHex().draw(g2d, true);
        g2d.setColor(tmpC);
        g2d.drawImage(img, getX() - (getRadius() / 2) - 1, getY() - (getRadius() / 2) - 1, 
                getRadius(), getRadius(), null, null);
        Stroke tmpS = g2d.getStroke();
        g2d.setStroke(tmpS);
        
    }
}
