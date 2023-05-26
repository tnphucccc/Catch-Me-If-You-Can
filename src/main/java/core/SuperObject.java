package core;

import graphics.Game;
import variables.Constant;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int objectX, objectY;
    public Rectangle solidArea = new Rectangle(0,0,32,32);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public boolean cooldown = false;
    public long timeStart = 0L;

    public void  draw(Graphics2D graphics2D, Game game){
            graphics2D.drawImage(image, objectX, objectY, Constant.TILE_SIZE, Constant.TILE_SIZE, null);
    }

    public void setPosition(int objectX, int objectY) {
        this.objectX = objectX * Constant.TILE_SIZE;
        this.objectY = objectY * Constant.TILE_SIZE;
    }
    public void setCooldown (boolean cooldown){
        this.cooldown = cooldown;
        updateTime();
    }
    public void releaseCooldown(){
        if (cooldown){
            if ((System.nanoTime() - timeStart)/1000000000 >= 30){
                cooldown = false;
            }
        }
    }
    public void updateTime(){
        timeStart = System.nanoTime();
    }
}
