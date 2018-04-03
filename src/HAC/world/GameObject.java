package HAC.world;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class GameObject {
    private int sizeX;
    private int sizeY;
    private int posX;
    private int posY;

    private Color color; //TODO: Change to image
    private Image image;

    public GameObject(Image image, int posY, int posX, int sizeX, int sizeY) {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }


    public int getSizeX() {
        return this.sizeX;
    }

    public int getSizeY() {
        return this.sizeY;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    /**
     * TODO: change to image
     * @return color
     */
    public Image getAsset() {
        return this.image;
    }
}
