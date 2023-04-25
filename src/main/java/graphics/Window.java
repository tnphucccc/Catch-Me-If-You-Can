package graphics;

import Variables.Constant;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable {
    public static Window window = null;
    public static boolean isRunning;
    public static int currentState;
    public Scene currentScene;

    public Window(int width, int height, String TITLE) {
        setSize(width, height);
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        isRunning = true;
        setStage(0);
    }

    public static Window getInstance() {
        if (Window.window == null) {
            Window.window = new Window(Constant.WIDTH, Constant.HEIGHT, Constant.TITLE);
        }
        return Window.window;
    }

    public void setStage(int stage) {
        currentState = stage;
        switch (currentState) {
            case 0 -> {
                currentScene = new Menu();
            }
            default -> {
                System.out.println("Invalid stage");
                currentScene = null;
            }
        }
    }

    public int getCurrentState() {
        return currentState;
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

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / Constant.FPS, delta = 0;
        long lastTime = System.nanoTime(), currentTime, timer = 0;
        int count = 0;

        while (isRunning) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                delta--;
                count++;
            }

            if (timer >= 1000000000) {
                // System.out.println("FPS: " + Count);
                count = 0;
                timer = 0;
            }
        }
        this.dispose();
    }
}