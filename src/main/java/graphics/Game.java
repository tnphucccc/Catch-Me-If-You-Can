package graphics;

import core.AssetSetter;
import objects.SuperObject;
import core.TileManager;
import entities.*;

import java.awt.*;

public class Game extends Scene {
    TileManager tileM;
    static Player player;
    public static SuperObject[] Object = new SuperObject[20];
    public static SuperObject[] PortList = new SuperObject[10];
    public static SuperObject[] PortInList = new SuperObject[10];
    Boss boss;
    AssetSetter aSetter = new AssetSetter(this);

    public Game() {
        tileM = TileManager.getInstance();
        player = new Player();
        boss = new Boss();
        setupGame();
    }

    @Override
    public void update() {
        tileM.update();
        player.update();
        boss.update();
        for (SuperObject port : Game.PortInList){
            if (port != null){
                port.releaseCD();
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        tileM.draw(g2d);

        for (SuperObject superObject : Object) {
            if (superObject != null) {
                superObject.draw(g2d, this);
            }
        }
        player.draw(g2d);
        boss.draw(g2d);
    }
    public void setupGame(){
        aSetter.setObject();
    }
    public static Player getPlayer(){
        return player;
    }
}
