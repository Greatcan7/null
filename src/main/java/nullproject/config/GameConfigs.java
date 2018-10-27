package nullproject.config;

import nullproject.levels.Level1;

public class GameConfigs {

    //Player position and block size
    public static final int PLAYER_WIDTH = 40;
    public static final int PLAYER_HEIGHT = 60;
    public static final int BLOCK_SIZE = 50;

    public static int lvl = 0;
    public static int levelsWidth = Level1.levels[lvl][0].length() * BLOCK_SIZE;

    public static int level1Length = Level1.levels[lvl].length;

    public static double playerSize = 1.5;


}
