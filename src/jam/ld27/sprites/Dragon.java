package jam.ld27.sprites;

import jam.ld27.game.C;
import org.newdawn.slick.geom.Vector2f;

public class Dragon extends Sprite {
    public Dragon() {
        super(C.Textures.DRAGON_SET.name, 675, 425, 500);
        name = C.Entities.DRAGON.name;
        group = C.Groups.DRAGONS.name;
        
        addAnimation("flying", new int[]{0,1});
        setAnimation("flying");
        
        setPosition(new Vector2f(50, 100));
    }
}
