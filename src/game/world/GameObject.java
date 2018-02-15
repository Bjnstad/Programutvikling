package game.world;

import javafx.scene.paint.Color;

public class GameObject {
    private int sizeX;
    private int sizeY;

    private Color color; //TODO: Change to image

    public GameObject(Color color, int sizeX, int sizeY) {
        this.color = color;
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
    public Color getAsset() {
        return this.color;
    }
}
