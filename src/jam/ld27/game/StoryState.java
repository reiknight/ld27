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
public class StoryState extends ManagedGameState {
    private Image background;

    public StoryState(int stateID) {
        super(stateID);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        em.setGameState(C.States.STORY_STATE.name);
        evm.addEvent(C.Events.BACK.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_SPACE, (Integer) C.Logic.SELECT_OPTION_DELAY.data));
        evm.addEvent(C.Events.CLOSE_WINDOW.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_ESCAPE));
        
        
        tm.addTexture(C.Textures.STORY_BACKGROUND.name, C.Textures.STORY_BACKGROUND.path);
        //Add background
        background = tm.getTexture(C.Textures.STORY_BACKGROUND.name);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        em.setGameState(C.States.STORY_STATE.name);
        background.draw(0, 0);
        em.render(container, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        em.setGameState(C.States.STORY_STATE.name);
        
        em.update(container, delta);
        evm.update(container, delta);
        
        if(evm.isHappening(C.Events.BACK.name, container)) {
            game.enterState(C.States.START_STATE.value, new FadeOutTransition(), new FadeInTransition());
        }
        if(evm.isHappening(C.Events.CLOSE_WINDOW.name, container)) {
            container.exit();
        }
    }
    
}
