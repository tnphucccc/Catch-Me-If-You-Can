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
    public void  draw(Graphics2D graphics2D, Game game){
            graphics2D.drawImage(image, objectX, objectY, Constant.TILE_SIZE, Constant.TILE_SIZE, null);
    }

    public void setPosition(int objectX, int objectY) {
        this.objectX = objectX * Constant.TILE_SIZE;
        this.objectY = objectY * Constant.TILE_SIZE;
    }
}
