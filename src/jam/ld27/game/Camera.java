/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld27.game;

/**
 *
 * @author david
 */
public class Camera {
    private float offsetX;
    private float offsetY;
    
    public Camera() {
        offsetX = 0;
        offsetY = 0;
    }
    
    public float getOffsetX() {
        return offsetX;
    }
    
    public float getOffsetY() {
        return offsetY;
    }
            
    public void centerOn(float x, float y) {
        offsetX = x - C.SCREEN_WIDTH / 2;
        offsetY = y - C.SCREEN_HEIGHT / 2;
    }
}
