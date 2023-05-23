package graphics;

import core.AssetSetter;
import core.SuperObject;
import core.TileManager;
import entities.Player;

import java.awt.*;

public class Game extends Scene {
    TileManager tileM;
    Player player;
    public SuperObject obj[] = new SuperObject[10];
    AssetSetter aSetter = new AssetSetter(this);

    public Game() {
        tileM = TileManager.getInstance();
        player = new Player();
        setupGame();
    }

    @Override
    public void update() {
        tileM.update();
        player.update();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        tileM.draw(g2d);

        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2d, this);
            }

        player.draw(g2d);
        }
    }
    public void setupGame(){
        aSetter.setObject();
    }
}
