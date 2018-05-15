package application.model;

import application.Main;

import javax.sound.sampled.*;
import java.io.File;


public class SoundHandler {
    public static synchronized void playSound(final String filename) {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();


                File audioFile = new File("assets/sounds/"+filename);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                //AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream(new File("/assets/sounds/" + url)));


                clip.open(audioStream);
                FloatControl volume= (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

                volume.setValue(1.0f); // Reduce volume by 10 decibels.

                clip.start();
                clip.drain();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }).start();

    }
}