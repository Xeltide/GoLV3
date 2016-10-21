package core;

import java.awt.Graphics;
import java.awt.Point;

public class Node {

    Point p;
    
    public Node(Point p) {
        this.p = p;
    }

    public Node(int x, int y) {
        p = new Point(x, y);
    }
    
    public Point getPoint() {
        return p;
    }
    
    public int getX() {
        return (int) p.getX();
    }
    
    public int getY() {
        return (int) p.getY();
    }
    
    public void draw(Graphics g) {
        g.drawOval(getX(), getY(), 5, 5);
    }
}
