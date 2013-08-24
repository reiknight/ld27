/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld27.tilemap;

import infinitedog.frisky.textures.TextureManager;
import jam.ld27.game.C;
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
    private float cameraOffsetX;
    private float cameraOffsetY;
    
    public TileMap(String textureFileName, int ts) {
        tileSize = ts;
        tileSet = new TileSet(textureFileName, ts);
        cameraOffsetX = 0;
        cameraOffsetY = 0;
    }
    
    public void setMap(int[][] m) {
        map = m;
    }
    
    public int[][] getMap() {
        return map;
    }
    
    public void render(GameContainer gc, Graphics g) {
        int i, j, l, ll;
        float x = -cameraOffsetX, y = -cameraOffsetY;
        
        for (i = 0, l = map.length; i < l; i += 1) {
            for (j = 0, ll = map[i].length; j < ll; j += 1) {
                tileSet.render(map[i][j], x, y);
                x += tileSize;
            }
            x = -cameraOffsetX;
            y += tileSize;
        }
    }
    
    public void update(GameContainer gc, int delta) {
        
    }
    
    public void centerCameraOn(float x, float y) {
        cameraOffsetX = x - C.SCREEN_WIDTH / 2;
        cameraOffsetY = y - C.SCREEN_HEIGHT / 2;
    }
}
