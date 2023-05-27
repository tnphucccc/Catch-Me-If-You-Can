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
    public boolean collision,onPath = true;
    BufferedImageLoader loader = new BufferedImageLoader();
    public Collision collisionCheck = new Collision();
    public PathFinding pFind =new PathFinding();

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
        x = Constant.TILE_SIZE * 9;
        y = Constant.TILE_SIZE * 7;
        collision = true;
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
    public void checkCollision(){
        collisionOn=false;
        collisionCheck.checkTile(this);
        collisionCheck.checkMob(Game.getPlayer(), this);
    }
    @Override
    public void update(){
        checkCollision();
        searchPath(3,3);
        if(!collisionOn){
            switch (direction) {
                case 0 -> y -= speed;
                case 2 -> y += speed;
                case 3 -> x -= speed;
                case 1 -> x += speed;
            }
        }

    }
    public void searchPath(int goalCol, int goalRow){
        int startCol = (int) (this.x + solidArea.x)/Constant.TILE_SIZE ;
        int startRow = (int) (this.y+ solidArea.y)/Constant.TILE_SIZE;
        pFind.setNodes(startCol, startRow, goalCol,goalRow);

        if(pFind.search()){
            int nextX = pFind.pathList.get(0).col*Constant.TILE_SIZE;
            int nextY = pFind.pathList.get(0).row*Constant.TILE_SIZE;

            setEntityInteractionBox(this);

            if(this.InteractionBox.get(0) > nextY &&
                    this.InteractionBox.get(3) >= nextX &&
                    this.InteractionBox.get(1) < nextX + Constant.TILE_SIZE){
                direction = 0;
                y -= speed;
            }
            else if(this.InteractionBox.get(0) < nextY &&
                    this.InteractionBox.get(3) >= nextX &&
                    this.InteractionBox.get(1) < nextX + Constant.TILE_SIZE){
                direction = 2;
                y += speed;
            }
            else if(this.InteractionBox.get(0) >= nextY &&
                    this.InteractionBox.get(2) <= nextY + Constant.TILE_SIZE){
                //Left or Right
                if(this.InteractionBox.get(3)>nextX){
                    direction = 3;
                    x-=speed;
                }
                if(this.InteractionBox.get(3) < nextX){
                    direction = 1;
                    x+=speed;
                }
            } else if (this.InteractionBox.get(0) > nextY && this.InteractionBox.get(3) > nextX){
                //Up or Left
                direction = 0;
                checkCollision();
                if(collisionOn){
                    direction = 3;
                    x -= speed;
                }

            } else if (this.InteractionBox.get(0) > nextY && this.InteractionBox.get(3) < nextX){
                //Up or Right
                direction = 0;
                y+=speed;
                checkCollision();
                if(collisionOn){
                    direction =1;
                    x+=speed;

                }
            } else if (this.InteractionBox.get(0) < nextY && this.InteractionBox.get(3) > nextX){
                //Down or Left
                direction = 2;
                y+=speed;
                checkCollision();
                if(collisionOn){
                    direction = 3;
                    x-=speed;
                }

            } else if (this.InteractionBox.get(0) < nextY && this.InteractionBox.get(3) < nextX){
                //Down or Right
                direction = 2;
                y+=speed;
                checkCollision();
                if(collisionOn){
                    direction =1;
                }
            }
            int nextCol = pFind.pathList.get(0).col;
            int nextRow = pFind.pathList.get(0).row;
            if (nextCol == goalCol && nextRow == goalRow){
                onPath = false;
            }
        }
    }
    @Override
    public void draw(Graphics2D g2){
        BufferedImage img = getEntityImage();
        g2.drawImage(img,this.x ,this.y, Constant.TILE_SIZE, Constant.TILE_SIZE, null);

        if(onPath){
            g2.setColor(new Color(255,0,0,70));

            for(int i = 0;i < pFind.pathList.size();i++){
                int worldX = pFind.pathList.get(i).col + Constant.TILE_SIZE;
                int worldY = pFind.pathList.get(i).row + Constant.TILE_SIZE;

                g2.fillRect(worldX,worldY,Constant.TILE_SIZE,Constant.TILE_SIZE);
            }

        }
    }

}
