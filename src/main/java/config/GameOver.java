package config;

import core.MouseHandler;
import core.TileManager;
import variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameOver {
    public static GameOver instance = null;
    BufferedImage gameOver, playAgainPressed, exitPressed;
    BufferedImage currentGameOver;
    Rectangle playAgainRect, exitRect;
    MouseHandler mouseH = Window.getMouseH();

    public GameOver() {
        try {
            gameOver = ImageIO.read(new File("src/main/resources/Menu/GameOver.png"));
            playAgainPressed = ImageIO.read(new File("src/main/resources/Menu/GameOverPlayAgainPressed.png"));
            exitPressed = ImageIO.read(new File("src/main/resources/Menu/GameOverExitPressed.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        currentGameOver = gameOver;
        playAgainRect = new Rectangle(300, 400, 330, 50);
        exitRect = new Rectangle(400, 450, 170, 50);
        Game.stopMusic();
        Game.playSE(1);
    }

    public static GameOver getInstance() {
        if (GameOver.instance == null) {
            GameOver.instance = new GameOver();
        }
        return GameOver.instance;
    }

    public void update() {
        if (mouseH.checkInteractWithRect(mouseH, playAgainRect)) {
            currentGameOver = playAgainPressed;
            if (mouseH.isPressed) {
                Window.getInstance().setState(1);
                TileManager.getInstance();
                instance = null;
            }
        } else if (mouseH.checkInteractWithRect(mouseH, exitRect)) {
            currentGameOver = exitPressed;
            if (mouseH.isPressed) {
                Window.getInstance().close();
            }
        } else {
            currentGameOver = gameOver;
        }
    }

    public void draw(Graphics g2) {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, Constant.WIDTH, Constant.HEIGHT);
        g2.drawImage(currentGameOver, 0, 0, Constant.WIDTH, Constant.HEIGHT, null);
    }
}
