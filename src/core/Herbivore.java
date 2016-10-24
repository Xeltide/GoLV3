package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Herbivore extends Animal {

    EntityType type = EntityType.HERBIVORE;
    BufferedImage img;
    
    public Herbivore(Point p, RoCo id, int radius, EntityType type) {
        super(p, id, type, radius);
        try{
            img = ImageIO.read(new File("herbIcon.png"));
        } catch (IOException e) {
            System.err.println("Icon missing");
        }
    }
    
    public EntityType getType() {
        return type;
    }
    
    @Override
    public void draw(Graphics2D g) {
        Stroke tmpS = g.getStroke();
        Color tmpC = g.getColor();
        
        g.setColor(Color.YELLOW);
        getHex().draw(g, true);
        g.drawImage(img, getX() - (getRadius() / 2), getY() - (getRadius() / 2), getRadius(), getRadius(), null, null);
        
        g.setStroke(tmpS);
        g.setColor(tmpC);
    }
}
