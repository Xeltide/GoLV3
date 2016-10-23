package gui;

import core.HexMap;
import core.TurnListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame {
    
    private DrawCtx draw;
    private HexMap map;
    
    public Main() {
        map = new HexMap(25, 60, 20);
        draw = new DrawCtx(map);
        
        add(draw);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
        setSize(map.getScreenWidth(), map.getScreenHeight());
        addMouseListener(new TurnListener(this));
    }
    
    public void takeTurn() {
        map.takeTurn();
    }
    
    public static void main(String[] args) {
        new Main();
    }

}
