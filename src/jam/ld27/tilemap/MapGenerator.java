/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld27.tilemap;

import infinitedog.frisky.entities.EntityManager;
import jam.ld27.entities.ConcreteWall;
import jam.ld27.entities.FragileWall;
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
        
        for(int i = 0; i < rows; i += rows/difficulty) {
            posX = r.nextInt(cols+2)-4;
            posY = r.nextInt(rows/difficulty) + i;
            
            // TODO: hardcoded number of wall Types
            int wallType = r.nextInt(2);
            
            switch(wallType) {
                case 0:
                    entityManager.addEntity(C.Entities.WALL.name + nWall, 
                            new ConcreteWall(posX * 32, posY * 32, C.Textures.TILE_SET.name, 32));
                    break;
                case 1:
                    entityManager.addEntity(C.Entities.WALL.name + nWall,
                            new FragileWall(posX * 32, posY * 32, C.Textures.TILE_SET.name, 32));
                    break;
            }
            
            nWall += 1;            
        }
    }
}
