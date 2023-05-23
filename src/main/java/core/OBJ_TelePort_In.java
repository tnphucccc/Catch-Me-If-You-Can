package core;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_TelePort_In extends SuperObject{

    public OBJ_TelePort_In(){
        name = "teleport_in";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/TelePort_In.png"));
        } catch(IOException e){
                e.printStackTrace();
        }
    }
}
