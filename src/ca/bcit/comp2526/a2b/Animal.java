package ca.bcit.comp2526.a2b;

import java.awt.Point;
import java.util.ArrayList;
/**
 * <p>
 * This is the abstract class for an animal, defining all of the core
 * functionality that an animal should have in order to work within
 * this system.
 * 
 * Moves should be added to this class for future expansion, but in the
 * current system is unnecessary.
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
     * <p>
     * Determines whether the mating conditions have been met for an Animal
     * and adds valid spawn locations to the validNodes list.
     * </p>
     * 
     * @param validNodes list of valid spawn locations.
     * @return boolean if mating conditions are met.
     */
    protected abstract boolean checkMate(ArrayList<HexNode> validNodes);
    /**
     * <p>
     * Spawns a new Animal at one of the valid locations and adds them to the
     * entity list to be managed by the World.
     * </p>
     * 
     * @param validNodes spawn location for new Animal.
     * @param lives manages all Entities.
     */
    protected abstract void mate(ArrayList<HexNode> validNodes, ArrayList<Entity> lives);
    /**
     * <p>
     * Determines if there are any valid nodes and adds them to the array list.
     * Returns whether valid nodes are available or not.
     * </p>
     * 
     * @param validNodes list of valid locations to move to.
     * @return boolean if valid move locations.
     */
    protected abstract boolean checkMove(ArrayList<HexNode> validNodes);
    /**
     * <p>
     * Moves the Animal to a valid node and adjusts health.
     * Living Entities are included in order to recursively call additional
     * turns if movement is > 1. This allows for the mate function to be
     * called in a later movement if required.
     * </p>
     */
    protected abstract void move(ArrayList<HexNode> validNodes, ArrayList<Entity> lives, int moves);
}
