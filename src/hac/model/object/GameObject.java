package hac.model.object;

import javafx.scene.image.Image;
import hac.model.Camera;
import hac.model.object.sprite.Avatar;
import hac.model.object.sprite.ImageHandler;
import hac.model.render.Actions;

/**
 * Main object used in World, best practise
 * @author Axel Bjørnstad - s315322
 */
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

    public void setNoneCollideable() {
        collideable = false;
    }

    /**
     * If this object collides with an other object as parameter, game actions can be done through actions.
     * @param object Object colliding with this.
     * @param actions Call actions based on your wanted logic
     */
    public abstract void onCollide(GameObject object, Actions actions);

    /**
     * Called on every gameloop, giving you opportunity to do logic for object.
     * @param actions Call actions based on your wanted logic
     */
    public abstract void logic(Actions actions);

    /**
     * Called on every render, giving you opportunity to draw on screen.
     * @param camera use GraficalContext to draw to screen.
     */
    public abstract void renderOptional(Camera camera);

    public boolean willCollide(GameObject object) {
        return willCollide(object, posX, posY);
    }

    /**
     * This checks if this character crashes with other character.
     * @param object target to check against.
     * @return the position to enemy in height and width.
     */
    public boolean willCollide(GameObject object, double x, double y) {
        double deadzone = .01;
        if(x > object.getPosX() + 1 -deadzone || x + 1 -deadzone < object.getPosX()) return false;
        return !(y > object.getPosY() + 1 -deadzone) && !(y + 1-deadzone < object.getPosY());
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public Image getImage(ImageHandler imageHandler) {
        return imageHandler.getImage(avatar);
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }
}
