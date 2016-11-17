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

public class Omnivore extends Animal implements CarnEdible {

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
    public Omnivore(Point origin, int radius) {
        super(origin, radius);
        init();
    }
    /**
     * Attempts to initialize herbivore graphic.
     */
    private void init() {
        setColor(Color.MAGENTA);
        setHealth(4);
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
        boolean omni = false;
        boolean empty;
        while (nodeIter.hasNext()) {
            currentNode = nodeIter.next();
            empty = true;
            for (Entity inside : currentNode.getEntities()) {
                if (inside instanceof OmniEdible) {
                    food++;
                } else if (inside instanceof Omnivore) {
                    omni = true;
                }
                empty = false;
            }
            if (empty) {
                validNodes.add(currentNode);
            }
        }
        
        return (validNodes.size() > 2 && food > 2 && omni);
    }
    
    protected void mate(ArrayList<HexNode> validNodes, ArrayList<Entity> lives) {
        Random rand = new Random();
        int numToSpawn = rand.nextInt(2) + 1;
        for (int i = 0; i < numToSpawn; i++) {
            int rolled = rand.nextInt(validNodes.size());
            HexNode position = validNodes.get(rolled);
            validNodes.remove(rolled);
            Omnivore newOmni = new Omnivore(position.getPoint(),
                    position.getHex().getRadius());
            position.addEntity(newOmni);
            lives.add(newOmni);
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
                if (!(inside instanceof OmniEdible)) {
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
            if (inside instanceof OmniEdible) {
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
    
    private void eat(HexNode node) {
        Iterator<Entity> here = node.getEntities().iterator();
        while (here.hasNext()) {
            Entity current = here.next();
            if (current instanceof OmniEdible) {
                current.setHealth(0);
            }
        }
        setHealth(4);
        setColor(Color.MAGENTA);
    }
    
    public void die() {
        Iterator<Entity> location = getCurrent().getEntities().iterator();
        while (location.hasNext()) {
            Entity current = location.next();
            if (current instanceof Omnivore) {
                location.remove();
                break;
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
}
