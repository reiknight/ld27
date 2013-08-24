/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld27.entities;

import infinitedog.frisky.entities.Entity;
import infinitedog.frisky.events.EventManager;
import jam.ld27.game.C;
import jam.ld27.tiles.TileSet;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Reik Val
 */
public class Player extends Entity {

    //Position
    private float posX;
    private float posY;
    private byte direccion = 0;
            
    //Movement
    private double velX = 0.8;
    private double velY = 0.2;
    
    //Gravity mode
    private boolean gravityActive = true;
    private double g = .001;
    private double maxVelY = 1;
    
    //Graphics
    private int frame = 30;
    private TileSet tileSet = new TileSet(C.Textures.TILE_SET.name, 
            (Integer) C.Logic.TILE_SIZE.data);
    
    //Managers
    private EventManager evm = EventManager.getInstance();
    
    public Player() {
        name = C.Entities.PLAYER.name;
        group = C.Groups.PLAYER.name;
        //TODO: Cuadrado de colisión: "menor al personaje, un 50% apróximadamente, interno a este"
        this.posX = 400;
        this.setPosition(new Vector2f(posX, 20));
    }
    
    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        float x = getX();
        float y = getY();
        //Applying gravity:
        if(gravityActive) {
            if(velY >= maxVelY) {
                velY = maxVelY;
            } else {
                velY += velY*delta*g;
            }
        }
        //Applying movement to character:
        movement(gc, delta);
        //Velocity of the character:
        y += velY*delta;
        x += direccion*velX*delta;
        //Setting the character:
        this.setPosition(new Vector2f(x,y));
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
        
        tileSet.render(frame, getX(), getY());
    }
    
    /**
     * Movement logic.
     * @param gc
     * @param delta 
     */
    private void movement(GameContainer gc, int delta) {
        if(evm.isHappening(C.Events.MOVE_LEFT.name, gc)) {
            direccion = -1;
        } else if(evm.isHappening(C.Events.MOVE_RIGHT.name, gc)) {
            direccion = 1;
        } else {
            direccion = 0;
        }
    }
    
}
