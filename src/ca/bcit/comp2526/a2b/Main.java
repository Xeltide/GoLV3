package ca.bcit.comp2526.a2b;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.Timer;
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
    private World lifeMap;
    private Timer timer;
    private int steps = 0;
    private boolean running = false;
    /**
     * <p>
     * Main constructor to initialize the frame and
     * start the game.
     * </p>
     */
    public Main() {
        running = true;
        lifeMap = new World(38, 83, 15);
        draw = new DrawCtx(lifeMap);
        
        add(draw);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
        setSize(lifeMap.getScreenWidth(), lifeMap.getScreenHeight());
        addMouseListener(new TurnListener(this));
        timer = new Timer(0, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent event) {
                cycle();
                steps++;
                setTitle("Steps: " + steps);
            }
            
        });
    }
    /**
     * On click, moves all animals by one step.
     */
    public void takeTurn() {
        running = !running;
        if (!running) {
            timer.start();
        } else {
            timer.stop();
        }
    }
    
    public void cycle() {
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
