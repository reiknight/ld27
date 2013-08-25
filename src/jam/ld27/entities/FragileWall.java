package jam.ld27.entities;

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
        player.setScore(player.getScore() - 200);
        player.setVelY(player.getVelY() / 2);
    }

    @Override
    public boolean isDestroyable() {
        return true;
    }
    
}
