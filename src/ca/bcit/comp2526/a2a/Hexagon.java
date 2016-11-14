package ca.bcit.comp2526.a2a;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
/**
 * Hexagon class to calculate points and draw.
 * 
 * @author Joshua Abe
 * @version Nov.6th, 2016
 */
@SuppressWarnings("serial")
public class Hexagon extends Polygon {

    public static final int SIDES = 6;
    private Point origin;
    private int radius;
    private int[] oxPoints;
    private int[] oyPoints;
    /**
     * <p>
     * Constructor for Hexagon. Takes in origin point,
     * x and y offset, and size.
     * </p>
     * 
     * @param origin point in world space
     * @param radius occupied world space
     * @param xoff all point x offset
     * @param yoff all point y offset
     */
    public Hexagon(Point origin, int radius, int xoff, int yoff) {
        oxPoints = new int[SIDES];
        oyPoints = new int[SIDES];
        this.origin = new Point(origin.x + xoff, origin.y + yoff);
        this.radius = radius;
        setPoints();
    }
    /**
     * Overloaded constructor for Hexagon.
     * 
     * @param origin point in world space
     * @param radius occupied world space
     */
    public Hexagon(Point origin, int radius) {
        this(origin, radius, 0, 0);
    }
    /**
     * Returns the x origin.
     * 
     * @return x origin
     */
    public int getX() {
        return (int) origin.getX();
    }
    /**
     * Returns the y origin.
     * 
     * @return y origin
     */
    public int getY() {
        return (int) origin.getY();
    }
    /**
     * Returns radius.
     * 
     * @return radius
     */
    public int getRadius() {
        return radius;
    }
    /**
     * Sets the hexagon origin point.
     * 
     * @param point new origin
     */
    public void setOrigin(Point point) {
        origin = point;
    }
    /**
     * Sets all the initial X and Y point values.
     */
    private void setPoints() {
        for (int i = 0; i < SIDES; i++) {
            oxPoints[i] = (int) (getX() + Math.cos(i * Math.toRadians(60)) * radius);
            oyPoints[i] = (int) (getY() + Math.sin(i * Math.toRadians(60)) * radius);
        }
    }
    /**
     * Drawing context for Hexagon.
     * 
     * @param g2d graphics context
     * @param fill draw or fill polygon
     */
    public void draw(Graphics2D g2d, boolean fill) {
        if (fill) {
            g2d.fillPolygon(oxPoints, oyPoints, SIDES);
        } else {
            g2d.drawPolygon(oxPoints, oyPoints, SIDES);
        }
    }
}
