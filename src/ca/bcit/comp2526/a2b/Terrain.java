package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
/**
 * <p>
 * Abstract Terrain class containing non-specific data members
 * for each terrain type. All terrain must include a hexagon,
 * color, initial point and radius.
 * </p>
 * 
 * @author Joshua Abe
 * @version Nov.16th, 2016
 */
public abstract class Terrain {

    protected Hexagon hexagon;
    private Color hexColor;
    private Point point;
    private int radius;
    /**
     * <p>
     * Default constructor for terrain class. Initializes new
     * hexagon to represent the terrain based on origin point
     * and radius.
     * </p>
     * 
     * @param point origin point of hexagon.
     * @param radius of hexagon.
     */
    public Terrain(Point point, int radius) {
        this.point = point;
        this.radius = radius;
        this.hexagon = new Hexagon(point, radius);
    }
    /**
     * Sets the origin point of the terrain.
     * 
     * @param newPoint new origin point.
     */
    public void setPoint(Point newPoint) {
        point = newPoint;
    }
    /**
     * Returns the current origin point.
     * 
     * @return current origin point.
     */
    public Point getPoint() {
        return point;
    }
    /**
     * Returns the current radius of the hexagon.
     * 
     * @return radius of hexagon.
     */
    public int getRadius() {
        return radius;
    }
    /**
     * Sets the hexagon for drawing.
     * 
     * @param newHex hexagon representation.
     */
    public void setHex(Hexagon newHex) {
        hexagon = newHex;
    }
    /**
     * Returns the current hexagon representation.
     * 
     * @return current hexagon.
     */
    public Hexagon getHex() {
        return hexagon;
    }
    /**
     * Sets the draw color.
     * 
     * @param newColor new draw color.
     */
    public void setColor(Color newColor) {
        hexColor = newColor;
    }
    /**
     * Returns the current draw color.
     * 
     * @return draw color.
     */
    public Color getColor() {
        return hexColor;
    }
    /**
     * <p>
     * All terrain must include a visual representation
     * through a draw function.
     * </p>
     * 
     * @param g2d graphics context.
     */
    public abstract void draw(Graphics2D g2d);
}
