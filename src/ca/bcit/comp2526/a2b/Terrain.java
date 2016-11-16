package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

abstract public class Terrain {

    protected Hexagon hexagon;
    private Color hexColor;
    private Point point;
    private int radius;
    
    public Terrain(Point point, int radius) {
        this.point = point;
        this.radius = radius;
        this.hexagon = new Hexagon(point, radius);
    }
    
    public void setPoint(Point newPoint) {
        point = newPoint;
    }
    
    public Point getPoint() {
        return point;
    }
    
    public int getRadius() {
        return radius;
    }
    
    public void setHex(Hexagon newHex) {
        hexagon = newHex;
    }
    
    public Hexagon getHex() {
        return hexagon;
    }
    
    public void setColor(Color newColor) {
        hexColor = newColor;
    }
    
    public Color getColor() {
        return hexColor;
    }
    
    abstract public void draw(Graphics2D g2d);
}
