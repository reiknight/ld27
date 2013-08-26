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
        ZOMBIE("zombie", "resources/textures/zombie.png"),
        AVATAR("avatar", "resources/textures/zombie_0.png"),
        BUTTON_CREDITS("button_credits", "resources/textures/button_credits.png"),
        BUTTON_PLAY("button_play", "resources/textures/button_play.png"),
        BUTTON_INSTRUCTIONS("button_instructions", "resources/textures/button_instructions.png"),
        BUTTON_EASY("button_easy", "resources/textures/button_easy.png"),
        BUTTON_NORMAL("button_normal", "resources/textures/button_normal.png"),
        BUTTON_HARD("button_hard", "resources/textures/button_hard.png"),
        CROSSHAIR("crosshair", "resources/textures/crosshair.png"),
        WALL("wall", "resources/textures/wall.png"),
        TILE_SET("tile_set", "resources/textures/tile_set.png"),
        KNIGHT_SET("knight_set", "resources/textures/knight_set.png"),
        LOGO("logo", "resources/textures/logo.png"),
        ENEMY("enemy", "resources/textures/enemy.png"),
        CASTLE("castle", "resources/textures/castillo.png"),
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
        CREDITS(Textures.BUTTON_CREDITS.name, "", new Vector2f(150, 450), new Vector2f(45, 25)),
        START_GAME(Textures.BUTTON_PLAY.name, "", new Vector2f(340, 450), new Vector2f(45, 25)),
        INSTRUCTIONS(Textures.BUTTON_INSTRUCTIONS.name, "", new Vector2f(550, 450), new Vector2f(45, 25)),
        EASY(Textures.BUTTON_EASY.name, "", new Vector2f(150, 450), new Vector2f(45, 25)),
        NORMAL(Textures.BUTTON_NORMAL.name, "", new Vector2f(340, 450), new Vector2f(45, 25)),
        HARD(Textures.BUTTON_HARD.name, "", new Vector2f(550, 450), new Vector2f(45, 25));

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