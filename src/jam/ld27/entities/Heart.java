/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld27.entities;

import infinitedog.frisky.entities.Entity;
import infinitedog.frisky.sounds.SoundManager;
import jam.ld27.game.C;
import jam.ld27.tilemap.TileSet;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Reik Val
 */
public class Heart extends Entity {
    
    private double velY;
    private double velX = .5;
    private int type;
    private int countMovement = 0;
    private static Random r;
    private TileSet tileSet = new TileSet(C.Textures.HEART.name, 
            (Integer) C.Logic.TILE_SIZE.data);
    boolean active = true;
    
    private SoundManager sm = SoundManager.getInstance();
    
    public Heart() {
        name = C.Entities.HEART.name;
        group = C.Groups.HEARTS.name;
        
        if(r == null) {
            r = new Random();
        }
        velY = -1*(r.nextInt(2)+1);
        type = r.nextInt(2);
        
        //TODO: Dimensions hardcode
        setWidth(32);
        setHeight(32);
    }
    
    public Heart(float x, float y) {
        this();
        this.setPosition(new Vector2f(x, y));
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        if(active) {
            super.render(gc, g);
            tileSet.render(type, getX(), getY());
        }
    }

    @Override
    public void update(GameContainer gc, int delta) {
        if(active) {
            super.update(gc, delta);
            float y = getY();
            float x = getX();
            if(++countMovement > 120) {
                countMovement = 0;
                velX *= -1;
            }
            x += velX;
            y += velY;
            this.setPosition(new Vector2f(x, y));
        }
    }
    
    public void checkCollision(Player p) {
        if(p.getR().intersects(this.getR())) {
            p.setScore(p.getScore() + ((int)Math.ceil(-1*velY) + type + 1)*10);
            active = false;
            sm.playSound(C.Sounds.HEART.name);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}
