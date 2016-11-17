package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
/**
 * Plant class to represent edible plants for Herbivore class.
 * 
 * @author Joshua Abe
 * @version Nov.16th, 2016
 */
public class Plant extends Entity implements HerbEdible, OmniEdible {

    Hexagon hex;
    /**
     * <p>
     * Plant constructor. Sets the origin, world space id,
     * world space size, and type.
     * </p>
     * 
     * @param location origin
     * @param radius world space size
     */
    public Plant(Point location, int radius) {
        super(location);
        hex = new Hexagon(getPoint(), radius);
        setHealth(10);
        setColor(Color.GREEN);
    }
    /**
     * Turn order logic for a Plant.
     */
    public void takeTurn(ArrayList<Entity> lives) {
        ArrayList<HexNode> validNodes = new ArrayList<HexNode>();
        
        if (checkReproduce(validNodes)) {
            grow(validNodes, lives);
        }
        setHealth(getHealth() - 1);
        setColor(getColor().darker());
    }
    /**
     * <p>
     * Returns whether mating conditions are met or not while storing
     * valid spawn nodes for seedlings.
     * </p>
     * 
     * @param validNodes valid spawn locations for seedlings.
     * @return boolean for reproduction conditions.
     */
    private boolean checkReproduce(ArrayList<HexNode> validNodes) {
        Iterator<HexNode> nodeIter = linked.iterator();
        int plants = 0;
        boolean empty;
        while (nodeIter.hasNext()) {
            HexNode currentNode = nodeIter.next();
            empty = true;
            for (Entity inside : currentNode.getEntities()) {
                if (inside instanceof Plant) {
                    plants++;
                }
                empty = false;
            }
            if (currentNode.getTerrain() instanceof Water) {
                empty = false;
            }
            if (empty) {
                validNodes.add(currentNode);
            }
        }
        
        return (plants > 2 && validNodes.size() > 1);
    }
    /**
     * <p>
     * Spawns a new Plant at a randomly picked valid node.
     * </p>
     * 
     * @param validNodes valid nodes to spawn in.
     * @param lives new Herbivore is added to the turn list.
     */
    private void grow(ArrayList<HexNode> validNodes, ArrayList<Entity> living) {
        Random rand = new Random();
        int numToSpawn = rand.nextInt(2) + 1;
        for (int i = 0; i < numToSpawn; i++) {
            int rolled = rand.nextInt(validNodes.size());
            HexNode position = validNodes.get(rolled);
            validNodes.remove(rolled);
            Plant newPlant = new Plant(position.getPoint(),
                    position.getHex().getRadius());
            position.addEntity(newPlant);
            living.add(newPlant);
        }
    }
    /**
     * <p>
     * Removes the Plant from the current location. Removal
     * from the turn list (lives) is handled by the World.
     * </p>
     */
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
    /**
     * <p>
     * Draws the Plant to the graphics context
     * using the hexagonal representation.
     * </p>
     */
    public void draw(Graphics2D g2d) {
        Color tmpC = g2d.getColor();
        g2d.setColor(getColor());
        Stroke tmpS = g2d.getStroke();
        hex.draw(g2d, true);
        g2d.setColor(tmpC);
        g2d.setStroke(tmpS);
    }

}
