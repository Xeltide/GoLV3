package core;

import java.awt.Graphics2D;
import java.awt.Point;
/**
 * Abstract Entity class, that stores all core traits of living things.
 * 
 * @author Joshua Abe
 * @version Nov.6th, 2016
 */
public abstract class Entity {
    
    private Point point;
    private RoCo id;
    private EntityType type;
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
    public Entity(Point point, RoCo id, EntityType type) {
        this.point = point;
        this.id = id;
        this.type = type;
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
    public Entity(int originX, int originY, int row, int col, EntityType type) {
        this(new Point(originX, originY), new RoCo(row, col), type);
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
    /**
     * Return world space id.
     * 
     * @return RoCo
     */
    public RoCo getRoCo() {
        return id;
    }
    /**
     * Set world space id.
     * 
     * @param loc new RoCo
     */
    public void setRoCo(RoCo loc) {
        id = loc;
    }
    /**
     * Return entity type.
     * 
     * @return EntityType
     */
    public EntityType getType() {
        return type;
    }
    /**
     * Representation of an entity is abstract.
     * 
     * @param g2d graphics context
     */
    public abstract void draw(Graphics2D g2d);
}
