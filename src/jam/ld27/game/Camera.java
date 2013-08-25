package jam.ld27.game;

import infinitedog.frisky.entities.Entity;
import jam.ld27.tilemap.TileMap;
import org.newdawn.slick.GameContainer;

public class Camera {
    private float offsetX;
    private float offsetY;
    private TileMap tileMap;
    private Entity following;
    
    public Camera(TileMap tm) {
        tileMap = tm;
        offsetX = 0;
        offsetY = 0;
        following = null;
    }
    
    public float getOffsetX() {
        return offsetX;
    }
    
    public float getOffsetY() {
        return offsetY;
    }
     
    public void follow(Entity entity) {
        following = entity;
    }
    
    public void centerOn(float x, float y) {
        offsetX = x - C.SCREEN_WIDTH / 2;
        if (offsetX < 0) {
            offsetX = 0;
        } else if(offsetX > (tileMap.getX() + tileMap.getWidth() - C.SCREEN_WIDTH)) {
            offsetX = (tileMap.getX() + tileMap.getWidth() - C.SCREEN_WIDTH);
        }
        offsetY = y - C.SCREEN_HEIGHT / 2;
        
        if (offsetY < 0) {
            offsetY = 0;
        } else if(offsetY > (tileMap.getY() + tileMap.getHeight() - C.SCREEN_HEIGHT)) {
            offsetY = (tileMap.getY() + tileMap.getHeight() - C.SCREEN_HEIGHT);
        }
    }
    
    public void update(GameContainer gc, int delta) {
        if (following != null) {
            this.centerOn(following.getX(), following.getY());
        }
    }
}
