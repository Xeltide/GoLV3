package core;

import java.awt.Graphics2D;
import java.awt.Point;

abstract public class Entity {
    
    private HexNode loc;
    
    public Entity(HexNode location) {
        this.loc = location;
    }
    
    public Point getPoint() {
        return loc.getPoint();
    }
    
    public int getX() {
        return loc.getX();
    }
    
    public int getY() {
        return loc.getY();
    }
    
    public HexNode getLoc() {
        return loc;
    }
    
    public void setLoc(HexNode newLoc) {
        loc = newLoc;
    }

    public abstract void draw(Graphics2D g);
}
