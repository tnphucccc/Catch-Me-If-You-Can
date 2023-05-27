package objects;

import variables.Constant;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_TelePort_Out extends SuperObject {
    public OBJ_TelePort_Out(int x, int y){
        this.objectX = x * Constant.TILE_SIZE;
        this.objectY = y * Constant.TILE_SIZE;
        name = "teleport_in";
        collision = false;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/TelePort_Out.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
