package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
/**
 * 
 * @author Joshua Abe
 * @version Nov.16th, 2016
 */
public class Carnivore extends Animal implements OmniEdible {

    private int moves;
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
    public Carnivore(Point origin, int radius) {
        super(origin, radius);
        init();
    }
    /**
     * Initializes the remaining traits for the Carnivore.
     */
    private void init() {
        setColor(Color.RED);
        setHealth(3);
        setMoves(2);
    }
    /**
     * Turn order logic for a Carnivore.
     */
    public void takeTurn(ArrayList<Entity> lives) {
        ArrayList<HexNode> validNodes = new ArrayList<HexNode>();
        if (checkMate(validNodes)) {
            mate(validNodes, lives);
        } else if (checkMove(validNodes)) {
            move(validNodes, lives, getMoves());
        } else {
            setHealth(getHealth() - 1);
            setColor(getColor().darker());
            setMoves(2);
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
        boolean carn = false;
        boolean empty;
        while (nodeIter.hasNext()) {
            currentNode = nodeIter.next();
            empty = true;
            for (Entity inside : currentNode.getEntities()) {
                if (inside instanceof CarnEdible) {
                    food++;
                } else if (inside instanceof Carnivore) {
                    carn = true;
                }
                if (!(inside instanceof Plant)) {
                    empty = false;
                }
            }
            if (empty) {
                validNodes.add(currentNode);
            }
        }
        
        return (validNodes.size() > 2 && food > 0 && carn);
    }
    /**
     * <p>
     * Spawns a new Carnivore at a randomly picked valid node.
     * </p>
     * 
     * @param validNodes valid nodes to spawn in.
     * @param lives new Carnivore is added to the turn list.
     */
    protected void mate(ArrayList<HexNode> validNodes, ArrayList<Entity> lives) {
        Random rand = new Random();
        int rolled = rand.nextInt(validNodes.size());
        HexNode position = validNodes.get(rolled);
        validNodes.remove(rolled);
        Carnivore newCarn = new Carnivore(position.getPoint(),
                position.getHex().getRadius());
        position.addEntity(newCarn);
        lives.add(newCarn);
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
            if (currentNode.getTerrain() instanceof Water) {
                continue;
            }
            for (Entity inside : currentNode.getEntities()) {
                if (!(inside instanceof CarnEdible) && !(inside instanceof Plant)) {
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
    protected void move(ArrayList<HexNode> validNodes, ArrayList<Entity> lives, int moves) {
        Random rand = new Random();
        HexNode newNode = validNodes.get(0);
        boolean food = false;
        int rolled;
        for (Entity inside : newNode.getEntities()) {
            if (inside instanceof CarnEdible) {
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
            if (moves > 1) {
                setMoves(getMoves() - 1);
                takeTurn(lives);
            } else {
                setHealth(getHealth() - 1);
                setColor(getColor().darker());
                setMoves(2);
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
            if (current instanceof CarnEdible) {
                current.setHealth(0);
            }
        }
        setColor(Color.RED);
        setHealth(3);
    }
    /**
     * <p>
     * Removes the Carnivore from the current location. Removal
     * from the turn list (lives) is handled by the World.
     * </p>
     */
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
    /**
     * Sets the number of available moves.
     * 
     * @param moves number of new moves.
     */
    private void setMoves(int moves) {
        this.moves = moves;
    }
    /**
     * Returns the number of moves available.
     * 
     * @return current moves.
     */
    private int getMoves() {
        return moves;
    }
    /**
     * <p>
     * Draws the Herbivore to the graphics context
     * using the hexagonal representation and color.
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



