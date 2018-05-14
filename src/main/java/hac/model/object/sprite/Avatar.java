package main.java.hac.model.object.sprite;

import main.java.hac.model.object.sprite.animation.Animation;

public class Avatar {

    private Animation animation;
    private String filename;

    public Avatar (String filename, Animation animation) {
        this.filename = filename;
        this.animation = animation;
    }


    public String getFilename() {
        return filename;
    }

    public int getX() {
        return animation.getX();
    }

    public int getY() {
        return animation.getY();
    }

    public Animation getAnimation() {
        return animation;
    }
}
