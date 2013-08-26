package jam.ld27.sprites;

import jam.ld27.game.C;
import jam.ld27.sprites.Wall;
import jam.ld27.sprites.Player;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class ConcreteWall extends Wall {

    public ConcreteWall(String name, float x, float y) {
        super(name, x, y, C.Textures.CONCRETE_WALL_SET.name, 400, 300, 500);
        addBB(new Rectangle(0, 0, 400, 300));
    }

    @Override
    public boolean isDestroyable() {
        return false;
    }

    void reactionToCollisionWithPlayer(Player player) {
        player.die();
    }
    
}
