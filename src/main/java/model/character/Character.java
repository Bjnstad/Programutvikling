package main.java.model.character;

import main.java.model.Camera;
import javafx.scene.canvas.GraphicsContext;


/**
 * This class contains the position and size to the character in the game.
 * @author ceciliethoresen
 */
public abstract class Character extends Avatar {

    private double posX = 0;
    private double posY = 0;
    private int sizeX;
    private int sizeY;


    /**
     * This method represents the character, and it´s size.
     * @param filename assets file.
     * @param sizeX this is the size to x in width.
     * @param sizeY this is the size to y in height.
     * @author ceciliethoresen
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
     * @author ceciliethoresen
     */
    public void setPosX(double posX) {
        double speed = posX - this.posX;
        if(speed == 0) return;

        if(speed < 0) {
            setDirection(Direction.LEFT);
        } else {
            setDirection(Direction.RIGHT);
        }

        this.posX = posX;
        move();
    }

    /**
     * Sets position to the character in height in gameboard.
     * If the speed to the character is faster then 1, then it walks up, if not it walks down.
     * @param posY This is the position to the character in height.
     * @author ceciliethoresen
     */
    public void setPosY(double posY) {
        double speed = posY - this.posY;
        if(speed == 0) return;

        if(speed < 0) {
            setDirection(Direction.UP);
        } else {
            setDirection(Direction.DOWN);
        }

        this.posY = posY;
        move();
    }


    /**
     * In this method we add the position to the character in width.
     * If the speed to the character is less then 0, then it walks left.
     * If not, it walks right.
     * @param speed this is the speed to the character walking right or left.
     * @author ceciliethoresen
     */
    public void addPosX(double speed) {
        if(speed < 0) {
            setDirection(Direction.LEFT);
        } else {
            setDirection(Direction.RIGHT);
        }

        this.posX += speed;
        move();
    }

    /**
     * In this method we add the position to the character in height.
     * If the speed the the character is less then 0, then it walks up, if not, it walks down.
     * @param speed this is the speed to the character walking up or down.
     * @author ceciliethoresen
     */
    public void addPosY(double speed) {
        if(speed < 0) {
            setDirection(Direction.UP);
        } else {
            setDirection(Direction.DOWN);
        }

        this.posY += speed;
        move();
    }

    /**
     * Gets the position to the character walking right or left.
     * @return Then it returns the position to the character. (width)
     * @author Cecilie Thoresen
     */
    public double getPosX() {
        return this.posX;
    }

    /**
     * Gets the position to the character walking up or down.
     * @return Then it returns the position to the character.(height)
     * @author Cecilie Thoresen
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
     * @author Cecilie Thoresen
     */
    public void render(Camera camera) {
        GraphicsContext gc = camera.getGraphicsContext();
        gc.drawImage(getSprite(), posX*camera.getScale(), posY*camera.getScale(), sizeX * camera.getScale(), sizeY * camera.getScale());
    }
}
