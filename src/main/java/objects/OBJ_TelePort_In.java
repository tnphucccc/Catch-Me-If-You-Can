package objects;

import variables.Constant;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_TelePort_In extends SuperObject {

    public OBJ_TelePort_In(int x, int y) {
        this.objectX = x * Constant.TILE_SIZE;
        this.objectY = y * Constant.TILE_SIZE;
        this.collision = true;
        name = "teleport_in";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/Portal_In.png")));
            imageCD = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/Portal_CD.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
