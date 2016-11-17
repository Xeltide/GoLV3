package ca.bcit.comp2526.a2b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
/**
 * <p>
 * Contains the tile map made of hexagons to be drawn under
 * all entity tiles.
 * </p>
 * 
 * @author Joshua Abe
 * @version Nov.6th, 2016
 */
public class World {

    private ArrayList<Entity> entities = new ArrayList<Entity>();
    private ArrayList<Entity> updated = new ArrayList<Entity>();
    private HexNode[][] nodeMap;
    private int rows;
    private int cols;
    private int rad;
    /**
     * <p>
     * HexMap constructor takes in number of rows,
     * columns, and radius of each node. Initializes the
     * board and tiles.
     * </p>
     * 
     * @param rows integer
     * @param cols integer
     * @param rad integer
     */
    public World(int rows, int cols, int rad) {
        this.rows = rows;
        this.cols = cols;
        this.rad = rad;
        nodeMap = new HexNode[rows][cols];
        init();
    }
    /**
     * <p>
     * Initializes the hex map, generates the neighbour listings
     * for each node, and generates the initial seed.
     * </p>
     */
    private void init() {
        genWorld();
        genNeighbours();
        genLife();
    }
    /**
     * <p>
     * Generates the HexNodes that the world is made up of.
     * All HexNodes will contain the World's Entities.
     * </p>
     */
    private void genWorld() {
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                if ((col + 1) % 2 != 0) {
                    nodeMap[row][col] = new HexNode(
                            getNewX(getRadius(), col), 
                            getNewY(getRadius(), row, true), 
                            getRadius());
                } else if ((col + 1) % 2 == 0) {
                    nodeMap[row][col] = new HexNode(
                            getNewX(getRadius(), col),
                            getNewY(getRadius(), row, false), 
                            getRadius());
                }
            }
        }
    }
    /**
     * <p>
     * Generates a list of neighbours for each cell based on the relative
     * visual location of each hexagon.
     * </p>
     */
    private void genNeighbours() {
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                if ((col + 2) % 2 != 0) {
                    if ((row - 1) >= 0 && (col - 1) >= 0) {
                        nodeMap[row][col].addLink(nodeMap[row - 1][col - 1]);
                    }
                    if ((row - 1) >= 0) {
                        nodeMap[row][col].addLink(nodeMap[row - 1][col]);
                    }
                    if ((row - 1) >= 0 && (col + 1) < getCols()) {
                        nodeMap[row][col].addLink(nodeMap[row - 1][col + 1]);
                    }
                    if ((col - 1) >= 0) {
                        nodeMap[row][col].addLink(nodeMap[row][col - 1]);
                    }
                    if ((col + 1) < getCols()) {
                        nodeMap[row][col].addLink(nodeMap[row][col + 1]);
                    }
                    if ((row + 1) < getRows()) {
                        nodeMap[row][col].addLink(nodeMap[row + 1][col]);
                    }
                } else {
                    if ((row + 1) < getRows() && (col - 1) >= 0) {
                        nodeMap[row][col].addLink(nodeMap[row + 1][col - 1]);
                    }
                    if ((row - 1) >= 0) {
                        nodeMap[row][col].addLink(nodeMap[row - 1][col]);
                    }
                    if ((row + 1) < getRows() && (col + 1) < getCols()) {
                        nodeMap[row][col].addLink(nodeMap[row + 1][col + 1]);
                    }
                    if ((col - 1) >= 0) {
                        nodeMap[row][col].addLink(nodeMap[row][col - 1]);
                    }
                    if ((col + 1) < getCols()) {
                        nodeMap[row][col].addLink(nodeMap[row][col + 1]);
                    }
                    if ((row + 1) < getRows()) {
                        nodeMap[row][col].addLink(nodeMap[row + 1][col]);
                    }
                }
            }
        }
    }
    /**<p>
     * Generates the initial life for the World based on
     * the outlined probability.
     * <ul>
     * <li>10% Herbivores</li>
     * <li>10% Carnivores</li>
     * <li>10% Omnivores</li>
     * <li>30% Plants</li>
     * <li>3% Water</li>
     * </ul>
     * </p>
     * 
     */
    private void genLife() {
        Random rand = new Random();
        int rolled;
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                rolled = rand.nextInt(100);
                if (rolled < 10) {
                    Herbivore newHerb = new Herbivore(
                            nodeMap[row][col].getPoint(), getRadius());
                    nodeMap[row][col].addEntity(newHerb);
                    entities.add(newHerb);
                } else if (rolled < 40) {
                    Plant newPlant = new Plant(
                            nodeMap[row][col].getPoint(), getRadius());
                    nodeMap[row][col].addEntity(newPlant);
                    entities.add(newPlant);
                } else if (rolled < 50) {
                    Carnivore newCarn = new Carnivore(
                            nodeMap[row][col].getPoint(), getRadius());
                    nodeMap[row][col].addEntity(newCarn);
                    entities.add(newCarn);
                } else if (rolled < 60) {
                    Omnivore newOmni = new Omnivore(
                            nodeMap[row][col].getPoint(), getRadius());
                    nodeMap[row][col].addEntity(newOmni);
                    entities.add(newOmni);
                } else if (rolled < 63) {
                    Water newWater = new Water(nodeMap[row][col].getPoint(), getRadius());
                    nodeMap[row][col].setTerrain(newWater);
                }
            }
        }
    }
    /**
     * <p>
     * Manages the turns of all living Entities. Death is dealt with before
     * and after every Entities turn to determine if they died or were killed.
     * All new Entities are added to the living Entities at the end of the turn.
     * </p>
     */
    public void takeTurn() {
        Collections.shuffle(entities);
        Iterator<Entity> livesIter = entities.iterator();
        while (livesIter.hasNext()) {
            Entity current = livesIter.next();
            if (current.getHealth() == 0) {
                current.die();
                livesIter.remove();
            } else {
                current.takeTurn(updated);
                if (current.getHealth() == 0) {
                    current.die();
                    livesIter.remove();
                }
            }
        }
        entities.addAll(updated);
        updated.clear();
    }
    /**
     * <p>
     * Returns the x origin for a node based on radius and the column
     * number.
     * 
     * Pre-Condition:
     * <ul>
     * <li>Columns will start at 0</li>
     * <li>Tile type is hexagon</li>
     * </ul>
     * </p>
     * 
     * @param radius the radius of the hex tiles
     * @param col the current column number
     * @return integer of x coordinate
     */
    private int getNewX(int radius, int col) {
        return (radius * (col + 1)) + (col * radius / 2);
    }
    /**
     * <p>
     * Returns the y origin for a node based on radius and the column
     * number.
     * 
     * Pre-Condition:
     * <ul>
     * <li>Columns will start at 0</li>
     * <li>Tile type is hexagon</li>
     * <li>Condition for use is dependent on user</li>
     * </ul>
     * </p>
     * 
     * @param radius the radius of the hex tiles
     * @param row the current row number
     * @param evenCol boolean to determine if radius offset should be applied
     * @return integer of y coordinate
     */
    private int getNewY(int radius, int row, boolean evenCol) {
        if (evenCol) {
            return (int) (radius * (row + 1) * Math.sqrt(3));
        }
        return (int) ((radius * (row + 1) * Math.sqrt(3)) - (radius * Math.sqrt(3) / 2));
    }
    /**
     * Returns rows.
     * 
     * @return integer rows
     */
    public int getRows() {
        return rows;
    }
    /**
     * Returns cols.
     * 
     * @return integer cols
     */
    public int getCols() {
        return cols;
    }
    /**
     * Returns node radius.
     * 
     * @return integer radius
     */
    public int getRadius() {
        return rad;
    }
    /**
     * Returns the specified node in the hex map.
     * 
     * @param row integer
     * @param col integer
     * @return HexNode
     */
    public HexNode getNodeAt(int row, int col) {
        return nodeMap[row][col];
    }
    /**
     * <p>
     * Returns the screen width based on the size of each cell
     * and number of cells.
     * </p>
     * 
     * @return integer screen width
     */
    public int getScreenWidth() {
        return nodeMap[0][getCols() - 1].getX() + (2 * getRadius());
    }
    /**
     * <p>
     * Returns the screen height based on the size of each cell
     * and number of cells.
     * </p>
     * 
     * @return integer screen height
     */
    public int getScreenHeight() {
        return nodeMap[getRows() - 1][0].getY() + (3 * getRadius());
    }
}
