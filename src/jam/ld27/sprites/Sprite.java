package jam.ld27.sprites;

import infinitedog.frisky.entities.Entity;
import infinitedog.frisky.events.EventManager;
import infinitedog.frisky.sounds.SoundManager;
import jam.ld27.game.C;
import jam.ld27.tilemap.TileSet;
import java.util.ArrayList;
import java.util.HashMap;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Sprite extends Entity {
    private TileSet tileSet;
    protected int currentFrame;
    private int currentAnimationFrame = 0;
    private String currentAnimation = null;
    private int step;
    private int timer;
    
    //Managers
    protected EventManager evm = EventManager.getInstance();
    protected SoundManager sm = SoundManager.getInstance();
    
    protected HashMap<String, int[]> animations;
    protected ArrayList<Rectangle> bb;
    
    public Sprite(String textureName, int tileWidth, int tileHeight, int s) {
        tileSet = new TileSet(textureName, tileWidth, tileHeight);
        currentFrame = 0;
        animations = new HashMap<String,int[]>();
        bb = new ArrayList<Rectangle>();
        step = s;
        timer = 0;
        setWidth(tileWidth);
        setHeight(tileHeight);
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
        tileSet.render(currentFrame, getX(), getY());
        //drawBB(g);
    }
    
    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        timer += delta;

        if (currentAnimation != null && timer > step) {
            currentAnimationFrame = (currentAnimationFrame + 1) % animations.get(currentAnimation).length;
            currentFrame = animations.get(currentAnimation)[currentAnimationFrame];
            timer = 0;
        }
    }
    
    public void setFrame(int frame) {
        currentFrame = frame;
    }
    
    public void stopAnimation() {
        currentAnimation = null;
    }
    
    public void addAnimation(String name, int[] frames) {
        animations.put(name, frames);
    }
    
    public void setAnimation(String name) {
        currentAnimation = name;
        currentAnimationFrame = 0;
        currentFrame = animations.get(name)[0];
    }
    
    public void addBB(Rectangle r) {
        bb.add(r);
    }
    
    public ArrayList<Rectangle> getBB() {
        return bb;
    }
    
    private void drawBB(Graphics g) {
        float x = getX(), y = getY();
        
        for(Rectangle r: bb) {
            g.drawRect(x + r.getX(), y + r.getY(), r.getWidth(), r.getHeight());
        }
    }
    
    public boolean collideWith(Sprite s) {
        float x = getX(), y = getY();
        
        for(Rectangle r: bb) {
            Rectangle cr = new Rectangle(r.getX() + x, r.getY() + y, r.getWidth(), r.getHeight());
            
            for(Rectangle q: s.getBB()) {
                float sx = s.getX(), sy = s.getY();
                Rectangle cq = new Rectangle(q.getX() + sx, q.getY() + sy, q.getWidth(), q.getHeight());
                                
                if (cr.intersects(cq)) {
                    return true;
                }
            }
        }
            
        return false;
    }
}
