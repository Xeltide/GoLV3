package core;

import java.util.ArrayList;
import java.util.Random;

public class HexMap {

    private HexNode[][] nodeMap;
    private int rows;
    private int cols;
    private int rad;
    
    public HexMap(int rows, int cols, int rad) {
        this.rows = rows;
        this.cols = cols;
        this.rad = rad;
        nodeMap = new HexNode[rows][cols];
        init();
    }
    
    public void init() {
        genHexMap();
    }
    
    private void genHexMap() {
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                if ((col + 1) % 2 != 0) {
                    nodeMap[row][col] = new HexNode(getNewX(getRadius(), col), getNewY(getRadius(), row, true), getRadius());
                } else if ((col + 1) % 2 == 0) {
                    nodeMap[row][col] = new HexNode(getNewX(getRadius(), col), getNewY(getRadius(), row, false), getRadius());
                }
            }
        }
    }
    
    private void setLinks() {
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                if ((row - 1) >= 0 && (col - 1) >= 0) {
                    nodeMap[row][col].addLink(getNodeAt(row - 1, col - 1));
                }
                if ((row - 1) >= 0) {
                    nodeMap[row][col].addLink(getNodeAt(row - 1, col));
                }
                if ((row - 1) >= 0 && (col + 1) < getCols()) {
                    nodeMap[row][col].addLink(getNodeAt(row - 1, col + 1));
                }
                if ((col - 1) >= 0) {
                    nodeMap[row][col].addLink(getNodeAt(row, col - 1));
                }
                if ((col + 1) < getCols()) {
                    nodeMap[row][col].addLink(getNodeAt(row, col + 1));
                }
                if ((row + 1) < getRows()) {
                    nodeMap[row][col].addLink(getNodeAt(row + 1, col));
                }
            }
        }
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
    
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    public int getRadius() {
        return rad;
    }
    
    public HexNode getNodeAt(int row, int col) {
        return nodeMap[row][col];
    }
    
    public int getScreenWidth() {
        return nodeMap[0][getCols() - 1].getX() + (2 * getRadius());
    }
    
    public int getScreenHeight() {
        return nodeMap[getRows() - 1][0].getY() + (3 * getRadius());
    }
}
