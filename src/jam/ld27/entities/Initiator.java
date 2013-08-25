/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld27.entities;

import infinitedog.frisky.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Reik Val
 */
public class Initiator extends Entity {
    private int heightMax;
    private int h;
    
    public Initiator(int heighMax) {
        this.heightMax = heighMax;
        this.h = heighMax;
        this.setPosition(new Vector2f(400, heighMax));
    }

    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        h -= heightMax/600;
        this.setPosition(new Vector2f(400, h));
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
    
    
}
