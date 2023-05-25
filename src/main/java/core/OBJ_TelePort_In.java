package core;

import variables.Constant;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_TelePort_In extends SuperObject{
    public boolean cooldown;
    public OBJ_TelePort_In(int x, int y){
        this.objectX = x * Constant.TILE_SIZE;
        this.objectY = y * Constant.TILE_SIZE;
        this.collision = true;
        cooldown = false;
        name = "teleport_in";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/TelePort_In.png"));
        } catch(IOException e){
                e.printStackTrace();
        }
        collision = true;
    }
}
