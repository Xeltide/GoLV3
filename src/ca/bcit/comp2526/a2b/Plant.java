package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
/**
 * Plant class to represent edible plants for Herbivore class.
 * 
 * @author Joshua Abe
 * @version Nov.6th, 2016
 */
public class Plant extends Entity implements HerbEdible, OmniEdible {

    Hexagon hex;
    /**
     * <p>
     * Plant constructor. Sets the origin, world space id,
     * world space size, and type.
     * </p>
     * 
     * @param location origin
     * @param radius world space size
     */
    public Plant(Point location, int radius) {
        super(location);
        hex = new Hexagon(getPoint(), radius);
        setHealth(10);
        setColor(Color.GREEN);
    }
    /**
     * Renders the graphics representation.
     */
    @Override
    public void draw(Graphics2D g2d) {
        Color tmpC = g2d.getColor();
        g2d.setColor(getColor());
        Stroke tmpS = g2d.getStroke();
        hex.draw(g2d, true);
        g2d.setColor(tmpC);
        g2d.setStroke(tmpS);
    }
    
    public void die() {
        Iterator<Entity> location = getCurrent().getEntities().iterator();
        while (location.hasNext()) {
            Entity current = location.next();
            if (current instanceof Plant) {
                location.remove();
                break;
            }
        }
    }
    
    @Override
    public void takeTurn(ArrayList<Entity> lives) {
        Random rand = new Random();
        Iterator<HexNode> nodeIter = linked.iterator();
        ArrayList<HexNode> validNodes = new ArrayList<HexNode>();
        int plants = 0;
        while (nodeIter.hasNext()) {
            HexNode current = nodeIter.next();
            boolean empty = true;
            for (Entity inside : current.getEntities()) {
                if ((inside instanceof Plant)) {
                    plants++;
                }
                empty = false;
            }
            
            if (current.getTerrain() instanceof Water) {
                empty = false;
            }
            
            if (empty) {
                validNodes.add(current);
            }
            empty = true;
        }
        
        if (plants > 2 && validNodes.size() > 1) {
            int rolled = rand.nextInt(2) + 1;
            for (HexNode seedSpot : validNodes) {
                if (rolled > 0) {
                    Plant newPlant = new Plant(seedSpot.getPoint(),
                            seedSpot.getHex().getRadius());
                    seedSpot.addEntity(newPlant);
                    lives.add(newPlant);
                    rolled -= 1;
                } else {
                    break;
                }
            }
        }
        setHealth(getHealth() - 1);
        setColor(getColor().darker());
    }

}
