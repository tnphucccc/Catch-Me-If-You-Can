package entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Entity {
    public int x, y, speed, state;
    public int spriteCounter = 0, spriteNum = 1;
    public String direction, name;
    public Rectangle solidArea;
    public boolean collisionOn = false;
    public ArrayList<Integer> InteractionBox = new ArrayList<>();
    public int solidAreaDefaultX, solidAreaDefaultY;
    public BufferedImage[] up = new BufferedImage[4], down = new BufferedImage[4],
            left = new BufferedImage[4], right = new BufferedImage[4];


    public abstract void update();
    public abstract void draw(Graphics2D g2);
    public void setEntityInteractionBox(Entity entity) {
        this.InteractionBox.add(0, entity.y + entity.solidArea.y);//TopY
        this.InteractionBox.add(1, entity.x + entity.solidArea.x + entity.solidArea.width);//RightX
        this.InteractionBox.add(2, entity.y + entity.solidArea.y + entity.solidArea.height);//BottomY
        this.InteractionBox.add(3, entity.x + entity.solidArea.x);//Left
    }
}
