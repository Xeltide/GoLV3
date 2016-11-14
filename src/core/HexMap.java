package core;
/**
 * <p>
 * Contains the tile map made of hexagons to be drawn under
 * all entity tiles.
 * </p>
 * 
 * @author Joshua Abe
 * @version Nov.6th, 2016
 */
public class HexMap {

    private HexNode[][] nodeMap;
    private int rows;
    private int cols;
    private int rad;
    /**
     * <p>
     * HexMap constructor takes in number of rows,
     * columns, and radius of each node.
     * </p>
     * 
     * @param rows integer
     * @param cols integer
     * @param rad integer
     */
    public HexMap(int rows, int cols, int rad) {
        this.rows = rows;
        this.cols = cols;
        this.rad = rad;
        nodeMap = new HexNode[rows][cols];
        init();
    }
    /**
     * Initializes the hex map.
     */
    public void init() {
        genHexMap();
    }
    /**
     * Fills the HexNode arrays and initializes the new nodes.
     */
    private void genHexMap() {
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
