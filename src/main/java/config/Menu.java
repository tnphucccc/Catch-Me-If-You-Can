package config;

import core.MouseHandler;
import variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Menu extends Scene {
    public Rectangle playButton;
    public MouseHandler mouseH = Window.getMouseH();
    private BufferedImage Menu;

    public Menu() {
        try {
            Menu = ImageIO.read(new File("src/main/resources/Menu/MenuF.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        playButton = new Rectangle(647, 320, 80, 106);
        //MenuCurrentImage = Menu;
    }

    @Override
    public void update() {
        if (mouseH.checkInteractWithRect(mouseH, playButton)) {
            //MenuCurrentImage = MenuPlayPressed;
            if (mouseH.isPressed) {
                Window.getInstance().setState(1);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Menu, 0, 0, Constant.WIDTH, Constant.HEIGHT, null);
    }
}