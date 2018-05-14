package hac.model.object;

import javafx.scene.image.Image;
import hac.model.Camera;
import hac.model.object.sprite.Avatar;
import hac.model.object.sprite.ImageHandler;
import hac.model.render.Actions;
import hac.controller.World;

public abstract class GameObject {

    Avatar avatar;
    private double posX;
    private double posY;
    private boolean collideable = true;

    public GameObject(Avatar avatar, double posX, double posY) {
        this.avatar = avatar;
        this.posX = posX;
        this.posY = posY;
    }

    public boolean isCollideable() {
        return collideable;
    }

    void setNoneCollideable() {
        collideable = false;
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
    boolean willCollide(GameObject object, double x, double y) {
        double deadzone = .001;
        if(x > object.getPosX() + 1 -deadzone || x + 1 -deadzone < object.getPosX()) return false;
        return !(y > object.getPosY() + 1 -deadzone) && !(y + 1-deadzone < object.getPosY());
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
}
