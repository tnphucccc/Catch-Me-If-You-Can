package features;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {
        soundURL[0] = this.getClass().getResource("/Sound/BGM.wav");
        soundURL[1] = this.getClass().getResource("/Sound/just_died.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream audioInputStream = javax.sound.sampled.AudioSystem.getAudioInputStream(soundURL[i]);
            clip = javax.sound.sampled.AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
