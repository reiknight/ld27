package jam.ld27.game;

import infinitedog.frisky.events.InputEvent;
import infinitedog.frisky.game.ManagedGameState;
import jam.ld27.game.C;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author InfiniteDog
 */
public class GameOverState extends ManagedGameState {
    private int score = 0;
    private Image background;
    
    public GameOverState(int stateID) {
        super(stateID);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        em.setGameState(C.States.GAME_OVER_STATE.name);
        evm.addEvent(C.Events.BACK.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_SPACE, (Integer) C.Logic.SELECT_OPTION_DELAY.data));
        evm.addEvent(C.Events.CLOSE_WINDOW.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_ESCAPE));
        
        tm.addTexture(C.Textures.GAME_OVER_BACKGROUND.name, C.Textures.GAME_OVER_BACKGROUND.path);
        background = tm.getTexture(C.Textures.GAME_OVER_BACKGROUND.name);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        em.setGameState(C.States.GAME_OVER_STATE.name);
        background.draw(0,0);
        g.setColor(Color.black);
        g.drawString("press <SPACE> to back", 300, 50);
        g.drawString("Score: " + score, 320, 100);
        em.render(container, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        em.setGameState(C.States.GAME_OVER_STATE.name);
        
        em.update(container, delta);
        evm.update(container, delta);
        
        if(evm.isHappening(C.Events.BACK.name, container)) {
            ((MainState)game.getState(C.States.MAIN_STATE.value)).restart();
            game.enterState(C.States.MAIN_STATE.value, new FadeOutTransition(), new FadeInTransition());
        }
        if(evm.isHappening(C.Events.CLOSE_WINDOW.name, container)) {
            container.exit();
        }
    }
    
    public void setScore(int s) {
        score = s;        
    }
    
}
