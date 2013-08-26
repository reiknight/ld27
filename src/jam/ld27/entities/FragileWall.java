package jam.ld27.entities;

import jam.ld27.sprites.Player;

public class FragileWall extends Wall {

    public FragileWall(float x, float y, String textureFileName, int ts) {
        super(x, y, textureFileName, ts);        
        
        //TODO: ultra hardcoded
        frame = 18;
        int[][] cb = {{1,1,1},{1,1,1}};
        collisionBoxes = cb;
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
