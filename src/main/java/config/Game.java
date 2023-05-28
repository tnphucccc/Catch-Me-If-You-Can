package config;

import core.AssetSetter;
import core.TileManager;
import entities.Boss;
import entities.Player;
import features.Sound;
import objects.SuperObject;

import java.awt.*;

public class Game extends Scene {
    public static SuperObject[] Object = new SuperObject[20];
    public static SuperObject[] PortList = new SuperObject[10];
    public static SuperObject[] PortInList = new SuperObject[10];
    static Player player;
    static Sound sound = new Sound();
    TileManager tileM;
    Boss boss;
    AssetSetter aSetter = new AssetSetter(this);

    public Game() {
        tileM = TileManager.getInstance();
        player = new Player();
        boss = new Boss();
        setupGame();
    }

    public static Player getPlayer() {
        return player;
    }

    public static void stopMusic() {
        sound.stop();
    }

    public static void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }

    @Override
    public void update() {
        tileM.update();
        player.update();
        boss.update();
        for (SuperObject port : Game.PortInList) {
            if (port != null) {
                port.releaseCD();
            }
        }
        if (player.state == 0) {
            GameOver.getInstance().update();
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        tileM.draw(g2d);

        for (SuperObject superObject : Object) {
            if (superObject != null) {
                superObject.draw(g2d);
            }
        }
        player.draw(g2d);
        boss.draw(g2d);
        if (player.state == 0) {
            GameOver.getInstance().draw(g2d); //Draw Game Over Menu
        }
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void setupGame() {
        aSetter.setObject();
        playMusic(0);
    }
}
