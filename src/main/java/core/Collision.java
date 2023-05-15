package core;

import entities.Entity;
import variables.Constant;

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

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopY - entity.speed)/Constant.TILE_SIZE;
                checkTile1 = TileManager.map[entityLeftCol][entityTopRow];
                checkTile2 = TileManager.map[entityRightCol][entityTopRow];
                if(TileManager.tiles[checkTile1].collision ==  true
                        || TileManager.tiles[checkTile2].collision ==  true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed)/Constant.TILE_SIZE;
                checkTile1 = TileManager.map[entityLeftCol][entityBottomRow];
                checkTile2 = TileManager.map[entityRightCol][entityBottomRow];
                if(TileManager.tiles[checkTile1].collision ==  true
                        || TileManager.tiles[checkTile2].collision ==  true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed)/Constant.TILE_SIZE;
                checkTile1 = TileManager.map[entityLeftCol][entityTopRow];
                checkTile2 = TileManager.map[entityLeftCol][entityBottomRow];
                if(TileManager.tiles[checkTile1].collision ==  true
                        || TileManager.tiles[checkTile2].collision ==  true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed)/Constant.TILE_SIZE;
                checkTile1 = TileManager.map[entityRightCol][entityTopRow];
                checkTile2 = TileManager.map[entityRightCol][entityBottomRow];
                if(TileManager.tiles[checkTile1].collision ==  true
                        || TileManager.tiles[checkTile2].collision ==  true){
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
