package main.java.model.object.sprite;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class SingleAnimation extends Animation {
    private Image[] images;

    public SingleAnimation(SpriteSheet spriteSheet) {
        super(spriteSheet.getFrames());
        this.images = new Image[frames];

        for (int i = 0; i < frames; i++) {
            images[i] = SwingFXUtils.toFXImage(spriteSheet.getSprite(0, i), null);
        }
    }

    @Override
    public Image getImage() {
        return images[interval];
    }

}
