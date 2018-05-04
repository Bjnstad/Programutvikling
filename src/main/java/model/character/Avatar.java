package main.java.model.character;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import main.java.model.Sprite;

/**
 * This class describes the different states to character in the game.
 * @author Axel Bjørnstad - s315322
 */
public class Avatar {

    public static int EXPECTED_ANIMATIONS = 9;

    private Image[] up;
    private Image[] down;
    private Image[] left;
    private Image[] right;
    private Direction direction;
    private int interval;

    /**
     * This method gives us the size and position to Avatar.
     * @param fileName of the file we can save.
     * @param size The size to the Avatar.
     * @author ceciliethoresen
     */
    public Avatar(String fileName, int size){
        Sprite sprite = new Sprite(fileName, size);
        loadAssets(sprite);

        interval = 0;
    }

    public void move() {
        if(interval < EXPECTED_ANIMATIONS -1) {
            interval++;
        } else {
            interval = 0;
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private void loadAssets(Sprite sprite) {
        up = loadRow(0, sprite);
        left = loadRow(1, sprite);
        down = loadRow(2, sprite);
        right = loadRow(3, sprite);
    }

    private Image[] loadRow(int row, Sprite sprite) {
        try {
            Image[] result = new Image[EXPECTED_ANIMATIONS];
            for (int i = 0; i < EXPECTED_ANIMATIONS; i++) {
                result[i] = SwingFXUtils.toFXImage(sprite.getSprite(i, row), null);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading character image.");
        }
        System.exit(-1);
        return null;
    }

    public Image getSprite() {
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
}
