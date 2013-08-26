/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld27.sprites;

import infinitedog.frisky.sounds.SoundManager;
import jam.ld27.game.C;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Reik Val
 */
public class Heart extends Sprite {
    
    private double velY;
    private double velX = .5;
    private int type;
    private int countMovement = 0;
    private static Random r;
    boolean active = true;
    
    private SoundManager sm = SoundManager.getInstance();
    
    public Heart() {
        super(C.Textures.HEART.name, 32, 32, 256);
        name = C.Entities.HEART.name;
        group = C.Groups.HEARTS.name;
        
        if(r == null) {
            r = new Random();
        }
        velY = -1*(r.nextInt(2)+1);
        type = r.nextInt(2);
        addAnimation("heart", new int[]{type});
        setAnimation("heart");

        addBB(new Rectangle(0, 0, 32, 32));
    }
    
    public Heart(float x, float y) {
        this();
        this.setPosition(new Vector2f(x, y));
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        if(active) {
            super.render(gc, g);
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
