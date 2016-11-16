package ca.bcit.comp2526.a2b;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.imageio.ImageIO;

public class Carnivore extends Animal implements OmniEdible {

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
    public Carnivore(Point origin, int radius) {
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
        setColor(Color.RED);
        setHealth(3);
        moves = 2;
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
        moves = 2;
    }
    
    private boolean mate(ArrayList<Entity> lives) {
        Random rand = new Random();
        Iterator<HexNode> nodeIter = linked.iterator();
        ArrayList<HexNode> validNodes = new ArrayList<HexNode>();
        HexNode current;
        boolean carn = false;
        boolean empty;
        int food = 0;
        while (nodeIter.hasNext()) {
            empty = true;
            current = nodeIter.next();
            Iterator<Entity> inside = current.getEntities().iterator();
            while (inside.hasNext()) {
                Entity currentEntity = inside.next();
                empty = false;
                if (currentEntity instanceof Omnivore) {
                    carn = true;
                }
                if (currentEntity instanceof OmniEdible) {
                    food++;
                }
            }
            
            if (empty) {
                validNodes.add(current);
            }
        }
        
        if (validNodes.size() > 2 && carn && food > 2) {
            int rolled = rand.nextInt(validNodes.size());
            HexNode position = validNodes.get(rolled);
            Carnivore newCarn = new Carnivore(position.getPoint(),
                    position.getHex().getRadius());
            position.addEntity(newCarn);
            lives.add(newCarn);
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
            if (current.getTerrain() instanceof Water) {
                continue;
            }
            Iterator<Entity> inside = current.getEntities().iterator();
            while (inside.hasNext()) {
                Entity currentEntity = inside.next();
                if (!(currentEntity instanceof CarnEdible) && !(currentEntity instanceof Plant)) {
                    valid = false;
                } else if (currentEntity instanceof CarnEdible) {
                    food = true;
                }
            }
            
            if (valid && food) {
                setHealth(3);
                setColor(Color.RED);
                getCurrent().removeEntity(this);
                current.addEntity(this);
                eat(current);
                setHex(current.getPoint(), getRadius());
                break;
            } else if (valid) {
                validNodes.add(current);
            }
            valid = true;
            food = false;
        }
        
        if (!(valid && food)) {
            if (validNodes.size() > 0 && moves > 0) {
                moves -= 1;
                getCurrent().removeEntity(this);
                validNodes.get(rand.nextInt(validNodes.size())).addEntity(this);
                setHex(current.getPoint(), getRadius());
                move(moves);
            } else {
                moves -= 1;
                setHealth(getHealth() - 1);
                setColor(getColor().darker());
            }
        }
    }
    
    private void eat(HexNode node) {
        Iterator<Entity> here = node.getEntities().iterator();
        while (here.hasNext()) {
            Entity current = here.next();
            if (current instanceof CarnEdible) {
                current.setHealth(0);
            }
        }
    }
    
    public void die() {
        Iterator<Entity> location = getCurrent().getEntities().iterator();
        while (location.hasNext()) {
            Entity current = location.next();
            if (current instanceof Carnivore) {
                location.remove();
                break;
            }
        }
    }
}



