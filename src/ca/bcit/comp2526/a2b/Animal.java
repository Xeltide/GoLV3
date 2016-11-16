package ca.bcit.comp2526.a2b;

import java.awt.Point;
/**
 * <p>
 * This is the abstract class for an animal, defining all of the core
 * functionality that an animal should have in order to work within
 * this system.
 * </p>
 * 
 * @author Joshua Abe
 * @version Nov.06, 2016
 */
abstract class Animal extends Entity {
    
    /**
     * <p>
     * Animal constructor defining it's origin point, world space,
     * entity type, and space it takes.
     * </p>
     * 
     * @param location origin point in space
     * @param id unique world space id
     * @param type entity type
     * @param radius space occupied
     */
    public Animal(Point location, int radius) {
        super(location);
        setHex(getPoint(), radius);
    }
    
    
    /**
     * Picks a new world space id to occupy from the valid list.
     */
    public abstract void move(int moves);
}
