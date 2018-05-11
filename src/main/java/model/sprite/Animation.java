package main.java.model.sprite;

import javafx.scene.image.Image;

public abstract class Animation {
    protected int interval = 0;
    protected int frames;

    public Animation(int frames) {
        this.frames = frames;
    }

    public void increaseInterval() {
        interval ++;
        if (interval > frames) interval = 0;
    }

    public abstract Image getImage();
}
