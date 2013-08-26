package jam.ld27.sprites;

import jam.ld27.game.C;
import jam.ld27.sprites.Player;
import org.newdawn.slick.geom.Vector2f;

public abstract class Wall extends Sprite {
    public Wall(String name, float x, float y, String textureName, int tileWidth, int tileHeight, int s) {
        super(textureName, tileWidth, tileHeight, s);
        this.name = name;
        group = C.Groups.WALLS.name;
        setPosition(new Vector2f(x, y));
    }

    public boolean collideWith(Player player) {
        boolean collision = super.collideWith(player);
        if (collision) {
            reactionToCollisionWithPlayer(player);
        }
        return collision;
    }
    abstract void reactionToCollisionWithPlayer(Player player);
    public abstract boolean isDestroyable();
}
