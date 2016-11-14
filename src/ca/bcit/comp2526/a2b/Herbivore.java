package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
    public Herbivore(Point origin, int radius) {
        super(origin, radius);
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
        setColor(Color.YELLOW);
        setHealth(6);
    }
    /**
     * <p>
     * Draws the Herbivore to the graphics context
     * using the hexagonal representation.
     * </p>
     */
    @Override
    public void draw(Graphics2D g2d) {
        Color tmpC = g2d.getColor();
        g2d.setColor(getColor());
        getHex().draw(g2d, true);
        g2d.setColor(tmpC);
        /*g2d.drawImage(img, getX() - (getRadius() / 2) - 1, getY() - (getRadius() / 2) - 1, 
                getRadius(), getRadius(), null, null);*/
        Stroke tmpS = g2d.getStroke();
        g2d.setStroke(tmpS);
        
    }
    
    public void takeTurn() {
        move();
    }
    
    @Override
    public void move() {
        Random rand = new Random();
        Iterator<HexNode> nodeIter = linked.iterator();
        ArrayList<HexNode> validNodes = new ArrayList<HexNode>();
        boolean valid = true;
        boolean food = false;
        HexNode current = null;
        while (nodeIter.hasNext()) {
            current = nodeIter.next();
            for (Entity inside : current.getEntities()) {
                if (!(inside instanceof HerbEdible)) {
                    valid = false;
                } else {
                    food = true;
                }
            }
            
            if (valid && food) {
                break;
            } else if (valid) {
                validNodes.add(current);
            }
            valid = true;
            food = false;
        }
        
        if (valid && food) {
            setHealth(6);
            setColor(Color.YELLOW);
            getCurrent().removeEntity(this);
            eat(current);
            current.addEntity(this);
            setHex(current.getPoint(), getRadius());
        } else if (validNodes.size() > 0) {
            setHealth(getHealth() - 1);
            setColor(getColor().darker());
            getCurrent().removeEntity(this);
            validNodes.get(rand.nextInt(validNodes.size())).addEntity(this);
            setHex(current.getPoint(), getRadius());
        }
    }
    
    private void eat(HexNode node) {
        Iterator<Entity> here = node.getEntities().iterator();
        while (here.hasNext()) {
            Entity current = here.next();
            if (current instanceof HerbEdible) {
                here.remove();
            }
        }
    }
    
    public void die() {
        Iterator<Entity> location = getCurrent().getEntities().iterator();
        while (location.hasNext()) {
            Entity current = location.next();
            if (current instanceof Herbivore) {
                location.remove();
                break;
            }
        }
    }
}
