package entities;

import core.Collision;
import core.KeyHandler;
import graphics.BufferedImageLoader;
import graphics.SpriteSheet;
import graphics.Window;
import variables.Constant;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    KeyHandler keyH = Window.getKeyH();
    BufferedImageLoader loader = new BufferedImageLoader();
    public Collision collisionCheck = new Collision();

    public Player() {
        this.name = "player";
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.height = 32;
        solidArea.width = 32;

        setDefault();
        getPlayerImage();
        state = 1;
    }

    public void setDefault() {
        x = Constant.TILE_SIZE * 2;
        y = Constant.TILE_SIZE * 2;
        speed = 2;
        direction = "down";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void getPlayerImage() {
        try {
            SpriteSheet ss = new SpriteSheet(loader.loadImage("/Player/PlayerBlackWalk.png"));
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
    public void update() {
        collisionOn = false;

        //Check collision
        collisionCheck.checkTile(this);

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

            spriteCounter++;
            if (spriteCounter > 8) {
                if (spriteNum != 4) {
                    spriteNum++;
                } else
                    spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage img = getEntityImage();
        g2.drawImage(img, getX(), getY(), Constant.TILE_SIZE, Constant.TILE_SIZE, null);
    }
}
