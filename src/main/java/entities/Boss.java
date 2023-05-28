package entities;

import config.Game;
import core.Collision;
import features.PathFinding;
import variables.Constant;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Boss extends Entity {
    public boolean collision;
    public Collision collisionCheck = new Collision();
    public PathFinding pFind = new PathFinding();

    public Boss() {
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

    public void setDefault() {
        x = Constant.TILE_SIZE * 8;
        y = Constant.TILE_SIZE * 7;
        collision = true;
        speed = 2;
    }

    public void getBossImage() {
        try {
            getSprite("/Sprite/White.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkCollision() {
        collisionOn = false;
        collisionCheck.checkTile(this);
        collisionCheck.checkMob(Game.getPlayer(), this);
    }

    @Override
    public void update() {
        int startCol = (Game.getPlayer().x + Game.getPlayer().solidArea.x) / Constant.TILE_SIZE;
        int startRow = (Game.getPlayer().y + Game.getPlayer().solidArea.y) / Constant.TILE_SIZE;
        checkCollision();
        searchPath(startCol, startRow);

        spriteCounter++;
        if (spriteCounter > 8) {
            if (spriteNum != 4) {
                spriteNum++;
            } else
                spriteNum = 1;
            spriteCounter = 0;
        }

    }

    public void searchPath(int goalCol, int goalRow) {
        int startCol = (this.x + solidArea.x) / Constant.TILE_SIZE;
        int startRow = (this.y + solidArea.y) / Constant.TILE_SIZE;
        pFind.setNodes(startCol, startRow, goalCol, goalRow);

        if (pFind.search()) {
            int nextX = pFind.pathList.get(0).col * Constant.TILE_SIZE;
            int nextY = pFind.pathList.get(0).row * Constant.TILE_SIZE;

            setEntityInteractionBox(this);

            if (this.InteractionBox.get(0) > nextY &&
                    this.InteractionBox.get(3) >= nextX &&
                    this.InteractionBox.get(1) < nextX + Constant.TILE_SIZE) {
                direction = 0;
                y -= speed;
            } else if (this.InteractionBox.get(0) < nextY &&
                    this.InteractionBox.get(3) >= nextX &&
                    this.InteractionBox.get(1) < nextX + Constant.TILE_SIZE) {
                direction = 2;
                y += speed;
            } else if (this.InteractionBox.get(0) >= nextY &&
                    this.InteractionBox.get(2) <= nextY + Constant.TILE_SIZE) {
                //Left or Right
                if (this.InteractionBox.get(3) > nextX) {
                    direction = 3;
                    x -= speed;
                }
                if (this.InteractionBox.get(3) < nextX) {
                    direction = 1;
                    x += speed;
                }
            } else if (this.InteractionBox.get(0) > nextY && this.InteractionBox.get(3) > nextX) {
                //Up or Left
                direction = 0;
                y -= speed;
                checkCollision();
                if (collisionOn) {
                    direction = 3;
                    x -= speed;
                }

            } else if (this.InteractionBox.get(0) > nextY && this.InteractionBox.get(3) < nextX) {
                //Up or Right
                direction = 0;
                y -= speed;
                checkCollision();
                if (collisionOn) {
                    direction = 1;
                    x += speed;
                }
            } else if (this.InteractionBox.get(0) < nextY && this.InteractionBox.get(3) > nextX) {
                //Down or Left
                direction = 2;
                y += speed;
                checkCollision();
                if (collisionOn) {
                    direction = 3;
                    x -= speed;
                }

            } else if (this.InteractionBox.get(0) < nextY && this.InteractionBox.get(3) < nextX) {
                //Down or Right
                direction = 2;
                y += speed;
                checkCollision();
                if (collisionOn) {
                    direction = 1;
                    x += speed;
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage img = getEntityImage();
        g2.drawImage(img, this.x, this.y, Constant.TILE_SIZE, Constant.TILE_SIZE, null);
    }
}