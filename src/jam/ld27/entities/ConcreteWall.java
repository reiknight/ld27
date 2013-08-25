package jam.ld27.entities;

public class ConcreteWall extends Wall {

    public ConcreteWall(float x, float y, int[][] cb, String textureFileName, int ts) {
        super(x, y, cb, textureFileName, ts);        
    }

    @Override
    void reactionToCollisionWithPlayer(Player player) {
        player.respawn();
    }
    
}
