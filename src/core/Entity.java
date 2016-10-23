package core;

import java.awt.Graphics2D;
import java.awt.Point;

abstract public class Entity {
    
    private Point p;
    private RoCo id;
    private EntityType type;
    
    public Entity(Point p, RoCo id, EntityType type) {
        this.p = p;
        this.id = id;
        this.type = type;
    }
    
    public Entity(int x, int y, int row, int col, EntityType type) {
        this(new Point(x, y), new RoCo(row, col), type);
    }
    
    public void setPoint(Point p) {
        this.p = p;
    }
    
    public void setPoint(int x, int y) {
        setPoint(new Point(x, y));
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
    
    public RoCo getRoCo() {
        return id;
    }
    
    public void setRoCo(RoCo loc) {
        id = loc;
    }
    
    public EntityType getType() {
        return type;
    }

    public abstract void draw(Graphics2D g);
}
