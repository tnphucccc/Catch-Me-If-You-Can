package core;

import entities.*;
import variables.Constant;
import java.awt.Rectangle;

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
}
