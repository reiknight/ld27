package jam.ld27.game;
 
import infinitedog.frisky.entities.Entity;
import infinitedog.frisky.events.InputEvent;
import infinitedog.frisky.game.ManagedGameState;
import jam.ld27.entities.ConcreteWall;
import jam.ld27.entities.Enemy;
import jam.ld27.entities.Player;
import jam.ld27.entities.ConcreteWall;
import jam.ld27.entities.Wall;
import jam.ld27.tilemap.TileMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainState extends ManagedGameState {
    private boolean paused = false;
    private Player player;
    private TileMap tileMap;
    private Camera camera;
    private int nEnemies = 10;
    
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
        tm.addTexture(C.Textures.BACKGROUND.name, C.Textures.BACKGROUND.path);
        tm.addTexture(C.Textures.TILE_SET.name, C.Textures.TILE_SET.path);
        
        evm.addEvent(C.Events.CLOSE_WINDOW.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_ESCAPE));
        
        tileMap = new TileMap(75, 25, C.Textures.TILE_SET.name, 32);
        camera = new Camera(tileMap);
        player = new Player();
        camera.follow(player);
        
        em.addEntity(C.Entities.PLAYER.name, player);     
        
        int[][] wallExample1 = {{0,1,0},{1,1,1}};
        em.addEntity(C.Entities.WALL.name + "0", new ConcreteWall(256, 256, wallExample1, C.Textures.TILE_SET.name, 32));        
        int[][] wallExample2 = {{1,1,0},{0,1,1}};
        em.addEntity(C.Entities.WALL.name + "1", new ConcreteWall(512, 256, wallExample2, C.Textures.TILE_SET.name, 32));        

        restart();
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        em.setGameState(C.States.MAIN_STATE.name);

        g.pushTransform();        
        g.translate(-camera.getOffsetX(), -camera.getOffsetY());
        g.drawImage(tm.getTexture(C.Textures.BACKGROUND.name), 0, 0);
        tileMap.render(gc, g);
        em.render(gc, g);
        g.popTransform();
        
        g.drawString("Score: " + player.getScore(), 0, 0);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        em.setGameState(C.States.MAIN_STATE.name);
        evm.update(gc, delta);
        camera.update(gc, delta);
        
        checkEnemiesCollision(gc, game);
        checkPlayerCollision(gc, game);

        // Winning condition
        if (player.collideWithFloor(tileMap)) {
            gameOver(gc, game);
        }
        
        if(evm.isHappening(C.Events.CLOSE_WINDOW.name, gc)) {
            gc.exit();
        }
        em.update(gc, delta);
    }

    void restart() {
        em.setGameState(C.States.MAIN_STATE.name);
        player.respawn();             
        //initEnemies();
    }
    
    private void checkEnemiesCollision(GameContainer gc, StateBasedGame game) {
        ArrayList<Entity> enemies = (ArrayList<Entity>) em.getEntityGroup(C.Groups.ENEMIES.name);
        Iterator it = enemies.iterator();
        Shape playerBB = player.getR();
        
        while(it.hasNext()) {
            Enemy enemy = (Enemy) it.next();
            float x = enemy.getX();
            float width = enemy.getWidth();
            
            if ((x + width) > (tileMap.getX() + tileMap.getWidth()) || (x < 0)) {
                enemy.changeDirection();
            }
            
            if (enemy.getR().intersects(playerBB)){
                player.setScore(0);
                gameOver(gc, game);
                return;
            }
        }
    }

    private void checkPlayerCollision(GameContainer gc, StateBasedGame game) {
        float px = player.getX(), py = player.getY();
        
        if (px < 0) {
            player.setPosition(new Vector2f(0, py));
        } else {
            if((px + player.getWidth()) > (tileMap.getX() + tileMap.getWidth())) {
                player.setPosition(new Vector2f(tileMap.getX() + tileMap.getWidth() - player.getWidth(), py));
            }
        }
        
        ArrayList<Entity> walls = (ArrayList<Entity>) em.getEntityGroup(C.Groups.WALLS.name);
        Iterator it = walls.iterator();
        while(it.hasNext()) {
            Wall wall = (Wall) it.next();
            wall.checkCollisionWithPlayer(player);
        }
    }
    
    private void gameOver(GameContainer gc, StateBasedGame game) {
       ((GameOverState)game.getState(C.States.GAME_OVER_STATE.value)).setScore(player.getScore());
       game.enterState(C.States.GAME_OVER_STATE.value, new FadeOutTransition(), new FadeInTransition());
    }

    private void initEnemies() {
        em.removeEntityGroup(C.Groups.ENEMIES.name);
        em.forceRemoval();
        
        int i;
        for (i = 0; i < nEnemies; i += 1) {
            float x = new Random().nextFloat() * tileMap.getWidth() - tileMap.getTileSize();
            float y = new Random().nextFloat() * tileMap.getHeight() - tileMap.getTileSize();
            em.addEntity(C.Entities.ENEMY.name + i, new Enemy(x, y));
        }
    }
}
