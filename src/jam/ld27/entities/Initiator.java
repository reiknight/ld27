/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld27.entities;

import infinitedog.frisky.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Reik Val
 */
public class Initiator extends Entity {
    private int heightMax;
    private int h;
    private int frames;
    
    public Initiator(int heighMax) {
        this.heightMax = heighMax;
        this.h = heighMax;
        this.frames = 0;
        this.setPosition(new Vector2f(400, heighMax));
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
        g.drawString(String.format("%.2f", frames/60f), 350, 0);
    }

    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        h -= heightMax/600;
        frames++;
        this.setPosition(new Vector2f(400, h));
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
    
    public int getFrames() {
        return this.frames;
    }
    
    public void setFrames(int frames) {
        this.frames = frames;
    }
    
}
