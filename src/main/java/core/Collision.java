package core;

import entities.*;
import variables.Constant;
import java.awt.Rectangle;
import graphics.Game;

public class Collision {
    public Collision(){

    }
    public void checkTile(Entity entity){

        int entityLeftX = entity.x + entity.solidAreaDefaultX;
        int entityRightX = entity.x + entity.solidAreaDefaultX + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidAreaDefaultY;
        int entityBottomY = entity.y + entity.solidAreaDefaultY + entity.solidArea.height;

        int entityLeftCol = entityLeftX/Constant.TILE_SIZE;
        int entityRightCol = entityRightX/Constant.TILE_SIZE;
        int entityTopRow = entityTopY/Constant.TILE_SIZE;
        int entityBottomRow = entityBottomY/Constant.TILE_SIZE;

        int checkTile1, checkTile2;

        switch (entity.direction) {
            case 0 -> {
                entityTopRow = (entityTopY - entity.speed) / Constant.TILE_SIZE;
                checkTile1 = TileManager.map[entityLeftCol][entityTopRow];
                checkTile2 = TileManager.map[entityRightCol][entityTopRow];
                if (TileManager.tiles[checkTile1].collision
                        || TileManager.tiles[checkTile2].collision) {
                    entity.collisionOn = true;
                }
            }
            case 2 -> {
                entityBottomRow = (entityBottomY + entity.speed) / Constant.TILE_SIZE;
                checkTile1 = TileManager.map[entityLeftCol][entityBottomRow];
                checkTile2 = TileManager.map[entityRightCol][entityBottomRow];
                if (TileManager.tiles[checkTile1].collision
                        || TileManager.tiles[checkTile2].collision) {
                    entity.collisionOn = true;
                }
            }
            case 3 -> {
                entityLeftCol = (entityLeftX - entity.speed) / Constant.TILE_SIZE;
                checkTile1 = TileManager.map[entityLeftCol][entityTopRow];
                checkTile2 = TileManager.map[entityLeftCol][entityBottomRow];
                if (TileManager.tiles[checkTile1].collision
                        || TileManager.tiles[checkTile2].collision) {
                    entity.collisionOn = true;
                }
            }
            case 1 -> {
                entityRightCol = (entityRightX + entity.speed) / Constant.TILE_SIZE;
                checkTile1 = TileManager.map[entityRightCol][entityTopRow];
                checkTile2 = TileManager.map[entityRightCol][entityBottomRow];
                if (TileManager.tiles[checkTile1].collision
                        || TileManager.tiles[checkTile2].collision) {
                    entity.collisionOn = true;
                }
            }
        }
    }
    public void checkMob(Entity entity, Boss boss) {
        entity.setEntityInteractionBox(entity);
        Rectangle entitySolidBox = new Rectangle(entity.InteractionBox.get(3),
            entity.InteractionBox.get(0),
            entity.solidArea.width,
            entity.solidArea.height);
        boss.setEntityInteractionBox(boss);
        Rectangle bossSolidBox = new Rectangle(boss.InteractionBox.get(3),
            boss.InteractionBox.get(0),
            boss.solidArea.width,
            boss.solidArea.height);
            boolean intersects = entitySolidBox.intersects(bossSolidBox);
            if (intersects&&boss.col) {
                entity.collisionOn = true;
                entity.state = 0;
                entity.speed = 0;
        }
    }
    public int checkObject(Entity entity, boolean player){
        int interactObject = -1;
        for (int i = 0; i < Game.Object.length; i++) {
            if (Game.Object[i] != null) {

                // Get entity's solid area position
                entity.solidArea.x = entity.getX() + entity.solidArea.x;
                entity.solidArea.y = entity.getY() + entity.solidArea.y;

                // Get object's solid area position
                Game.Object[i].solidArea.x = Game.Object[i].objectX + Game.Object[i].solidArea.x;
                Game.Object[i].solidArea.y = Game.Object[i].objectY + Game.Object[i].solidArea.y;

                switch (entity.direction) {
                    case 0 -> {
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(Game.Object[i].solidArea)){
                            if(Game.Object[i].collision == true)
                                entity.collisionOn = true;
                            if(player == true)
                                interactObject = i;
                        }
                    }
                    case 2 -> {
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(Game.Object[i].solidArea)){
                            if(Game.Object[i].collision == true)
                                entity.collisionOn = true;
                            if(player == true)
                                interactObject = i;
                        }
                    }
                    case 3 -> {
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(Game.Object[i].solidArea)){
                            if(Game.Object[i].collision == true)
                                entity.collisionOn = true;
                            if(player == true)
                                interactObject = i;
                        }
                    }
                    case 1 -> {
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(Game.Object[i].solidArea)){
                            if(Game.Object[i].collision == true)
                                entity.collisionOn = true;
                            if(player == true)
                                interactObject = i;
                        }
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                Game.Object[i].solidArea.x = Game.Object[i].solidAreaDefaultX;
                Game.Object[i].solidArea.y = Game.Object[i].solidAreaDefaultY;
            }
        }
        return interactObject;
    }
}
