package jam.ld27.entities;

import infinitedog.frisky.entities.Entity;
import jam.ld27.game.C;
import jam.ld27.tilemap.TileSet;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public abstract class Wall extends Entity {
    private TileSet tileSet;
    private int tileSize;
    protected int[][] collisionBoxes;
    protected int frame;
    
    public Wall(float x, float y, String textureFileName, int ts) {
      tileSet = new TileSet(textureFileName, ts);
      tileSize = ts;
      setGroup(C.Groups.WALLS.name);
      setPosition(new Vector2f(x, y));
      
      //TODO: ultra hardcoded
      frame = 0;
      int[][] cb = {{1,1,1},{1,1,1}};
      collisionBoxes = cb;
    }
    
    public void render(GameContainer gc, Graphics g) {
        int i, j, l, ll;
        float x = getX(), y = getY();
        
        for (i = 0, l = collisionBoxes.length; i < l; i += 1) {
            for (j = 0, ll = collisionBoxes[i].length; j < ll; j += 1) {
                if (collisionBoxes[i][j] == 1) {
                    // TODO: hardcoded frame
                    tileSet.render(frame, x, y);
                }
                x += tileSize;
            }
            x = getX();
            y += tileSize;
        }
    }
    
    public void update(GameContainer gc, int delta) {
        
    }
    
    public boolean checkCollisionWithPlayer(Player player) {
        int i, j, l, ll;
        float x = getX(), y = getY();
        Shape playerBB = player.getR();
        
        for (i = 0, l = collisionBoxes.length; i < l; i += 1) {
            for (j = 0, ll = collisionBoxes[i].length; j < ll; j += 1) {
                if (collisionBoxes[i][j] == 1) {
                    Shape collisionBoxBB = new Rectangle(x, y, tileSize, tileSize);
                    if (collisionBoxBB.intersects(playerBB)) {
                        reactionToCollisionWithPlayer(player);
                        return true;
                    }
                }
                x += tileSize;
            }
            x = getX();
            y += tileSize;
        }
        
        return false;
    }

    abstract void reactionToCollisionWithPlayer(Player player);
    public abstract boolean isDestroyable();
}
