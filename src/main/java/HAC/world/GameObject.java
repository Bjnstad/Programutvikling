package main.java.HAC.world;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * This class defines the gameObject and contains the size and position.
 * @author ceciliethoresen
 */
public class GameObject {
    private int sizeX;
    private int sizeY;
    private int posX;
    private int posY;

    private Image image;

    /**
     * Visual, position and size to gameObject.
     * @param image represents the gameObject.
     * @param posY  position to gameObject in height.
     * @param posX position to gameObject in width.
     * @param sizeX size to gameObject in width.
     * @param sizeY size to gameObject in height.
     * @author ceciliethoresen
     */
    public GameObject(Image image, int posY, int posX, int sizeX, int sizeY) {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     * This method gets the size in width to gameObject.
     * @return the current size in width to the object.
     * @author ceciliethoresen
     */
    public int getSizeX() {
        return this.sizeX;
    }

    /**
     * This method gets the size in height to gameObject.
     * @return the current size in height to the object.
     * @author ceciliethoresen
     */
    public int getSizeY() {
        return this.sizeY;
    }

    /**
     * This method gets the position in width to gameObject.
     * @return the current position in width to the object.
     * @author ceciliethoresen
     */
    public int getPosX() {
        return this.posX;
    }

    /**
     * This method gets the position in height to gameObject.
     * @return the current position in height to the object.
     * @author ceciliethoresen
     */
    public int getPosY() {
        return this.posY;
    }

    /**
     * TODO: change to image
     * Here we get the asset.
     * @return color.
     */
    public Image getAsset() {
        return this.image;
    }
}
