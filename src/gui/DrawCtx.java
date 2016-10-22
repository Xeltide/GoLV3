package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

import core.HerbivoreNode;
import core.HexMap;
import core.HexNode;
import core.Hexagon;
import core.Node;
import core.PlantNode;

@SuppressWarnings("serial")
public class DrawCtx extends JPanel {
  
    private HexMap map;
    
    DrawCtx(HexMap map) {
        this.map = map;
    }
    
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getCols(); col++) {
                map.getNodeAt(row, col).draw(g2d);
                if (map.getNodeAt(row, col).getInnerNode() != null) {
                    map.getNodeAt(row, col).getInnerNode().draw(g2d);
                }
            }
        }
    }
}
