/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld27.tilemap;

import infinitedog.frisky.textures.TextureManager;
import jam.ld27.game.C;
import jam.ld27.game.Camera;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 *
 * @author david
 */
public class TileMap {
    private TileSet tileSet;
    private int tileSize;
    private int[][] map;
    private Camera camera;
    private int rows, cols, posX, posY;
    
    public TileMap(int r, int c, String textureFileName, int ts) {
        int i, j;
        
        rows = r;
        cols = c;
        tileSize = ts;
        tileSet = new TileSet(textureFileName, ts);
        posX = 0;
        posY = 0;
        
        map = new int[rows][cols];
        for (i = 0; i < rows; i += 1) {            
            for (j = 0; j < cols; j += 1) {
                map[i][j] = 0;
            }
        }
    }
    
    public void setMap(int[][] m) {
        map = m;
    }
    
    public int[][] getMap() {
        return map;
    }
    
    public void setCamera(Camera c) {
        camera = c;
    }
    
    public int getWidth() {
        return cols * tileSize;
    }
    
    public int getHeight() {
        return rows * tileSize;
    }
    
    public void render(GameContainer gc, Graphics g) {
        int i, j, l, ll;
        float x = posX, y = posY;
        
        for (i = 0, l = map.length; i < l; i += 1) {
            for (j = 0, ll = map[i].length; j < ll; j += 1) {
                tileSet.render(map[i][j], x, y);
                x += tileSize;
            }
            x = posY;
            y += tileSize;
        }
    }
}
