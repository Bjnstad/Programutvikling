package main.java.model.object.sprite.animation;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import main.java.model.filehandler.SpriteSheet;

public class SingleAnimation implements Animation {

    private int interval = 0;
    private int frames;//
    private int y;//

    public SingleAnimation(int frames, int y) {
        this.frames = frames;
        this.y = y;
    }

    @Override
    public void increaseInterval() {
        interval++;
        if(interval > frames) interval = 0;
    }

    @Override
    public int getX() {
        return interval;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getFrames() {
        return frames;
    }
}
