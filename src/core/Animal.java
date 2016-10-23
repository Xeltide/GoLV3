package core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

abstract class Animal extends Entity {
    
    private ArrayList<RoCo> valid = new ArrayList<RoCo>();
    private Hexagon hex;
    
    public Animal(Point location, RoCo id, EntityType type, int radius) {
        super(location, id, type);
        hex = new Hexagon(getPoint(), radius);
    }
    
    public void addRoCo(RoCo roco) {
        valid.add(roco);
    }
    
    public void clearValid() {
        valid.clear();
    }
    
    public Hexagon getHex() {
        return hex;
    }
    
    public void setHex(int radius) {
        hex = new Hexagon(getPoint(), radius);
    }
    /*public void addValid(HexNode node) {
        valid.add(node);
    }*/
    
    public void move() {
        Random rand = new Random();
        if (valid.size() == 0) {
            System.out.println("Animal with no options waited.");
        } else {
            setRoCo(valid.get(rand.nextInt(valid.size())));
        }
    }
}
