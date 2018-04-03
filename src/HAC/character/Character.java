package HAC.character;

import HAC.Camera;
import HAC.sprite.SpriteAnimation;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;


/**
 * Character in the game
 */

public abstract class Character {

    private double posX = 0;
    private double posY = 0;
    public CharacterAvatar animation;
    private int sizeX;
    private int sizeY;

    /**
     * Character in the game
     * @param avatar
     * @param sizeX
     * @param sizeY
     */
    public Character(CharacterAvatar avatar, int sizeX, int sizeY) {
        this.animation = avatar;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     * Sets position of x
     * @param posX
     */
    public void setPosX(double posX) {
        double speed = posX - this.posX;
        if(speed < 1) {
            animation.setWalkLeft();
        } else {
            animation.setWalkRight();
        }
        animation.startAnimation();
        animation.updateAnimation();

        this.posX = posX;
    }

    /**
     * Sets position of y
     * @param posY
     */
    public void setPosY(double posY) {
        double speed = posY - this.posY;
        if(speed > 1) {
            animation.setWalkingUp();
        } else {
            animation.setWalkingDown();
        }
        animation.startAnimation();
        animation.updateAnimation();

        this.posY = posY;
    }


    /**
     * Adds position of x
     * @param speed
     */

    public void addPosX(double speed) {
        if(speed < 0) {
            animation.setWalkLeft();
        } else {
            animation.setWalkRight();
        }
        animation.startAnimation();
        animation.updateAnimation();

        this.posX += speed;
    }

    /**
     * Adds position of y
     * @param speed
     */
    public void addPosY(double speed) {
        if(speed < 0) {
            animation.setWalkingUp();
        } else {
            animation.setWalkingDown();
        }
        animation.startAnimation();
        animation.updateAnimation();

        this.posY += speed;
    }

    /**
     * Gets position of x
     * @return position to x
     */
    public double getPosX() {
        return this.posX;
    }

    /**
     * Gets position of y
     * @return position of y
     */
    public double getPosY() {
        return this.posY;
    }

    /**
     * Gets the size to x
     * @return the size to x
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * Gets the size of y
     * @return the size to y
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     *
     * @param camera
     */
    public void render(Camera camera) {
        GraphicsContext gc = camera.getGraphicsContext();
        gc.drawImage(SwingFXUtils.toFXImage(animation.getAnimation().getSprite(), null), posX*camera.getScale(), posY*camera.getScale(), sizeX * camera.getScale(), sizeY * camera.getScale());
    }
}
