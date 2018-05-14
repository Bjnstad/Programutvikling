package main.java.model.object;

import javafx.scene.image.Image;
import main.java.model.Camera;
import main.java.model.object.sprite.Avatar;
import main.java.model.object.sprite.Direction;
import main.java.model.object.sprite.ImageHandler;
import main.java.model.object.sprite.Sprite;
import main.java.model.render.Actions;
import main.java.model.world.World;

public abstract class GameObject {

    protected Avatar avatar;
    private double posX;
    private double posY;
    private int sizeX;
    private int sizeY;
    private boolean collideable = true;

    public GameObject(Avatar avatar, int posX, int posY, int sizeX, int sizeY) {
        this.avatar = avatar;
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public boolean isCollideable() {
        return collideable;
    }

    public void setNoneCollideable() {
        collideable = true;
    }




    public abstract void onCollide(GameObject object, Actions actions);

    public abstract void logic(World world, Actions actions);
    public abstract void renderOptional(Camera camera);





    public boolean willCollide(GameObject object) {
        return willCollide(object, getPosX(), getPosY());
    }




    /**
     * This checks if this character crashes with other character.
     * @param object target to check against.
     * @return the position to enemy in height and width.
     */
    public boolean willCollide(GameObject object, double x, double y) {
        if(x > object.getPosX() + object.getSizeX() || object.getPosX() > x + sizeX) return false;
        if(y < object.getPosY() + object.getSizeY() || object.getPosY() >  y + sizeY) return false;
        return true;
    }




    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
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
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public Image getImage(ImageHandler imageHandler) {
        return imageHandler.getImage(avatar);
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}
