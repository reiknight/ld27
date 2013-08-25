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
    private int[][] collisionBoxes;
    private int tileSize;
    
    public Wall(float x, float y, int[][] cb, String textureFileName, int ts) {
      tileSet = new TileSet(textureFileName, ts);
      tileSize = ts;
      collisionBoxes = cb;
      setGroup(C.Groups.WALLS.name);
      setPosition(new Vector2f(x, y));
    }
    
    public void render(GameContainer gc, Graphics g) {
        int i, j, l, ll;
        float x = getX(), y = getY();
        
        for (i = 0, l = collisionBoxes.length; i < l; i += 1) {
            for (j = 0, ll = collisionBoxes[i].length; j < ll; j += 1) {
                if (collisionBoxes[i][j] == 1) {
                    // TODO: hardcoded frame
                    tileSet.render(2, x, y);
                }
                x += tileSize;
            }
            x = getX();
            y += tileSize;
        }
    }
    
    public void update(GameContainer gc, int delta) {
        
    }
    
    public void checkCollisionWithPlayer(Player player) {
        int i, j, l, ll;
        float x = getX(), y = getY();
        Shape playerBB = player.getR();
        
        for (i = 0, l = collisionBoxes.length; i < l; i += 1) {
            for (j = 0, ll = collisionBoxes[i].length; j < ll; j += 1) {
                if (collisionBoxes[i][j] == 1) {
                    Shape collisionBoxBB = new Rectangle(x, y, tileSize, tileSize);
                    if (collisionBoxBB.intersects(playerBB)) {
                        reactionToCollisionWithPlayer(player);
                    }
                }
                x += tileSize;
            }
            x = getX();
            y += tileSize;
        }
    }

    abstract void reactionToCollisionWithPlayer(Player player);
}
