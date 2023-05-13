package core;

import graphics.BufferedImageLoader;
import graphics.SpriteSheet;
import graphics.Window;
import variables.Constant;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    Window window;
    Tile[] tiles;
    SpriteSheet sheet;
    int[][] map;
    BufferedImageLoader loader = new BufferedImageLoader();
    public TileManager (Window window){
        this.window = window;
        tiles = new Tile[10];
        map = new int[Constant.MAX_SCREEN_COL][Constant.MAX_SCREEN_ROW];
        getTileImage();
        loadMap("/Map/map01.txt");
    }

    public void getTileImage(){
        try {
            sheet = new SpriteSheet(loader.loadImage("/Tiles/tiles.png"));
            tiles[0] = new Tile();
            //tiles[0].image = loader.loadImage("/Tiles/Block.png");
            tiles[0].image = sheet.crop(0,0,16,16);

            tiles[1] = new Tile();
            //tiles[1].image = loader.loadImage("/Tiles/Brick.png");
            tiles[1].image = sheet.crop(16,0,16,16);

            tiles[2] = new Tile();
            //tiles[2].image = loader.loadImage("/Tiles/Ground.png");
            tiles[2].image = sheet.crop(32,0,16,16);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap (String path){

        try{
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < Constant.MAX_SCREEN_COL && row < Constant.MAX_SCREEN_ROW){
                String line = br.readLine();
                while (col < Constant.MAX_SCREEN_COL){
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    map[col][row] = num;
                    col++;
                }
                if (col == Constant.MAX_SCREEN_COL){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static TileManager instance = null;
    public static TileManager getInstance(Window window){
        if (instance == null){
            instance = new TileManager(window);
        }
        return instance;
    }

    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < Constant.MAX_SCREEN_COL && row < Constant.MAX_SCREEN_ROW){
            int tileNum = map[col][row];
            g2.drawImage(tiles[tileNum].image, x, y, Constant.TILE_SIZE, Constant.TILE_SIZE, null);
            col++;
            x += Constant.TILE_SIZE;

            if (col == Constant.MAX_SCREEN_COL){
                col = 0;
                row++;
                x = 0;
                y += Constant.TILE_SIZE;
            }
        }

    }
}
