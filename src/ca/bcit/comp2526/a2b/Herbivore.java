package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
/**
 * <p>
 * Herbivore class in the Game Of Life. Eats Plants,
 * dies in 6 turns, color is yellow.
 * </p>
 * 
 * @author Joshua Abe
 * @version Nov.16th, 2016
 */
public class Herbivore extends Animal implements CarnEdible, OmniEdible {
    /**
     * <p>
     * Default constructor for Herbivore. Sets the origin point
     * and radius for the hexagon. Initializes default values
     * for color, health, and moves.
     * </p>
     * 
     * @param origin world space origin
     * @param radius occupied world space
     */
    public Herbivore(Point origin, int radius) {
        super(origin, radius);
        init();
    }
    /**
     * Initializes the remaining traits for the Herbivore.
     */
    private void init() {
        setColor(Color.YELLOW);
        setHealth(6);
    }
    /**
     * Turn order logic for a Herbivore.
     */
    public void takeTurn(ArrayList<Entity> lives) {
        ArrayList<HexNode> validNodes = new ArrayList<HexNode>();
        if (checkMate(validNodes)) {
            mate(validNodes, lives);
        } else if (checkMove(validNodes)) {
            move(validNodes, lives, 1);
        } else {
            if (!(getCurrent().getTerrain() instanceof Water)) {
                setHealth(getHealth() - 1);
                setColor(getColor().darker());
            }
        }
    }
    /**
     * <p>
     * Returns whether mating conditions are met or not while storing
     * valid spawn nodes for babies.
     * </p>
     * 
     * @param validNodes valid spawn locations for babies.
     * @return boolean for mating conditions.
     */
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
    /**
     * <p>
     * Spawns a new Herbivore at a randomly picked valid node.
     * </p>
     * 
     * @param validNodes valid nodes to spawn in.
     * @param lives new Herbivore is added to the turn list.
     */
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
    /**
     * <p>
     * Returns whether valid nodes are available while selecting valid nodes
     * to move to. As soon as a valid node with food is found, that is the only
     * available option in the list.
     * </p>
     * 
     * @param validNodes valid move locations.
     * @return boolean for valid movement.
     */
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
    /**
     * <p>
     * Moves the Herbivore to a valid location and eats, moves,
     * or waits depending on what is in the surrounding nodes.
     * </p>
     */
    protected void move(ArrayList<HexNode> validNodes, ArrayList<Entity> lives, int moves) {
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
     * Sets all edible health to 0 to be destroyed by the World.
     * Refills health and resets color.
     * </p>
     * 
     * @param node location of eating.
     */
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
    /**
     * <p>
     * Removes the Herbivore from the current location. Removal
     * from the turn list (lives) is handled by the World.
     * </p>
     */
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
    /**
     * <p>
     * Draws the Herbivore to the graphics context
     * using the hexagonal representation.
     * </p>
     */
    public void draw(Graphics2D g2d) {
        Color tmpC = g2d.getColor();
        g2d.setColor(getColor());
        getHex().draw(g2d, true);
        g2d.setColor(tmpC);
        Stroke tmpS = g2d.getStroke();
        g2d.setStroke(tmpS);
    }
}
