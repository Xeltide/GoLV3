package ca.bcit.comp2526.a2a;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
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
    
    private ArrayList<RoCo> valid = new ArrayList<RoCo>();
    private Hexagon hex;
    private int health;
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
    public Animal(Point location, RoCo id, EntityType type, int radius) {
        super(location, id, type);
        hex = new Hexagon(getPoint(), radius);
    }
    /**
     * Adds a valid world space id.
     * 
     * @param roco world space id
     */
    public void addRoCo(RoCo roco) {
        valid.add(roco);
    }
    /**
     * Empties the list of valid spaces.
     */
    public void clearValid() {
        valid.clear();
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
    public void setHex(int radius) {
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
    /**
     * Picks a new world space id to occupy from the valid list.
     */
    public void move() {
        Random rand = new Random();
        if (valid.size() == 0) {
            System.out.println("Animal with no options waited.");
        } else {
            setRoCo(valid.get(rand.nextInt(valid.size())));
        }
        health -= 1;
    }
}
