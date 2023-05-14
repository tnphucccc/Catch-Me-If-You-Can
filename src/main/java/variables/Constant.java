package variables;

public class Constant {
    public static final int MAX_SCREEN_COL = 20;
    public static final int MAX_SCREEN_ROW = 15;
    public static final int SCALE = 3;
    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48
    public static final int WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 816
    //Player position in main screen
    public static final int PLAYER_SCREEN_X = WIDTH / 2 - TILE_SIZE / 2;
    public static final int HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 528
    public static final int PLAYER_SCREEN_Y = HEIGHT / 2 - TILE_SIZE / 2;
    public static final String TITLE = "Catch Me If You Can";
    public static final int FPS = 60;
    //public static final int Tera= 1_000_000_000;
}