package gui;

import core.EntityMap;
import core.HexMap;
import core.TurnListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame {
    
    private DrawCtx draw;
    private HexMap tileMap;
    private EntityMap lifeMap;
    
    public Main() {
        tileMap = new HexMap(10, 10, 20);
        lifeMap = new EntityMap(tileMap);
        draw = new DrawCtx(tileMap, lifeMap);
        
        add(draw);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
        setSize(tileMap.getScreenWidth(), tileMap.getScreenHeight());
        addMouseListener(new TurnListener(this));
    }
    
    public void takeTurn() {
        lifeMap.takeTurn();
        draw.repaint();
    }
    
    public static void main(String[] args) {
        new Main();
    }

}
