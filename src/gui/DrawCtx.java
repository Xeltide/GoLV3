package gui;

import core.EntityMap;
import core.HexMap;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
/**
 * <p>
 * This is the drawing context for the Game of Life. All drawing
 * is handled by the paint.
 * </p>
 * 
 * @author Joshua Abe
 * @version Nov.6th, 2016
 */
@SuppressWarnings("serial")
public class DrawCtx extends JPanel {
  
    private HexMap tileMap;
    private EntityMap lifeMap;
    /**
     * <p>
     * Constructor for the drawing context.
     * Sets the tile map and life map to be drawn.
     * </p>
     * 
     * @param map HexMap containing hex board
     * @param lifeMap EntityMap containing entities
     */
    DrawCtx(HexMap map, EntityMap lifeMap) {
        this.tileMap = map;
        this.lifeMap = lifeMap;
    }
    /**
     * <p>
     * Paints all of the graphics in the HexMap and EntityMap to
     * the screen.
     * </p>
     */
    public void paint(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.clearRect(0, 0, getWidth(), getHeight());
        for (int row = 0; row < tileMap.getRows(); row++) {
            for (int col = 0; col < tileMap.getCols(); col++) {
                tileMap.getNodeAt(row, col).draw(g2d);
                if (lifeMap.getEntityAt(row, col) != null) {
                    lifeMap.getEntityAt(row, col).draw(g2d);
                }
            }
        }
    }
}
