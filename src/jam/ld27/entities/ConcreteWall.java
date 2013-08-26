package jam.ld27.entities;

import jam.ld27.sprites.Player;

public class ConcreteWall extends Wall {

    public ConcreteWall(float x, float y, String textureFileName, int ts) {
        super(x, y, textureFileName, ts);   
        
        //TODO: ultra hardcoded
        frame = 2;
        int[][] cb = {{0,1,0},{1,1,1}};
        collisionBoxes = cb;
    }

    @Override
    void reactionToCollisionWithPlayer(Player player) {
        player.die();
    }

    @Override
    public boolean isDestroyable() {
        return false;
    }
    
}
