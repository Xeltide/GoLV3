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
        setColor(Color.YELLOW);
        setHealth(6);
    }
    
    public void takeTurn(ArrayList<Entity> lives) {
        ArrayList<HexNode> validNodes = new ArrayList<HexNode>();
        if (checkMate(validNodes)) {
            mate(validNodes, lives);
        } else if (checkMove(validNodes)) {
            move(validNodes, 1);
        } else {
            if (!(getCurrent().getTerrain() instanceof Water)) {
                setHealth(getHealth() - 1);
                setColor(getColor().darker());
            }
        }
    }
    
    protected boolean checkMate(ArrayList<HexNode> validNodes) {
        Iterator<HexNode> nodeIter = linked.iterator();
        HexNode currentNode;
        int food = 0;
        boolean herb = false;
        boolean empty;
        while (nodeIter.hasNext()) {
            currentNode = nodeIter.next();
            empty = true;
            for (Entity inside : currentNode.getEntities()) {
                if (inside instanceof HerbEdible) {
                    food++;
                } else if (inside instanceof Herbivore) {
                    herb = true;
                }
                empty = false;
            }
            if (empty) {
                validNodes.add(currentNode);
            }
        }
        
        return (validNodes.size() > 1 && food > 1 && herb);
    }
    
    protected void mate(ArrayList<HexNode> validNodes, ArrayList<Entity> lives) {
        Random rand = new Random();
        int numToSpawn = rand.nextInt(2) + 1;
        for (int i = 0; i < numToSpawn; i++) {
            int rolled = rand.nextInt(validNodes.size());
            HexNode position = validNodes.get(rolled);
            validNodes.remove(rolled);
            Herbivore newHerb = new Herbivore(position.getPoint(),
                    position.getHex().getRadius());
            position.addEntity(newHerb);
            lives.add(newHerb);
        }
    }
    
    protected boolean checkMove(ArrayList<HexNode> validNodes) {
        Iterator<HexNode> nodeIter = linked.iterator();
        HexNode currentNode;
        boolean valid;
        boolean food;
        validNodes.clear();
        while (nodeIter.hasNext()) {
            currentNode = nodeIter.next();
            valid = true;
            food = false;
            for (Entity inside : currentNode.getEntities()) {
                if (!(inside instanceof HerbEdible)) {
                    valid = false;
                } else {
                    food = true;
                }
            }
            if (valid && food) {
                validNodes.clear();
                validNodes.add(currentNode);
                break;
            } else if (valid) {
                validNodes.add(currentNode);
            }
        }
        
        return (validNodes.size() > 0);
    }
    
    //@Override
    protected void move(ArrayList<HexNode> validNodes, int moves) {
        Random rand = new Random();
        HexNode newNode = validNodes.get(0);
        boolean food = false;
        int rolled;
        for (Entity inside : newNode.getEntities()) {
            if (inside instanceof HerbEdible) {
                food = true;
                break;
            }
        }
        if (food) {
            getCurrent().removeEntity(this);
            newNode.addEntity(this);
            eat(newNode);
            setHex(newNode.getPoint(), getRadius());
        } else {
            rolled = rand.nextInt(validNodes.size());
            newNode = validNodes.get(rolled);
            getCurrent().removeEntity(this);
            newNode.addEntity(this);
            setHex(newNode.getPoint(), getRadius());
            if (!(newNode.getTerrain() instanceof Water)) {
                setHealth(getHealth() - 1);
                setColor(getColor().darker());
            }
        }
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
        Stroke tmpS = g2d.getStroke();
        g2d.setStroke(tmpS);
        
    }
    
    private void eat(HexNode node) {
        Iterator<Entity> here = node.getEntities().iterator();
        while (here.hasNext()) {
            Entity current = here.next();
            if (current instanceof HerbEdible) {
                here.remove();
            }
        }
        setColor(Color.YELLOW);
        setHealth(6);
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
