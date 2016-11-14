package ca.bcit.comp2526.a2a;

import javax.swing.JFrame;
/**
 * <p>
 * This is the driver and frame for the Game of Life program.
 * The program steps through the lives of animals as they attempt
 * to survive by eating.
 * </p>
 * 
 * @author Joshua Abe
 * @version 3
 */
@SuppressWarnings("serial")
public class Main extends JFrame {
    
    private DrawCtx draw;
    private HexMap tileMap;
    private EntityMap lifeMap;
    /**
     * <p>
     * Main constructor to initialize the frame and
     * start the game.
     * </p>
     */
    public Main() {
        tileMap = new HexMap(25, 25, 20);
        lifeMap = new EntityMap(tileMap);
        draw = new DrawCtx(tileMap, lifeMap);
        
        add(draw);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
        setSize(tileMap.getScreenWidth(), tileMap.getScreenHeight());
        addMouseListener(new TurnListener(this));
    }
    /**
     * On click, moves all animals by one step.
     */
    public void takeTurn() {
        lifeMap.takeTurn();
        draw.repaint();
    }
    /**
     * Main entry point for the JVM.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Main();
    }

}
