package jam.ld27.sprites;

import infinitedog.frisky.entities.Entity;
import jam.ld27.game.C;
import jam.ld27.tilemap.TileSet;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Knight extends Sprite {    
    //Movement
    private byte direccion = 1;
    private double velX = 2;
    
    public Knight(int height) {
        super(C.Textures.KNIGHT_SET.name, 128, 128, 256);
        name = C.Entities.KNIGHT.name;
        group = C.Groups.KNIGHT.name;
        
        addBB(new Rectangle(46, 5, 37, 121));
        addBB(new Rectangle(23, 32, 83, 32));
        
        setPosition(new Vector2f(400, height - 200));
    }

    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        if(Math.random() < .05 || getX() > 600 || getX() < 200) {
            direccion *= -1;
            switch(direccion) {
                case 1: currentFrame = 0; break;
                case 2: currentFrame = 1; break;
            }
        }
        float newX = (float) (getX() + velX * direccion);
        setPosition(new Vector2f(newX, getY()));
    }
    
}
