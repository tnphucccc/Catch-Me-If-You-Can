package graphics;

import core.TileManager;
import entities.Boss;
import entities.Player;

import java.awt.*;

public class Game extends Scene {
    TileManager tileM;
    static Player player;
    Boss boss;

    public Game() {
        tileM = TileManager.getInstance();
        player = new Player();
        boss = new Boss();
    }

    @Override
    public void update() {
        tileM.update();
        player.update();
        boss.update();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        tileM.draw(g2d);
        player.draw(g2d);
        boss.draw(g2d);
    }
    public static Player getPlayer(){
        return player;
    }
}
