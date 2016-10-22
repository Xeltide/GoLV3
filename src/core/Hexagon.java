package core;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

@SuppressWarnings("serial")
public class Hexagon extends Polygon {

    public final static int SIDES = 6;
    private Point origin;
    private int radius;
    private int[] xPoints;
    private int[] yPoints;
    
    public Hexagon(Point origin, int radius, int xOff, int yOff) {
        xPoints = new int[SIDES];
        yPoints = new int[SIDES];
        this.origin = new Point(origin.x + xOff, origin.y + yOff);
        this.radius = radius;
        setPoints();
    }
    
    public Hexagon(Point origin, int radius) {
        this(origin, radius, 0, 0);
    }
    
    public int getX() {
        return (int) origin.getX();
    }
    
    public int getY() {
        return (int) origin.getY();
    }
    
    private void setPoints() {
        for (int i = 0; i < SIDES; i++) {
            xPoints[i] = (int) (getX() + Math.cos(i * Math.toRadians(60)) * radius);
            yPoints[i] = (int) (getY() + Math.sin(i * Math.toRadians(60)) * radius);
        }
    }
    
    public void draw(Graphics2D g, boolean fill){
        if (fill) {
            g.fillPolygon(xPoints, yPoints, SIDES);
        } else {
            g.drawPolygon(xPoints, yPoints, SIDES);
        }
    }
}
