/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld27.sprites;

import jam.ld27.game.C;
import jam.ld27.sprites.Wall;
import jam.ld27.sprites.Player;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Reik Val
 */
public class BlowingWall extends Wall {

    private int direccion = 10;
    
    public BlowingWall(String name, float x, float y) {
        super(name, x, y, C.Textures.BLOWING_WALL_SET.name, 260, 170, 500);
        addBB(new Rectangle(0, 0, 260, 170));
    }

    @Override
    void reactionToCollisionWithPlayer(Player player) {
        player.setModX(direccion);
    }

    @Override
    public boolean isDestroyable() {
        return false;
    }
    
}
