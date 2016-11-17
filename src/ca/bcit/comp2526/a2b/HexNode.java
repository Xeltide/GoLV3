package ca.bcit.comp2526.a2b;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
/**
 * <p>
 * Combines node functionality with a hexagon for representation.
 * Stores neighbouring HexNodes and Entities currently occupying
 * this HexNode. Also can contain a single Terrain type.
 * </p>
 * 
 * @author Joshua Abe
 * @version Nov.16th, 2016
 */
public class HexNode extends Node {

    private ArrayList<HexNode> linked = new ArrayList<HexNode>();
    private ArrayList<Entity> here = new ArrayList<Entity>();
    private Terrain terrain;
    private Hexagon hex;
    /**
     * Constructor for the HexNode.
     * 
     * @param origin center of the node
     * @param radius radius of hexagon
     */
    public HexNode(Point origin, int radius) {
        super(origin);
        hex = new Hexagon(this.getPoint(), radius);
    }
    /**
     * Constructor for the HexNode without row and col.
     * 
     * @param originX integer for x point
     * @param originY integer for y point
     * @param radius occupied world space
     */
    public HexNode(int originX, int originY, int radius) {
        super(originX, originY);
        hex = new Hexagon(this.getPoint(), radius);
    }
    /**
     * Adds an Entity occupying the HexNode.
     * 
     * @param life new Entity to add.
     */
    public void addEntity(Entity life) {
        here.add(life);
        life.setLinked(linked);
        life.setCurrent(this);
        life.setPoint(getPoint());
    }
    /**
     * Removes an Entity occupying the HexNode.
     * 
     * @param life Entity to remove.
     */
    public void removeEntity(Entity life) {
        Entity current;
        int index;
        for (index = 0; index < here.size(); index++) {
            current = here.get(index);
            if (current.getClass().getName().equals(life.getClass().getName())) {
                break;
            }
        }
        here.remove(index);
    }
    /**
     * Returns the entire list of Entities occupying the HexNode.
     * 
     * @return array of Entities.
     */
    public ArrayList<Entity> getEntities() {
        return here;
    }
    /**
     * Sets the Terrain for the HexNode.
     * 
     * @param newTerrain new Terrain.
     */
    public void setTerrain(Terrain newTerrain) {
        terrain = newTerrain;
    }
    /**
     * Returns the current Terrain for the HexNode.
     * 
     * @return current Terrain.
     */
    public Terrain getTerrain() {
        return terrain;
    }
    /**
     * Adds a linked HexNode to array.
     * 
     * @param node linked HexNode
     */
    public void addLink(HexNode node) {
        linked.add(node);
    }
    /**
     * Returns the array of linked nodes.
     * 
     * @return ArrayList of HexNodes
     */
    public ArrayList<HexNode> getLinks() {
        return linked;
    }
    /**
     * Return the size of the linked nodes.
     * 
     * @return ArrayList size
     */
    public int getLinkSize() {
        return linked.size();
    }
    /**
     * Returns the hexagon representation.
     * 
     * @return Hexagon
     */
    public Hexagon getHex() {
        return hex;
    }
    /**
     * <p>
     * Draw the hexagon representation of the HexNode. Also draws
     * any terrain if available.
     * </p>
     * 
     * @param g2d graphics context
     */
    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
        getHex().draw(g2d, false);
        if (getTerrain() != null) {
            terrain.draw(g2d);
        }
    }

}
