package jam.ld27.game;

import infinitedog.frisky.events.InputEvent;
import infinitedog.frisky.game.ManagedGameState;
import infinitedog.frisky.gui.Button;
import jam.ld27.entities.CrossHair;
import jam.ld27.game.C;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class StartState extends ManagedGameState {
    private Image background;
    private Button button_start, button_story, button_instructions, button_credits;
    private Button button_easy, button_normal, button_hard;
    private boolean choosingDificulty = false;
    
    private boolean start_game = false;
    
    public StartState(int stateID) {
        super(stateID);
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        em.setGameState(C.States.START_STATE.name);
        //Add events
        evm.addEvent(C.Events.CLICK_BUTTON.name, new InputEvent(InputEvent.MOUSE_CLICK, 
                Input.MOUSE_LEFT_BUTTON, (Integer) C.Logic.SELECT_OPTION_DELAY.data));
                //Crosshair movement
        evm.addEvent(C.Events.CROSSHAIR_MOVED.name, new InputEvent(InputEvent.MOUSE_MOVE, 
                new Rectangle(0, 0, C.SCREEN_WIDTH, C.SCREEN_HEIGHT)));
        //Load textures
        tm.addTexture(C.Textures.START_BACKGROUND.name, C.Textures.START_BACKGROUND.path);
        tm.addTexture(C.Textures.BUTTON_CREDITS.name, C.Textures.BUTTON_CREDITS.path);
        tm.addTexture(C.Textures.BUTTON_PLAY.name, C.Textures.BUTTON_PLAY.path);
        tm.addTexture(C.Textures.BUTTON_INSTRUCTIONS.name, C.Textures.BUTTON_INSTRUCTIONS.path);
        tm.addTexture(C.Textures.BUTTON_EASY.name, C.Textures.BUTTON_EASY.path);
        tm.addTexture(C.Textures.BUTTON_NORMAL.name, C.Textures.BUTTON_NORMAL.path);
        tm.addTexture(C.Textures.BUTTON_HARD.name, C.Textures.BUTTON_HARD.path);
        tm.addTexture(C.Textures.STORY.name, C.Textures.STORY.path);
        tm.addTexture(C.Textures.CROSSHAIR.name, C.Textures.CROSSHAIR.path);
        
        //Load entities
        button_start = new Button(C.Buttons.START_GAME.textureName,
                "button_start", C.Groups.BUTTONS.name,
                C.Buttons.START_GAME.label, C.Buttons.START_GAME.labelPosition);
        button_start.setPosition(C.Buttons.START_GAME.position);
        
        button_story = new Button(C.Buttons.STORY.textureName,
                "button_story", C.Groups.BUTTONS.name,
                C.Buttons.STORY.label, C.Buttons.STORY.labelPosition);
        button_story.setPosition(C.Buttons.STORY.position);
        
        button_instructions = new Button(C.Buttons.INSTRUCTIONS.textureName,
                "button_instructions", C.Groups.BUTTONS.name,
                C.Buttons.INSTRUCTIONS.label, C.Buttons.INSTRUCTIONS.labelPosition);
        button_instructions.setPosition(C.Buttons.INSTRUCTIONS.position);
        
        button_credits = new Button(C.Buttons.CREDITS.textureName,
                "button_credits", C.Groups.BUTTONS.name,
                C.Buttons.INSTRUCTIONS.label, C.Buttons.CREDITS.labelPosition);
        button_credits.setPosition(C.Buttons.CREDITS.position);
        
        button_easy = new Button(C.Buttons.EASY.textureName,
                "button_easy", C.Groups.BUTTONS.name,
                C.Buttons.EASY.label, C.Buttons.EASY.labelPosition);
        button_easy.setPosition(C.Buttons.EASY.position);
        
        button_normal = new Button(C.Buttons.NORMAL.textureName,
                "button_normal", C.Groups.BUTTONS.name,
                C.Buttons.NORMAL.label, C.Buttons.NORMAL.labelPosition);
        button_normal.setPosition(C.Buttons.NORMAL.position);
        
        button_hard = new Button(C.Buttons.HARD.textureName,
                "button_hard", C.Groups.BUTTONS.name,
                C.Buttons.HARD.label, C.Buttons.HARD.labelPosition);
        button_hard.setPosition(C.Buttons.HARD.position);
        
        //Add Crosshair
        em.addEntity(C.Entities.CROSSHAIR.name, new CrossHair());
        //Add background
        background = tm.getTexture(C.Textures.START_BACKGROUND.name);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        em.setGameState(C.States.START_STATE.name);
        background.draw(0, 0);
        em.render(gc, g);
        
        if(choosingDificulty) {
            button_easy.render(gc, g);
            button_normal.render(gc, g);
            button_hard.render(gc, g);
        } else {
            button_start.render(gc, g);
            button_story.render(gc, g);
            button_credits.render(gc, g);
            button_instructions.render(gc, g);
        }
        
        CrossHair crosshair = (CrossHair) em.getEntity(C.Entities.CROSSHAIR.name);
        crosshair.render(gc, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        evm.update(gc, delta);
        em.setGameState(C.States.START_STATE.name);
        em.update(gc, delta);
        
        if(evm.isHappening(C.Events.CLICK_BUTTON.name, gc)) {
            CrossHair crosshair = (CrossHair) em.getEntity(C.Entities.CROSSHAIR.name);
                                
            if (choosingDificulty) {
                if(pm.testCollisionsEntity(crosshair, button_easy)) {
                    ((MainState)game.getState(C.States.MAIN_STATE.value)).setDifficulty(10);
                    ((MainState)game.getState(C.States.MAIN_STATE.value)).restart();
                    game.enterState(C.States.MAIN_STATE.value, new FadeOutTransition(), new FadeInTransition());
                }
                else if(pm.testCollisionsEntity(crosshair, button_normal)) {
                    ((MainState)game.getState(C.States.MAIN_STATE.value)).setDifficulty(25);
                    ((MainState)game.getState(C.States.MAIN_STATE.value)).restart();
                    game.enterState(C.States.MAIN_STATE.value, new FadeOutTransition(), new FadeInTransition());
                }
                else if(pm.testCollisionsEntity(crosshair, button_hard)) {            
                    ((MainState)game.getState(C.States.MAIN_STATE.value)).setDifficulty(50);
                    ((MainState)game.getState(C.States.MAIN_STATE.value)).restart();
                    game.enterState(C.States.MAIN_STATE.value, new FadeOutTransition(), new FadeInTransition());
                }   
            } else {
                if(pm.testCollisionsEntity(crosshair, button_start)) {
                    choosingDificulty = true;
                }
                else if(pm.testCollisionsEntity(crosshair, button_instructions)) {
                    game.enterState(C.States.INSTRUCTIONS_STATE.value, new FadeOutTransition(), new FadeInTransition());
                }
                else if(pm.testCollisionsEntity(crosshair, button_credits)) {
                    game.enterState(C.States.CREDITS_STATE.value, new FadeOutTransition(), new FadeInTransition());
                }
                else if(pm.testCollisionsEntity(crosshair, button_story)) {
                    game.enterState(C.States.STORY_STATE.value, new FadeOutTransition(), new FadeInTransition());
                }
            }
        }
        if(evm.isHappening(C.Events.CLOSE_WINDOW.name, gc)) {
            if(choosingDificulty) {
                choosingDificulty = false;
            } else {
                gc.exit();
            }
        }
    }   
}
