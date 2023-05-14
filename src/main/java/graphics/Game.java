package graphics;

import core.TileManager;

import java.awt.*;

public class Game extends Scene {
    TileManager tileM;
    public Game() {
        tileM = TileManager.getInstance();
    }

    @Override
    public void update() {
        tileM.update();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        tileM.draw(g2d);
    }
}
