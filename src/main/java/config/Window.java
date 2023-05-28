package config;

import core.KeyHandler;
import core.MouseHandler;
import variables.Constant;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable {
    public static Window window = null;
    public static boolean isRunning;
    public static int currentState;
    static KeyHandler keyH = new KeyHandler();
    static MouseHandler mouseHandler = new MouseHandler();
    public Scene currentScene;

    public Window(int width, int height, String TITLE) {
        setSize(width, height);
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        addKeyListener(keyH);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        isRunning = true;
        setState(0);
    }

    public static Window getInstance() {
        if (Window.window == null) {
            Window.window = new Window(Constant.WIDTH, Constant.HEIGHT, Constant.TITLE);
        }
        return Window.window;
    }

    public static MouseHandler getMouseH() {
        return mouseHandler;
    }

    public static KeyHandler getKeyH() {
        return keyH;
    }

    public void setState(int state) {
        currentState = state;
        switch (currentState) {
            case 0 -> {
                currentScene = new Menu();
                System.out.println("Menu");
            }
            case 1 -> {
                currentScene = new Game();
                System.out.println("Game");
            }
            default -> {
                System.out.println("Invalid state");
                currentScene = null;
            }
        }
    }

    public void update() {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        getGraphics().drawImage(dbImage, 0, 0, this);
        currentScene.update();
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        currentScene.draw(g2d);
    }

    public void close() {
        isRunning = false;
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / Constant.FPS, delta = 0;
        long lastTime = System.nanoTime(), currentTime, timer = 0;

        while (isRunning) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                delta--;
            }

            if (timer >= 1000000000) {
                // System.out.println("FPS: " + Count);
                timer = 0;
            }
        }
        this.dispose();
    }
}