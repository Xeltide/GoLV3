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
public class Herbivore extends Animal implements CarnEdible, OmniEdible {

    BufferedImage img;
    private int moves;
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
        moves = 1;
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
    
    public void takeTurn(ArrayList<Entity> lives) {
        if (!mate(lives)) {
            move(moves);
        }
    }
    
    private boolean mate(ArrayList<Entity> lives) {
        Random rand = new Random();
        Iterator<HexNode> nodeIter = linked.iterator();
        ArrayList<HexNode> validNodes = new ArrayList<HexNode>();
        HexNode current;
        boolean herb = false;
        boolean empty;
        int food = 0;
        while (nodeIter.hasNext()) {
            empty = true;
            current = nodeIter.next();
            Iterator<Entity> inside = current.getEntities().iterator();
            while (inside.hasNext()) {
                Entity currentEntity = inside.next();
                empty = false;
                if (currentEntity instanceof Herbivore) {
                    herb = true;
                }
                if (currentEntity instanceof HerbEdible) {
                    food++;
                }
            }
            
            if (empty) {
                validNodes.add(current);
            }
        }
        
        if (validNodes.size() > 1 && herb && food > 1) {
            int numToSpawn = rand.nextInt(2) + 1;
            System.out.println(numToSpawn);
            for (int i = 0; i < numToSpawn; i++) {
                int rolled = rand.nextInt(validNodes.size());
                HexNode position = validNodes.get(rolled);
                validNodes.remove(rolled);
                Herbivore newHerb = new Herbivore(position.getPoint(),
                        position.getHex().getRadius());
                position.addEntity(newHerb);
                lives.add(newHerb);
            }
            return true;
        }
        return false;
    }
    
    @Override
    public void move(int moves) {
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
            HexNode newNode = validNodes.get(rand.nextInt(validNodes.size()));
            if (!(newNode.getTerrain() instanceof Water)) {
                setHealth(getHealth() - 1);
                setColor(getColor().darker());
            }
            getCurrent().removeEntity(this);
            newNode.addEntity(this);
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
