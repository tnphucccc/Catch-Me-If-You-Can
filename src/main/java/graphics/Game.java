package graphics;

import core.TileManager;
import entities.Player;

import java.awt.*;

public class Game extends Scene {
    TileManager tileM;
    Player player;
    public Game() {
        tileM = TileManager.getInstance();
        player = new Player();
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
        player.draw(g2d);
    }
}
