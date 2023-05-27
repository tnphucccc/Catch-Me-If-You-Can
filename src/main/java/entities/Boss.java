package entities;

import core.Collision;
import graphics.BufferedImageLoader;
import graphics.Game;
import graphics.SpriteSheet;
import variables.Constant;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Boss extends Entity{
    public boolean col;
    BufferedImageLoader loader = new BufferedImageLoader();
    public Collision collisionCheck = new Collision();

    public Boss(){
        this.name = "boss";
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.height = 32;
        solidArea.width = 32;

        setDefault();
        getBossImage();
    }
    public void setDefault(){
        x = Constant.TILE_SIZE * 9;
        y = Constant.TILE_SIZE * 7;
        col = true;
        speed = 2;
    }
    public void getBossImage(){
        try {
            SpriteSheet ss = new SpriteSheet(loader.loadImage("/Sprite/PlayerWhiteWalk.png"));
            for (int i = 0; i < 4; i++) {
                up[i] = ss.crop(16 * i, 0, 16, 24);
                down[i] = ss.crop(16 * i, 24, 16, 24);
                left[i] = ss.crop(16 * i, 48, 16, 24);
                right[i] = ss.crop(16 * i, 72, 16, 24);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(){
        collisionOn=false;
        collisionCheck.checkMob(Game.getPlayer(), this);
    }
    @Override
    public void draw(Graphics2D g2){
        BufferedImage img = getEntityImage();
        g2.drawImage(img,this.x ,this.y, Constant.TILE_SIZE, Constant.TILE_SIZE, null);
    }
}
