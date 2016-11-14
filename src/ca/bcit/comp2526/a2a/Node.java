package ca.bcit.comp2526.a2a;

import java.awt.Graphics;
import java.awt.Point;
/**
 * Node class used to store location data.
 * 
 * @author Joshua Abe
 * @version Nov.6th, 2016
 */
public class Node {

    Point point;
    /**
     * Node constructor to take in Point data.
     * 
     * @param point origin point
     */
    public Node(Point point) {
        this.point = point;
    }
    /**
     * Overloaded constructor to take in x and y origin.
     * 
     * @param originX x value of origin
     * @param originY y value of origin
     */
    public Node(int originX, int originY) {
        point = new Point(originX, originY);
    }
    /**
     * Returns the origin Point.
     * 
     * @return Point
     */
    public Point getPoint() {
        return point;
    }
    /**
     * Returns the origin X value.
     * 
     * @return origin X
     */
    public int getX() {
        return (int) point.getX();
    }
    /**
     * Returns the origin Y value.
     * 
     * @return origin Y
     */
    public int getY() {
        return (int) point.getY();
    }
    /**
     * Used to draw origin in graphics context for testing.
     * 
     * @param gfx drawing context
     */
    public void drawOrigin(Graphics gfx) {
        gfx.drawOval(getX(), getY(), 5, 5);
    }
}
