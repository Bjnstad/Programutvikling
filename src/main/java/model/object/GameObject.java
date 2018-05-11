package main.java.model.object;

import main.java.model.character.Character;
import main.java.model.object.sprite.Direction;
import main.java.model.object.sprite.SpriteSheet;
import main.java.model.object.sprite.Sprite;

public class GameObject extends Sprite {
    private double posX;
    private double posY;
    private int sizeX;
    private int sizeY;

    public GameObject(int posX, int posY, int sizeX, int sizeY, SpriteSheet spritesheet) {
        super(spritesheet);
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }


    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    /**
     * In this method we add the position to the character in width.
     * If the speed to the character is less then 0, then it walks left.
     * If not, it walks right.
     //* @param speed this is the speed to the character walking right or left.
     */
    public void addPos(double x, double y) {
        if(Math.abs(x) > Math.abs(y)) {
            setDirection(x < 0 ? Direction.LEFT : Direction.RIGHT);
        } else {
            setDirection(y < 0 ? Direction.UP : Direction.DOWN);
        }

        this.posX += x;
        this.posY += y;
    }

    /**
     * Sets position to the character in width in gameboard.
     * If the speed is less then 1, then the character walk left, if not it walks right.
     * @param posX This is the position to the character in width.
     */
    public void setPosX(double posX) {
        double speed = posX - getPosX();
        if (speed == 0) return;

        this.posX = posX;
        if (isDirectional()) setDirection(speed < 0 ? Direction.LEFT : Direction.RIGHT);
    }

    /**
     * Sets position to the character in height in gameboard.
     * If the speed to the character is faster then 1, then it walks up, if not it walks down.
     * @param posY This is the position to the character in height.
     */
    public void setPosY(double posY) {
        double speed = posY - this.posY;
        if (speed == 0) return;

        this.posY = posY;
        if (isDirectional()) setDirection(speed < 0 ? Direction.UP : Direction.DOWN);

    }


    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }


    public boolean willCollide(GameObject object) {
        return willCollide(object, getPosX(), getPosY());
    }


    /**
     * This checks if this character crashes with other character.
     * @param object target to check against.
     * @return the position to enemy in height and width.
     */
    public boolean willCollide(GameObject object, double x, double y) {
        double a = x - object.getSizeX()/2;
        double b = x + object.getSizeX()/2;
        double c = x;
        boolean xval = b > a ? c > a && c < b : c > b && c < a;

        a = y - object.getSizeY()/2;
        b = y + object.getSizeY()/2;
        c = y;
        boolean yval = b > a ? c > a && c < b : c > b && c < a;

        return xval && yval;
    }
}
