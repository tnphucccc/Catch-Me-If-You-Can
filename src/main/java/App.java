import graphics.Window;

public class App {
    public static void main(String[] args) {
        Window window = Window.getInstance();
        Thread thread = new Thread(window);
        thread.start();
    }
}
