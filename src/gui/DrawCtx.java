package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import core.EntityMap;
import core.HexMap;

@SuppressWarnings("serial")
public class DrawCtx extends JPanel {
  
    private HexMap tileMap;
    private EntityMap lifeMap;
    
    DrawCtx(HexMap map, EntityMap lifeMap) {
        this.tileMap = map;
        this.lifeMap = lifeMap;
    }
    
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
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
