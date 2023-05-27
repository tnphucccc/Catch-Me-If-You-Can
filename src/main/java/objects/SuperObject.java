package objects;

import variables.Constant;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public BufferedImage imageCD;
    public String name;
    public boolean collision = false;
    public int objectX, objectY;
    public Rectangle solidArea = new Rectangle(0, 0, 32, 32);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public boolean cd = false;
    public long timeStart = 0L;

    public void draw(Graphics2D graphics2D) {
        if (!cd) {
            graphics2D.drawImage(image, objectX, objectY, Constant.TILE_SIZE, Constant.TILE_SIZE, null);
        } else {
            graphics2D.drawImage(imageCD, objectX, objectY, Constant.TILE_SIZE, Constant.TILE_SIZE, null);
        }
    }

    public void setCD(boolean cd) {
        this.cd = cd;
        updateTime();
    }

    public void releaseCD() {
        if (cd) {
            if ((System.nanoTime() - timeStart) / 1000000000 >= 30) {
                cd = false;
            }
        }
    }

    public void updateTime() {
        timeStart = System.nanoTime();
    }
}
