package gui;

import core.HexMap;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame {
    
    private DrawCtx draw;
    private HexMap map;
    private Toolkit tk;
    private Dimension screen;
    
    public Main() {
        map = new HexMap(25, 60, 20);
        draw = new DrawCtx(map);
        tk = Toolkit.getDefaultToolkit();
        screen = tk.getScreenSize();
        
        add(draw);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
        setSize(map.getScreenWidth(), map.getScreenHeight());
    }
    
    public static void main(String[] args) {
        Main main = new Main();
    }

}
