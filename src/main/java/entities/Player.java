package entities;

import core.KeyHandler;
import variables.Constant;
import graphics.Window;


import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyH = Window.getKeyH();

    public Player(){
        this.name = "player";
      
        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.height = 32;
        solidArea.width =32;

        setDefault();
        state = 1;
    }

    public void setDefault(){
        x = Constant.TILE_SIZE*2;
        y = Constant.TILE_SIZE*2;
        direction="down";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    @Override
    public void update() {
        collisionOn = false;

        if ((keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) && state == 1) {
            if (keyH.upPressed) { //Character Movement
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else {
                direction = "right";
            }

            //Animation
            if (!collisionOn) {
                switch (direction) {
                    case "up" -> y -= speed;
                    case "down" -> y += speed;
                    case "left" -> x -= speed;
                    case "right" -> x += speed;
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage img = getEntityImage();
        g2.drawImage(img, getX(), getY(), Constant.TILE_SIZE, Constant.TILE_SIZE, null);
    }
}
