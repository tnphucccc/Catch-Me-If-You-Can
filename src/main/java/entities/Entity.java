package entities;

import graphics.BufferedImageLoader;
import graphics.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Entity {
    public int x, y, speed, state;
    public int spriteCounter = 0, spriteNum = 1, direction = 2;
    public String name;
    public Rectangle solidArea;
    public boolean collisionOn = false;
    public ArrayList<Integer> InteractionBox = new ArrayList<>();
    public int solidAreaDefaultX, solidAreaDefaultY;
    public BufferedImage[] up = new BufferedImage[4], down = new BufferedImage[4],
            left = new BufferedImage[4], right = new BufferedImage[4];
    public BufferedImageLoader loader = new BufferedImageLoader();


    public abstract void update();

    public abstract void draw(Graphics2D g2);

    public void setEntityInteractionBox(Entity entity) {
        this.InteractionBox.add(0, entity.y + entity.solidArea.y);//TopY
        this.InteractionBox.add(1, entity.x + entity.solidArea.x + entity.solidArea.width);//RightX
        this.InteractionBox.add(2, entity.y + entity.solidArea.y + entity.solidArea.height);//BottomY
        this.InteractionBox.add(3, entity.x + entity.solidArea.x);//Left
    }

    public BufferedImage getEntityImage() {
        BufferedImage img = null;
        switch (direction) {
            case 0 -> img = getBufferedImage(up[0], up[1], up[2], up[3]);
            case 2 -> img = getBufferedImage(down[0], down[1], down[2], down[3]);
            case 3 -> img = getBufferedImage(left[0], left[1], left[2], left[3]);
            case 1 -> img = getBufferedImage(right[0], right[1], right[2], right[3]);
        }
        return img;
    }

    public BufferedImage getBufferedImage(BufferedImage img1, BufferedImage img2,
                                          BufferedImage img3, BufferedImage img4) {
        switch (spriteNum) {
            case 1 -> {
                return img1;
            }
            case 2 -> {
                return img2;
            }
            case 3 -> {
                return img3;
            }
            case 4 -> {
                return img4;
            }
        }
        return null;
    }

    public void getSprite(String path) throws IOException {
        SpriteSheet ss = new SpriteSheet(loader.loadImage(path));
        for (int i = 0; i < 4; i++) {
            up[i] = ss.crop(16 * i, 0, 16, 24);
            down[i] = ss.crop(16 * i, 24, 16, 24);
            left[i] = ss.crop(16 * i, 48, 16, 24);
            right[i] = ss.crop(16 * i, 72, 16, 24);
        }
    }

    //getter && setter for x y but in tile
    public int getX() {
        return ((x + 16) / 48) * 48;
    }

    public int getY() {
        return ((y + 16) / 48) * 48;
    }
}