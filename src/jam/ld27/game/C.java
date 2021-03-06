package jam.ld27.game;

import org.newdawn.slick.geom.Vector2f;

public class C {
    public static final boolean DEBUG_MODE = false;
    public static final boolean GOD_MODE = false;
    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_WIDTH = 800;

    public static enum Events {
        CLOSE_WINDOW("close_window"),
        SOUND_OFF("sound_off"),
        MOVE_LEFT("move_left"),
        MOVE_RIGHT("move_right"),
        MOVE_UP("move_up"),
        MOVE_DOWN("move_down"),
        BACK("back"),
        CLICK_BUTTON("click_button"),
        CROSSHAIR_MOVED("crosshair_moved"),
        NEXT_ZOMBIE("next_zombie"),
        PREV_ZOMBIE("prev_zombie"),
        ACTION("action");

        public String name;

        private Events(String name) {
            this.name = name;
        }
    }

    public static enum Textures {
        START_BACKGROUND("start_background", "resources/textures/start_background.png"),
        STORY_BACKGROUND("story_background", "resources/textures/story_background.png"),
        CREDITS_BACKGROUND("credits_background", "resources/textures/credits_background.png"),
        INSTRUCTIONS_BACKGROUND("instructions_background", "resources/textures/instructions_background.png"),
        GAME_OVER_BACKGROUND("game_over_background", "resources/textures/game_over_background.png"),
        ZOMBIE("zombie", "resources/textures/zombie.png"),
        AVATAR("avatar", "resources/textures/zombie_0.png"),
        BUTTON_CREDITS("button_credits", "resources/textures/creditsboton.png"),
        BUTTON_PLAY("button_play", "resources/textures/playboton.png"),
        BUTTON_INSTRUCTIONS("button_instructions", "resources/textures/instructionsboton.png"),
        BUTTON_EASY("button_easy", "resources/textures/easyboton.png"),
        BUTTON_STORY("button_story", "resources/textures/storyboton.png"),
        BUTTON_NORMAL("button_normal", "resources/textures/normalboton.png"),
        BUTTON_HARD("button_hard", "resources/textures/hardboton.png"),
        CROSSHAIR("crosshair", "resources/textures/puntero.png"),
        WALL("wall", "resources/textures/wall.png"),
        TILE_SET("tile_set", "resources/textures/tile_set.png"),
        KNIGHT_SET("knight_set", "resources/textures/knight_set.png"),
        PRINCESS_SET("princess_set", "resources/textures/princess_set.png"),
        CONCRETE_WALL_SET("concrete_wall_set", "resources/textures/concrete_wall_set.png"),
        FRAGILE_WALL_SET("fragile_wall_set", "resources/textures/fragile_wall_set.png"),
        BLOWING_WALL_SET("blowing_wall_set", "resources/textures/blowing_wall_set.png"),
        DRAGON_SET("dragon_set", "resources/textures/dragon_set.png"),
        LOGO("logo", "resources/textures/logo.png"),
        ENEMY("enemy", "resources/textures/birds.png"),
        CASTLE("castle", "resources/textures/castillo.png"),
        STORY("story", "resources/textures/storyboton.png"),
        HEART("heart", "resources/textures/hearts.png");

        public String name;
        public String path;

        private Textures(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }

    public static enum Entities {
        PLAYER("player"),
        KNIGHT("knight"),
        ZOMBIE("zombie"),
        CROSSHAIR("crosshair"),
        ENEMY("enemy"),
        HEART("heart"),
        DRAGON("dragon"),
        WALL("wall");

        public String name;

        private Entities(String name) {
            this.name = name;
        }
    }

    public static enum Groups {
        PLAYER("player"),
        KNIGHT("knight"),
        BULLETS("bullets"),
        ZOMBIES("zombies"),
        BUTTONS("buttons"),
        ENEMIES("enemies"),
        HEARTS("hearts"),
        DRAGONS("dragons"),
        WALLS("walls");

        public String name;

        private Groups(String name) {
            this.name = name;
        }
    }

    public static enum Sounds {     
        MUERTE("muerte", "resources/sounds/muerte.wav"),
        HEART("heart", "resources/sounds/heart.wav"),
        MUSIC("music", "resources/music/music.wav");

        public String name;
        public String path;

        private Sounds(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }

    public static enum States {
        START_STATE("start", 0),
        MAIN_STATE("main", 1),
        PUBLISHER_STATE("publisher", 2),
        INSTRUCTIONS_STATE("instructions", 3),
        GAME_OVER_STATE("game_over", 4),
        STORY_STATE("story", 5),
        CREDITS_STATE("credits", 9);

        public String name;
        public int value;

        private States(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }

     public static enum Logic {
        ZOMBIE_SPEED((float)0.15),
        ENEMY_SPEED((float)0.20),
        SELECT_OPTION_DELAY(500),
        PUBLISHER_TIME(2000),
        NEXT_LEVEL_TIME(1500),
        ENEMY_THINK_TIME(1000),
        TILE_SIZE(32);

        public Object data;

        private Logic(Object data) {
            this.data = data;
        }
    }

     public static enum Buttons {
        START_GAME(Textures.BUTTON_PLAY.name, "", new Vector2f(500, 100), new Vector2f(45, 25)),
        STORY(Textures.STORY.name, "", new Vector2f(500, 200), new Vector2f(45, 25)),
        INSTRUCTIONS(Textures.BUTTON_INSTRUCTIONS.name, "", new Vector2f(500, 300), new Vector2f(45, 25)),
        CREDITS(Textures.BUTTON_CREDITS.name, "", new Vector2f(500, 400), new Vector2f(45, 25)),
        EASY(Textures.BUTTON_EASY.name, "", new Vector2f(500, 100), new Vector2f(45, 25)),
        NORMAL(Textures.BUTTON_NORMAL.name, "", new Vector2f(500, 200), new Vector2f(45, 25)),
        HARD(Textures.BUTTON_HARD.name, "", new Vector2f(500, 300), new Vector2f(45, 25));

        public String textureName;
        public String label;
        public Vector2f position;
        public Vector2f labelPosition;

        Buttons(String textureName, String label, Vector2f position, Vector2f labelPosition) {
            this.textureName = textureName;
            this.label = label;
            this.position = position;
            this.labelPosition = labelPosition;
        }
    }
}