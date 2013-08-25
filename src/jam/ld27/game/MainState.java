package jam.ld27.game;
 
import infinitedog.frisky.entities.Entity;
import infinitedog.frisky.events.InputEvent;
import infinitedog.frisky.game.ManagedGameState;
import jam.ld27.entities.Enemy;
import jam.ld27.entities.Player;
import jam.ld27.entities.Heart;
import jam.ld27.entities.Wall;
import jam.ld27.tilemap.MapGenerator;
import jam.ld27.tilemap.TileMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
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
    private int difficulty;
    private boolean musicOn = false;
        
    public MainState(int stateID)
    {
        super(stateID);
        em.setGameState(C.States.MAIN_STATE.name);
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        em.setGameState(C.States.MAIN_STATE.name);
        //Bind events
        evm.addEvent(C.Events.MOVE_LEFT.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_LEFT));
        evm.addEvent(C.Events.MOVE_RIGHT.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_RIGHT));
        evm.addEvent(C.Events.CLOSE_WINDOW.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_ESCAPE));
        evm.addEvent(C.Events.SOUND_OFF.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_M, 1000));
        
        //Load Textures
        tm.addTexture(C.Textures.CASTLE.name, C.Textures.CASTLE.path);
        tm.addTexture(C.Textures.TILE_SET.name, C.Textures.TILE_SET.path);
        tm.addTexture(C.Textures.HEART.name, C.Textures.HEART.path);
        //Load Sounds
        sm.addMusic(C.Sounds.MUSIC.name, C.Sounds.MUSIC.path);


        tileMap = new TileMap(200, 25, C.Textures.TILE_SET.name, 32);
        camera = new Camera(tileMap);
        player = new Player();
        camera.follow(player);
        em.addEntity(C.Entities.HEART.name(), new Heart(400, 800));
        em.addEntity(C.Entities.PLAYER.name, player);     
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        em.setGameState(C.States.MAIN_STATE.name);

        g.pushTransform();        
        g.translate(-camera.getOffsetX(), -camera.getOffsetY());
        Image castle = tm.getTexture(C.Textures.CASTLE.name);
        tileMap.render(gc, g);
        g.drawImage(castle, 0, tileMap.getY() + tileMap.getHeight() - castle.getHeight());
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
        
        if (player.isDead() || player.collideWithFloor(tileMap)) {
            gameOver(gc, game);
        }
        
        if(evm.isHappening(C.Events.CLOSE_WINDOW.name, gc)) {
            gc.exit();
        }
        if(evm.isHappening(C.Events.SOUND_OFF.name, gc)) {
            if(musicOn)
                sm.getMusic(C.Sounds.MUSIC.name).stop();
            else
                sm.playMusic(C.Sounds.MUSIC.name);
            musicOn = !musicOn;
        }
        if(!((Music)sm.getMusic(C.Sounds.MUSIC.name)).playing() && musicOn) {
            sm.playMusic(C.Sounds.MUSIC.name);
        }
            
        em.update(gc, delta);
    }

    void restart() {
        em.setGameState(C.States.MAIN_STATE.name);
        player.respawn();             
        initEnemies();
        initWalls();        
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
                player.die();
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
            if (wall.checkCollisionWithPlayer(player)) {
                if (wall.isDestroyable()) {
                    em.removeEntity(wall.getName());
                }
                return;
            }
        }
        
        for(Entity e: (ArrayList<Entity>) em.getEntityGroup(C.Groups.HEARTS.name)) {
            Heart heart = (Heart) e;
            if(heart.isActive()) {
                heart.checkCollision(player);
            }
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
    
    public void setDifficulty(int d) {
        difficulty = d;
    }
    
    private void initWalls() {
        MapGenerator mapGenerator = new MapGenerator(em, tileMap, difficulty);
        
        mapGenerator.generateWalls();
    }
}
