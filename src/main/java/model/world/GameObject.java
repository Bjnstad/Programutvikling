package main.java.model.world;

import javafx.scene.image.Image;

/**
 * This class defines the gameObject.
 * Contains the size and position to gameObject.
 * @author Axel Bjørnstad - s315322
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
     */
    public GameObject(Image image, int posY, int posX, int sizeX, int sizeY) {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     * Gets the size in width to gameObject.
     * @return the current size in width to the object.
     */
    public int getSizeX() {
        return this.sizeX;
    }

    /**
     * Gets the size in height to gameObject.
     * @return the current size in height to the object.
     */
    public int getSizeY() {
        return this.sizeY;
    }

    /**
     * Gets the position in width to gameObject.
     * @return the current position in width to the object.
     */
    public int getPosX() {
        return this.posX;
    }

    /**
     * Gets the position in height to gameObject.
     * @return the current position in height to the object.
     */
    public int getPosY() {
        return this.posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Gets the asset.
     * @return asset.
     */
    public Image getAsset() {
        return this.image;
    }
}




