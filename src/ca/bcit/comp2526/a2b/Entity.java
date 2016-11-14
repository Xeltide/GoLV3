package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
/**
 * Abstract Entity class, that stores all core traits of living things.
 * 
 * @author Joshua Abe
 * @version Nov.6th, 2016
 */
public abstract class Entity {
    
    protected ArrayList<HexNode> linked = new ArrayList<HexNode>();
    protected HexNode current;
    private Hexagon hex;
    private Point point;
    private int health;
    private Color color;
    /**
     * <p>
     * Constructor for an Entity. Sets the origin point,
     * origin id, and type.
     * </p>
     * 
     * @param point origin
     * @param id world space id
     * @param type entity type
     */
    public Entity(Point point) {
        this.point = point;
    }
    /**
     * <p>
     * Overloaded constructor for and Entity. Sets the origin
     * by taking x and y values.
     * </p>
     * 
     * @param originX point x value
     * @param originY point y value
     * @param row row id
     * @param col col id
     * @param type entity type
     */
    public Entity(int originX, int originY) {
        this(new Point(originX, originY));
    }
    /**
     * Sets the origin point.
     * 
     * @param point origin
     */
    public void setPoint(Point point) {
        this.point = point;
    }
    /**
     * Sets the origin point.
     * 
     * @param originX origin x value
     * @param originY origin y value
     */
    public void setPoint(int originX, int originY) {
        setPoint(new Point(originX, originY));
    }
    /**
     * Returns the origin point.
     * 
     * @return origin point
     */
    public Point getPoint() {
        return point;
    }
    /**
     * Returns origin x value.
     * 
     * @return origin x value
     */
    public int getX() {
        return (int) point.getX();
    }
    /**
     * Return origin y value.
     * 
     * @return origin y value
     */
    public int getY() {
        return (int) point.getY();
    }
    
    public void setLinked(ArrayList<HexNode> linked) {
        this.linked = linked;
    }
    /**
     * Representation of an entity is abstract.
     * 
     * @param g2d graphics context
     */
    public abstract void draw(Graphics2D g2d);
    
    public void setCurrent(HexNode hexNode) {
        current = hexNode;
    }
    
    public HexNode getCurrent() {
        return current;
    }
    /**
     * Returns the physical representation hexagon.
     * 
     * @return hexagon object
     */
    public Hexagon getHex() {
        return hex;
    }
    /**
     * Sets the physical representation hexagon.
     * 
     * @param radius occupied world space
     */
    public void setHex(Point origin, int radius) {
        hex = new Hexagon(getPoint(), radius);
    }
    /**
     * Returns the radius of the hexagon.
     * 
     * @return integer radius of hexagon
     */
    public int getRadius() {
        return hex.getRadius();
    }
    /**
     * Returns the health of the animal.
     * 
     * @return integer health
     */
    public int getHealth() {
        return health;
    }
    /**
     * Sets the health of this animal.
     * 
     * @param health new health of the animal
     */
    public void setHealth(int health) {
        this.health = health;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        return color;
    }
    
    public abstract void takeTurn();
    
    public abstract void die();
}