package ca.bcit.comp2526.a2b;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * <p>
 * TurnListener class to attach a mouse clicked event to the
 * Main frame.
 * </p>
 * 
 * @author Joshua Abe
 * @version Nov.6th, 2016
 */
public class TurnListener extends MouseAdapter {
    
    private Main main;
    /**
     * TurnListener constructor. Sets the main reference.
     * 
     * @param main frame for the program
     */
    public TurnListener(Main main) {
        this.main = main;
    }
    /**
     * Calls the take turn method in Main.
     */
    @Override
    public void mouseClicked(MouseEvent evt) {
        main.takeTurn();
    }
}
