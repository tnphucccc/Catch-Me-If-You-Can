package core;

public class TileManager {
    public Tile[] tiles;
    public int[][] map;
    public static TileManager instance = null;
    public static TileManager getInstance(){
        if (instance == null){
            instance = new TileManager();
        }
        return instance;
    }

}
