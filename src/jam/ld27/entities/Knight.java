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
    private int frame = 1;
    private TileSet tileSet = new TileSet(C.Textures.KNIGHT_SET.name, 128);
    
    public Knight(int height) {
        name = C.Entities.KNIGHT.name;
        group = C.Groups.KNIGHT.name;
        //TODO: Hardcoded height of sprite
        posY = height - 200;
        posX = 400;
        setPosition(new Vector2f(posX, posY));
        setWidth(128);
        setHeight(128);
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
                case 1: frame = 0; break;
                case 2: frame = 1; break;
            }
        }
        posX += velX * direccion;
        setPosition(new Vector2f(posX, posY));
    }
    
}
