/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld27.tilemap;

import infinitedog.frisky.entities.EntityManager;
import jam.ld27.sprites.BlowingWall;
import jam.ld27.sprites.ConcreteWall;
import jam.ld27.sprites.FragileWall;
import jam.ld27.game.C;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Reik Val
 */
public class MapGenerator {

    private static ArrayList<int[][]> elements;
    private Random r = new Random();
    
    private EntityManager entityManager;
    private TileMap tileMap;
    private int difficulty;
    
    public MapGenerator(EntityManager em, TileMap tm, int d) {
        entityManager = em;
        tileMap = tm;
        difficulty = d;
    }

    public void generateWalls() {
        int rows = tileMap.getRows(), cols = tileMap.getCols();
        
        int posX = 0;
        int posY = 0;
        int nWall = 0;
        
        for(int i = 2*rows/difficulty; i < rows - 2*rows/difficulty; i += rows/difficulty) {
            posX = r.nextInt(cols+2)-4;
            posY = r.nextInt(rows/difficulty) + i;
            
            // TODO: hardcoded number of wall Types
            int wallType = r.nextInt(10);
            
            switch(wallType) {
                case 0:
                    entityManager.addEntity(C.Entities.WALL.name + nWall, 
                            new ConcreteWall(C.Entities.WALL.name + nWall, posX * 32, posY * 32));
                    break;
                case 1: case 2: case 3: case 4:
                    entityManager.addEntity(C.Entities.WALL.name + nWall,
                            new FragileWall(C.Entities.WALL.name + nWall, posX * 32, posY * 32));
                    break;
                case 5: case 6:
                    entityManager.addEntity(C.Entities.WALL.name + nWall,
                            new BlowingWall(C.Entities.WALL.name + nWall, posX * 32, posY * 32));
                    break;
            }
            
            nWall += 1;            
        }
    }
}
