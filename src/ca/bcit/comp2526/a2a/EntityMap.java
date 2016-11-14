package ca.bcit.comp2526.a2a;

import java.util.ArrayList;
import java.util.Random;
/**
 * <p>
 * Stores all of the entities on the map and manages movement.
 * </p>
 * 
 * @author Joshua Abe
 * @version Nov.6th, 2016
 */
public class EntityMap {

    private HexMap posMap;
    private Entity[][] lifeMap;
    private ArrayList<Animal> animals = new ArrayList<Animal>();
    /**
     * Constructor for the entity map to hold all of the entities.
     * 
     * @param map HexMap
     */
    public EntityMap(HexMap map) {
        posMap = map;
        lifeMap = new Entity[posMap.getRows()][posMap.getCols()];
        init();
    }
    /**
     * Initializes the entity map.
     */
    private void init() {
        genLife();
    }
    /**
     * Generates all plants and herbivores based on percentages.
     */
    private void genLife() {
        Random rand = new Random();
        int rolled;
        for (int row = 0; row < posMap.getRows(); row++) {
            for (int col = 0; col < posMap.getCols(); col++) {
                rolled = rand.nextInt(10);
                if (rolled < 1) {
                    lifeMap[row][col] = new Herbivore(
                            posMap.getNodeAt(row, col).getPoint(), 
                            new RoCo(row, col), posMap.getRadius(), 
                            EntityType.HERBIVORE);
                    animals.add((Animal) lifeMap[row][col]);
                } else if (rolled < 4) {
                    lifeMap[row][col] = new Plant(
                            posMap.getNodeAt(row, col).getPoint(), 
                            new RoCo(row, col), posMap.getRadius(), 
                            EntityType.PLANT);
                }
            }
        }
    }
    
    public Entity getEntityAt(int row, int col) {
        return lifeMap[row][col];
    }
    /**
     * Sets the valid move list for each animal.
     * 
     * @param in Animal set valid moves
     */
    public void setValidMove(Animal in) {
        RoCo id = in.getRoCo();
        switch (in.getType()) {
          case HERBIVORE:
              if ((id.getCo() + 2) % 2 != 0) {
                  if ((id.getRo() - 1) >= 0 && (id.getCo() - 1) >= 0) {
                      if (lifeMap[id.getRo() - 1][id.getCo() - 1] == null 
                              || lifeMap[id.getRo() - 1][id.getCo() - 1].getType() 
                              == EntityType.PLANT) {
                          in.addRoCo(new RoCo(id.getRo() - 1, id.getCo() - 1));
                      }
                  }
                  if ((id.getRo() - 1) >= 0) {
                      if (lifeMap[id.getRo() - 1][id.getCo()] == null 
                              || lifeMap[id.getRo() - 1][id.getCo()].getType() 
                              == EntityType.PLANT) {
                          in.addRoCo(new RoCo(id.getRo() - 1, id.getCo()));
                      }
                  }
                  if ((id.getRo() - 1) >= 0 && (id.getCo() + 1) < posMap.getCols()) {
                      if (lifeMap[id.getRo() - 1][id.getCo() + 1] == null 
                              || lifeMap[id.getRo() - 1][id.getCo() + 1].getType() 
                              == EntityType.PLANT) {
                          in.addRoCo(new RoCo(id.getRo() - 1, id.getCo() + 1));
                      }
                  }
                  if ((id.getCo() - 1) >= 0) {
                      if (lifeMap[id.getRo()][id.getCo() - 1] == null 
                              || lifeMap[id.getRo()][id.getCo() - 1].getType() 
                              == EntityType.PLANT) {
                          in.addRoCo(new RoCo(id.getRo(), id.getCo() - 1));
                      }
                  }
                  if ((id.getCo() + 1) < posMap.getCols()) {
                      if (lifeMap[id.getRo()][id.getCo() + 1] == null 
                              || lifeMap[id.getRo()][id.getCo() + 1].getType() 
                              == EntityType.PLANT) {
                          in.addRoCo(new RoCo(id.getRo(), id.getCo() + 1));
                      }
                  }
                  if ((id.getRo() + 1) < posMap.getRows()) {
                      if (lifeMap[id.getRo() + 1][id.getCo()] == null 
                              || lifeMap[id.getRo() + 1][id.getCo()].getType() 
                              == EntityType.PLANT) {
                          in.addRoCo(new RoCo(id.getRo() + 1, id.getCo()));
                      }
                  }
              } else {
                  if ((id.getRo() + 1) < posMap.getRows() && (id.getCo() - 1) >= 0) {
                      if (lifeMap[id.getRo() + 1][id.getCo() - 1] == null 
                              || lifeMap[id.getRo() + 1][id.getCo() - 1].getType() 
                              == EntityType.PLANT) {
                          in.addRoCo(new RoCo(id.getRo() + 1, id.getCo() - 1));
                      }
                  }
                  if ((id.getRo() - 1) >= 0) {
                      if (lifeMap[id.getRo() - 1][id.getCo()] == null 
                              || lifeMap[id.getRo() - 1][id.getCo()].getType() 
                              == EntityType.PLANT) {
                          in.addRoCo(new RoCo(id.getRo() - 1, id.getCo()));
                      }
                  }
                  if ((id.getRo() + 1) < posMap.getRows() && (id.getCo() + 1) < posMap.getCols()) {
                      if (lifeMap[id.getRo() + 1][id.getCo() + 1] == null 
                              || lifeMap[id.getRo() + 1][id.getCo() + 1].getType() 
                              == EntityType.PLANT) {
                          in.addRoCo(new RoCo(id.getRo() + 1, id.getCo() + 1));
                      }
                  }
                  if ((id.getCo() - 1) >= 0) {
                      if (lifeMap[id.getRo()][id.getCo() - 1] == null 
                              || lifeMap[id.getRo()][id.getCo() - 1].getType() 
                              == EntityType.PLANT) {
                          in.addRoCo(new RoCo(id.getRo(), id.getCo() - 1));
                      }
                  }
                  if ((id.getCo() + 1) < posMap.getCols()) {
                      if (lifeMap[id.getRo()][id.getCo() + 1] == null 
                              || lifeMap[id.getRo()][id.getCo() + 1].getType() 
                              == EntityType.PLANT) {
                          in.addRoCo(new RoCo(id.getRo(), id.getCo() + 1));
                      }
                  }
                  if ((id.getRo() + 1) < posMap.getRows()) {
                      if (lifeMap[id.getRo() + 1][id.getCo()] == null 
                              || lifeMap[id.getRo() + 1][id.getCo()].getType() 
                              == EntityType.PLANT) {
                          in.addRoCo(new RoCo(id.getRo() + 1, id.getCo()));
                      }
                  }
              }
              break;
          default:
              break;
        }
    }
    /**
     * <p>
     * Iterates through all animals, sets valid moves, picks
     * valid move, empties old cell, removes old valid moves,
     * sets new origin point, sets new hex, eats if plant,
     * dies if no health, adds back into life map if survived.
     * </p>
     */
    public void takeTurn() {
        for (int i = 0; i < animals.size(); i++) {
            Animal now = animals.get(i);
            RoCo origin = now.getRoCo();
            
            setValidMove(now);
            now.move();
            lifeMap[origin.getRo()][origin.getCo()] = null;
            now.clearValid();
            now.setPoint(posMap.getNodeAt(animals.get(i).getRoCo().getRo(), 
                    animals.get(i).getRoCo().getCo()).getPoint());
            now.setHex(posMap.getRadius());
            if (lifeMap[now.getRoCo().getRo()][now.getRoCo().getCo()] != null) {
                now.setHealth(5);
            }
            if (now.getHealth() == 0) {
                animals.remove(i);
            } else {
                lifeMap[now.getRoCo().getRo()][now.getRoCo().getCo()] = now;
            }
        }
    }
}
