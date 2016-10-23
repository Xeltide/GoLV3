package core;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gui.Main;

public class TurnListener extends MouseAdapter {
    
    private Main main;
    
    public TurnListener(Main main) {
        this.main = main;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        main.takeTurn();
    }
}
