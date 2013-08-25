/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld27.tilemap;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Reik Val
 */
public class MapGenerator {

    private static ArrayList<int[][]> elements;
    private Random r = new Random();
    
    
    public MapGenerator() {
        elements = new ArrayList<int[][]>();
        elements.add(new int[][]{{0,1,0},{1,1,1}});
        elements.add(new int[][]{{1,1,0},{0,1,1}});
    }
    
    
    public int[][] generateMap(int rows, int cols, int difficulty) {
        int[][] map = new int[rows][cols];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = 0;
            }
        }
        int posX = 0;
        int posY = 0;
        for(int i = 0; i < map.length; i += rows/difficulty) {
            posX = r.nextInt(cols+2)-4;
            posY = r.nextInt(rows/difficulty) + i;
            int[][] element = elements.get(r.nextInt(elements.size()));
            
            for(int m = 0; m < element.length; m++) {
                for(int n = 0; n < element[m].length; n++) {
                    if(posX+n >= 0 && posX+n < cols && posY+m < rows) {
                        map[posY+m][posX+n] = element[m][n];
                    }
                }
            }
            
        }
        return map;
    }
}
