package game.character;

import javafx.scene.image.Image;

public class Character {
    private double posX = 0;
    private double posY = 0;
    private Image avatar; // TODO: Change to sprite
    private int sizeX;
    private int sizeY;

    public Character(Image avatar, int sizeX, int sizeY) {
        this.avatar = avatar;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }


    public void addPosX(double speed) {
        this.posX += speed;
    }

    public void addPosY(double speed) {
        this.posY += speed;
    }

    public double getPosX() {
        return this.posX;
    }

    public double getPosY() {
        return this.posY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Image getAvatar() {
        return avatar;
    }
}
