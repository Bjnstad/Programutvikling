package HAC.world;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class GameObject {
    private int sizeX;
    private int sizeY;

    private Color color; //TODO: Change to image
    private Image image;

    public GameObject(Image image, int sizeX, int sizeY) {
        this.image = image;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }


    public int getSizeX() {
        return this.sizeX;
    }

    public int getSizeY() {
        return this.sizeY;
    }

    /**
     * TODO: change to image
     * @return color
     */
    public Image getAsset() {
        return this.image;
    }
}
