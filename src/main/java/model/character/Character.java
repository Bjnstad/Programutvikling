package main.java.model.character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.java.model.Camera;

import java.sql.BatchUpdateException;
import java.util.ArrayList;

/**
 * This class contains the position and size to the character in the game.
 * @author Axel Bjørnstad - s315322
 */
public abstract class Character extends Avatar {

    protected float health = 100;
    private double posX = 0;
    private double posY = 0;
    private int sizeX;
    private int sizeY;

    /**
     * This method represents the character, and it´s size.
     * @param filename assets file.
     * @param sizeX this is the size to x in width.
     * @param sizeY this is the size to y in height.
     */
    public Character(String filename, int sizeX, int sizeY) {
        super(filename, 64);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     * Sets position to the character in width in gameboard.
     * If the speed is less then 1, then the character walk left, if not it walks right.
     * @param posX This is the position to the character in width.
     */
    public void setPosX(double posX) {
        double speed = posX - this.posX;
        if(speed == 0) return;
        setDirection(speed < 0 ? Direction.LEFT : Direction.RIGHT);

        this.posX = posX;
        move();
    }

    /**
     * Sets position to the character in height in gameboard.
     * If the speed to the character is faster then 1, then it walks up, if not it walks down.
     * @param posY This is the position to the character in height.
     */
    public void setPosY(double posY) {
        double speed = posY - this.posY;
        if(speed == 0) return;

        setDirection(speed < 0 ? Direction.UP : Direction.DOWN);
        this.posY = posY;
        move();
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
        move();
    }

    /**
     * Gets the position to the character walking right or left.
     * @return Then it returns the position to the character. (width)
     */
    public double getPosX() {
        return this.posX;
    }

    /**
     * Gets the position to the character walking up or down.
     * @return Then it returns the position to the character.(height)
     */
    public double getPosY() {
        return this.posY;
    }

    /**
     * Gets the size to the character.(width)
     * @return Then the method returns(gives) us the size to the character.
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * Gets the size to the character.(height)
     * @return Then the method returns(gives) us the size to the character.
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     * This method draws the animations on the game board, that we gets from sprite.
     * @param camera We get graphicscontext from camera, and that makes it possible for us to draw in canvas.
     */
    public void render(Camera camera) {
        GraphicsContext gc = camera.getGraphicsContext();
        gc.drawImage(getSprite(), camera.scale(posX),  camera.scale(posY),  camera.getScale() * sizeX, camera.getScale() * sizeY) ;
        renderOptional(camera);
    }


    /**
     * This checks if this character crashes with other character.
     * @param character target to check against.
     * @return the position to enemy in height and width.
     */
    public boolean willCollide(Character character) {
        double a = character.getPosX() - character.getSizeX()/2;
        double b = character.getPosX() + character.getSizeX()/2;
        double c = getPosX();
        boolean xval = b > a ? c > a && c < b : c > b && c < a;

        a = character.getPosY() - character.getSizeY()/2;
        b = character.getPosY() + character.getSizeY()/2;
        c = getPosY();
        boolean yval = b > a ? c > a && c < b : c > b && c < a;

        return xval && yval;

    }

    /**
     * This checks if this character crashes with other character.
     * @param bullet target to check against.
     * @return the position to enemy in height and width.
     */
    public boolean willCollide(Bullet bullet) {
        double a = bullet.getX() - 10/2;
        double b = bullet.getX() + 10/2;
        double c = getPosX();
        boolean xval = b > a ? c > a && c < b : c > b && c < a;

        a = bullet.getY() - 5/2;
        b = bullet.getY() + 5/2;
        c = getPosY();
        boolean yval = b > a ? c > a && c < b : c > b && c < a;

        return xval && yval;

    }

    public abstract void renderOptional(Camera camera);
}
