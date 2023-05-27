package core;

import graphics.BufferedImageLoader;
import graphics.SpriteSheet;
import variables.Constant;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    public static TileManager instance = null;
    public static Tile[] tiles;
    public static int[][] map;
    SpriteSheet sheet;
    BufferedImageLoader loader = new BufferedImageLoader();

    public TileManager() {
        tiles = new Tile[10];
        map = new int[Constant.MAX_SCREEN_COL][Constant.MAX_SCREEN_ROW];
        getTileImage();
        loadMap("/Map/map01.txt");
    }

    public static TileManager getInstance() {
        if (instance == null) {
            instance = new TileManager();
        }
        return instance;
    }

    public void getTileImage() {
        try {
            sheet = new SpriteSheet(loader.loadImage("/Tiles/tiles.png"));
            tiles[0] = new Tile();
            tiles[0].image = sheet.crop(0, 0, 16, 16);
            tiles[0].collision = true;

            tiles[1] = new Tile();
            tiles[1].image = sheet.crop(16, 0, 16, 16);
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = sheet.crop(32, 0, 16, 16);

            tiles[3] = new Tile();
            tiles[3].image = sheet.crop(48, 0, 16, 16);

            tiles[4] = new Tile();
            tiles[4].image = loader.loadImage("/Tiles/Sand.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String path) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < Constant.MAX_SCREEN_COL && row < Constant.MAX_SCREEN_ROW) {
                String line = br.readLine();
                while (col < Constant.MAX_SCREEN_COL) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    map[col][row] = num;
                    col++;
                }
                if (col == Constant.MAX_SCREEN_COL) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < Constant.MAX_SCREEN_COL && row < Constant.MAX_SCREEN_ROW) {
            int tileNum = map[col][row];
            g2.drawImage(tiles[tileNum].image, x, y, Constant.TILE_SIZE, Constant.TILE_SIZE, null);
            col++;
            x += Constant.TILE_SIZE;

            if (col == Constant.MAX_SCREEN_COL) {
                col = 0;
                row++;
                x = 0;
                y += Constant.TILE_SIZE;
            }
        }
    }
}
