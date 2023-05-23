package core;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_TelePort_Out extends SuperObject {
    public OBJ_TelePort_Out(){
        name = "teleport_in";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/TelePort_Out.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
