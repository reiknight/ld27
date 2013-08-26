/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jam.ld27.entities;

/**
 *
 * @author Reik Val
 */
public class BlowingWall extends Wall {

    private int direccion = 10;
    
    public BlowingWall(float x, float y, String textureFileName, int ts) {
        super(x, y, textureFileName, ts);   
        
        //TODO: ultra hardcoded
        frame = 4;
        int[][] cb = {{0,1,0},{1,0,1}};
        collisionBoxes = cb;
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
