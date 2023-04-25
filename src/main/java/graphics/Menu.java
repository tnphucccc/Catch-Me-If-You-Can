package graphics;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

public class Menu extends Scene {
    private BufferedImage Menu, MenuPlayPressed, MenuExitPressed, MenuCurrentImage;
    public Menu() {
        try {
            Menu = ImageIO.read(new File("src/main/resources/Menu/Menu.png"));
            MenuPlayPressed = ImageIO.read(new File("src/main/resources/Menu/MenuPlayPressed.png"));
            MenuExitPressed = ImageIO.read(new File("src/main/resources/Menu/MenuExitPressed.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        MenuCurrentImage = Menu;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(MenuCurrentImage, 0, 0, null);
    }
}