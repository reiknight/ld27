package jam.ld27.entities;

import infinitedog.frisky.entities.Entity;
import infinitedog.frisky.events.EventManager;
import infinitedog.frisky.sounds.SoundManager;
import jam.ld27.game.C;
import jam.ld27.tilemap.TileSet;
import java.util.ArrayList;
import java.util.HashMap;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Sprite extends Entity {
    private TileSet tileSet;
    private int currentFrame;
    private int currentAnimationFrame = 0;
    private String currentAnimation = null;
    private int step;
    private int timer;
    
    //Managers
    protected EventManager evm = EventManager.getInstance();
    protected SoundManager sm = SoundManager.getInstance();
    
    protected HashMap<String, int[]> animations;
    
    public Sprite(String textureName, int tileWidth, int tileHeight, int s) {
        tileSet = new TileSet(textureName, tileWidth, tileHeight);
        currentFrame = 0;
        animations = new HashMap<String,int[]>();
        step = s;
        timer = 0;
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
        tileSet.render(currentFrame, getX(), getY());
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
    
    public void addAnimation(String name, int[] frames) {
        animations.put(name, frames);
    }
    
    public void setAnimation(String name) {
        currentAnimation = name;
        currentAnimationFrame = 0;
        currentFrame = animations.get(name)[0];
    }
}
