package jam.ld27.entities;

import infinitedog.frisky.entities.Entity;
import jam.ld27.game.C;
import jam.ld27.tilemap.TileSet;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Enemy extends Entity{
    private TileSet tileSet = new TileSet(C.Textures.TILE_SET.name, 
        (Integer) C.Logic.TILE_SIZE.data);
    private int velX, velY;
    private int direction = 1;
    
    public Enemy(float x, float y) {
        name = C.Entities.ENEMY.name;
        group = C.Groups.ENEMIES.name;
        this.setPosition(new Vector2f(x, y));
        
        // TODO: width harcoded
        setWidth(32);
        
        // TODO: frame and velX harcoded
        velX = (int) (256 + 100 * new Random().nextFloat());
        velY = 0;
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
        
        // TODO: frame harcoded
        tileSet.render(35, getX(), getY());
    }
    
    @Override
    public void update(GameContainer gc, int delta) {
        float x = getX() + direction * velX * ((float) delta / 1000);
        float y = getY() + velY * ((float) delta / 1000);
        setPosition(new Vector2f(x, y));
    }
    
    public void changeDirection() {
        direction *= -1;
    }
}
