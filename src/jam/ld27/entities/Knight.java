/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld27.entities;

import infinitedog.frisky.entities.Entity;
import jam.ld27.game.C;
import jam.ld27.tilemap.TileSet;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Reik Val
 */
public class Knight extends Entity {
    //Position
    private float posX;
    private float posY;
    
    //Movement
    private byte direccion = 1;
    private double velX = 2;
    
    //Graphics
    private int frame = 30;
    private TileSet tileSet = new TileSet(C.Textures.TILE_SET.name, 
            (Integer) C.Logic.TILE_SIZE.data);
    
    public Knight(int height) {
        name = C.Entities.KNIGHT.name;
        group = C.Groups.KNIGHT.name;
        //TODO: Hardcoded height of sprite
        posY = height - 32;
        posX = 400;
        setPosition(new Vector2f(posX, posY));
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
        tileSet.render(frame, getX(), getY());
    }

    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        if(Math.random() < .05 || posX > 600 || posX < 200) {
            direccion *= -1;
            switch(direccion) {
                case 1: frame = 30; break;
                case 2: frame = 31; break;
            }
        }
        posX += velX * direccion;
        setPosition(new Vector2f(posX, posY));
    }
    
}
