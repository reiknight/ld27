package jam.ld27.sprites;

import jam.ld27.game.C;
import jam.ld27.sprites.Wall;
import jam.ld27.sprites.Player;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class FragileWall extends Wall {

    public FragileWall(String name, float x, float y) {
        super(name, x, y, C.Textures.FRAGILE_WALL_SET.name, 470, 150, 500);
        addBB(new Rectangle(0, 0, 470, 150));
    }

    @Override
    void reactionToCollisionWithPlayer(Player player) {
        //TODO: ultra hardcoded
        player.setVelY(player.getVelY() / 2);
    }

    @Override
    public boolean isDestroyable() {
        return true;
    }
    
}
