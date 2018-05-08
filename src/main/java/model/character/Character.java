package main.java.model.character;

import javafx.scene.canvas.GraphicsContext;
import main.java.model.Camera;

/**
 * This class contains the position and size to the character in the game.
 * @author
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
     * @param speed this is the speed to the character walking right or left.
     */
    public void addPosX(double speed) {
        setDirection(speed < 0 ? Direction.LEFT : Direction.RIGHT);
        this.posX += speed;
        move();
    }

    /**
     * In this method we add the position to the character in height.
     * If the speed the the character is less then 0, then it walks up, if not, it walks down.
     * @param speed this is the speed to the character walking up or down.
     */
    public void addPosY(double speed) {
        setDirection(speed < 0 ? Direction.UP : Direction.DOWN);
        this.posY += speed;
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
    }
}
