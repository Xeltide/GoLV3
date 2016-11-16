package ca.bcit.comp2526.a2b;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;
/**
 * Combines node functionality with a hexagon for representation.
 * 
 * @author Joshua Abe
 * @version Nov.6th, 2016
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
    
    public void addEntity(Entity life) {
        here.add(life);
        life.setLinked(linked);
        life.setCurrent(this);
        life.setPoint(getPoint());
    }
    
    public void removeEntity(Entity life) {
        Entity current;
        int i;
        for (i = 0; i < here.size(); i++) {
            current = here.get(i);
            if (current.getClass().getName().equals(life.getClass().getName())) {
                break;
            }
        }
        here.remove(i);
    }
    
    public void setTerrain(Terrain newTerrain) {
        terrain = newTerrain;
    }
    
    public Terrain getTerrain() {
        return terrain;
    }
    
    public ArrayList<Entity> getEntities() {
        return here;
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
     * Draw the hexagon representation.
     * 
     * @param g2d graphics context
     */
    public void draw(Graphics2D g2d) {
        Stroke tmpS = g2d.getStroke();
        
        g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
        Color tmpC = g2d.getColor();
        getHex().draw(g2d, false);
        if (getTerrain() != null) {
            terrain.draw(g2d);
        }
        g2d.setStroke(tmpS);
        g2d.setColor(tmpC);
    }

}
