/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld27.tilemap;

import infinitedog.frisky.textures.TextureManager;
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
    
    public TileMap(String textureFileName, int ts) {
        tileSize = ts;
        tileSet = new TileSet(textureFileName, ts);
    }
    
    public void setMap(int[][] m) {
        map = m;
    }
    
    public int[][] getMap() {
        return map;
    }
    
    public void render(GameContainer gc, Graphics g) {
        int i, j, l, ll, x = 0, y = 0;
        
        for (i = 0, l = map.length; i < l; i += 1) {
            for (j = 0, ll = map[i].length; j < ll; j += 1) {
                tileSet.render(map[i][j], x, y);
                x += tileSize;
            }
            x = 0;
            y += tileSize;
        }
    }
    
    public void update(GameContainer gc, int delta) {
        
    }
}
