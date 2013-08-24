package jam.ld27.game;
 
import infinitedog.frisky.events.InputEvent;
import infinitedog.frisky.game.ManagedGameState;
import jam.ld27.entities.Player;
import jam.ld27.tilemap.TileMap;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MainState extends ManagedGameState {
    private boolean paused = false;
    private Player player;
    private TileMap tileMap;
    private Camera camera;
    
    public MainState(int stateID)
    {
        super(stateID);
        em.setGameState(C.States.MAIN_STATE.name);
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        em.setGameState(C.States.MAIN_STATE.name);
        //Player movement
        evm.addEvent(C.Events.MOVE_LEFT.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_LEFT));
        evm.addEvent(C.Events.MOVE_RIGHT.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_RIGHT));
        
        //Load Textures
        tm.addTexture(C.Textures.TILE_SET.name, C.Textures.TILE_SET.path);
        
        evm.addEvent(C.Events.CLOSE_WINDOW.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_ESCAPE));
        
        camera = new Camera();
        
        player = new Player();
        camera.follow(player);
        
        em.addEntity(C.Entities.PLAYER.name, player);
        
        tileMap = new TileMap(60, 30, C.Textures.TILE_SET.name, 32);
        
        restart();
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        g.translate(-camera.getOffsetX(), -camera.getOffsetY());
        em.setGameState(C.States.MAIN_STATE.name);
        tileMap.render(gc, g);
        em.render(gc, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        em.setGameState(C.States.MAIN_STATE.name);
        evm.update(gc, delta);
        camera.update(gc, delta);
        
        
        
        if(evm.isHappening(C.Events.CLOSE_WINDOW.name, gc)) {
            gc.exit();
        }
        em.update(gc, delta);
    }

    void restart() {
        
    }
}
