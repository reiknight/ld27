/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld27.game;

import infinitedog.frisky.entities.Entity;
import org.newdawn.slick.GameContainer;

/**
 *
 * @author david
 */
public class Camera {
    private float offsetX;
    private float offsetY;
    private Entity following;
    
    public Camera() {
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
        offsetY = y - C.SCREEN_HEIGHT / 2;
    }
    
    public void update(GameContainer gc, int delta) {
        if (following != null) {
            this.centerOn(following.getX(), following.getY());
        }
    }
}
