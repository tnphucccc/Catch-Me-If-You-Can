package entities;

import java.awt.*;
import java.util.ArrayList;

public abstract class Entity {
    public int x, y, speed, state;
    public String direction, name;
    public Rectangle solidArea;

    public boolean collisionOn = false;
    public ArrayList<Integer> InteractionBox = new ArrayList<>();
    public int solidAreaDefaultX, solidAreaDefaultY;


    public abstract void update();
    public void setEntityInteractionBox(Entity entity) {
        this.InteractionBox.add(0, entity.y + entity.solidArea.y);//TopY
        this.InteractionBox.add(1, entity.x + entity.solidArea.x + entity.solidArea.width);//RightX
        this.InteractionBox.add(2, entity.y + entity.solidArea.y + entity.solidArea.height);//BottomY
        this.InteractionBox.add(3, entity.x + entity.solidArea.x);//Left
    }

    //getter && setter for x y but in tile
    public int getX() {
        return ((x + 16) / 48) * 48;
    }
    public int getY() {
        return ((y + 16) / 48) * 48;
    }
}
