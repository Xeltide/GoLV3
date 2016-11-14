package core;

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

    private Hexagon hex;
    private ArrayList<HexNode> linked = new ArrayList<HexNode>();
    private int row;
    private int col;
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
     * Returns the hexagon representation.
     * 
     * @return Hexagon
     */
    public Hexagon getHex() {
        return hex;
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
     * Returns the row.
     * 
     * @return row number
     */
    public int getRow() {
        return row;
    }
    /**
     * Returns the column.
     * 
     * @return column number
     */
    public int getCol() {
        return col;
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
        g2d.setStroke(tmpS);
        g2d.setColor(tmpC);
    }

}
