package main.java.model.object.sprite;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import main.java.model.filehandler.SpriteSheet;

public class MultiAnimation extends Animation {

    private Image[] up;
    private Image[] down;
    private Image[] left;
    private Image[] right;
    private Direction direction;


    public MultiAnimation(SpriteSheet spriteSheet) {
        super(spriteSheet.getFrames());
        up = loadRow(0, spriteSheet);
        left = loadRow(1, spriteSheet);
        down = loadRow(2, spriteSheet);
        right = loadRow(3, spriteSheet);

        direction = Direction.DOWN;
    }

    private Image[] loadRow(int row, SpriteSheet spriteSheet) {
        try {
            Image[] result = new Image[frames];
            for (int i = 0; i < frames; i++) {
                result[i] = SwingFXUtils.toFXImage(spriteSheet.getSprite(i, row), null);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(-1);
        return null;
    }


    @Override
    public Image getImage() {
        switch (direction) {
            case UP:
                return up[interval];
            case DOWN:
                return down[interval];
            case LEFT:
                return left[interval];
            case RIGHT:
                return right[interval];
        }
        return null;
    }

    public void setDirection(Direction direction) {
        increaseInterval();
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }
}
