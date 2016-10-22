package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame {
    
    private DrawCtx draw;
    private Toolkit tk;
    private Dimension screen;
    
    public Main() {
        draw = new DrawCtx(25, 60, 20);
        tk = Toolkit.getDefaultToolkit();
        screen = tk.getScreenSize();
        
        add(draw);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
        setSize(draw.getScreenWidth(), draw.getScreenHeight());
    }
    
    public static void main(String[] args) {
        Main main = new Main();
    }

}
