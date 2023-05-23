package entities;

import core.Collision;
import graphics.BufferedImageLoader;
import variables.Constant;
import graphics.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Objects;

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
        x = Constant.TILE_SIZE * 11;
        y = Constant.TILE_SIZE * 7;
        col = true;
        speed = 2;
    }
    public void getBossImage(){
        try {
            for (int i = 0; i < 4; i++) {
                up[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Boss/MobUpRight" + (i + 1) + ".png")));
                down[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Boss/MobDownLeft" + (i + 1) + ".png")));
                left[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Boss/MobDownLeft" + (i + 1) + ".png")));
                right[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Boss/MobUpRight" + (i + 1) + ".png")));
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
