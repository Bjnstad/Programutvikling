package hac.model.object.predefined;

import hac.model.Camera;
import hac.model.object.GameObject;
import hac.model.object.predefined.character.Character;
import hac.model.render.Actions;

/**
 * The bullet that is used to shoot in the game, and it contains the speed, position and visibility.
 * @author Axel Bjørnstad - s315322
 */
public abstract class Bullet extends MoveableObject {
    private final GameObject parent;
    private double velocityX, velocityY;
    private final double speed;
    private final double strength;

    /**
     * The coordinates to the bullets from start to end, vertical and horizontal.
     * @param endX coordinate of the cell horizontal.
     * @param endY coordinate of the cell vertical.
     */
    public Bullet(GameObject parent, double endX, double endY, double speed, double strength){
        super("default_bullet", parent.getPosX() +.5, parent.getPosY() +.5, 7);
        this.parent = parent;
        this.speed = speed;
        this.strength = strength;

        setNoneCollideable(); // Moveable objects shouldn't collide
        double angle = Math.atan2(endX - getPosX() +.5, endY - getPosY() +.5);
        velocityY = (speed) * Math.cos(angle) / 100;
        velocityX = (speed) * Math.sin(angle) / 100;
    }

    @Override
    public void onCollide(GameObject object, Actions actions) {
        if(object instanceof Character && object != parent) {
            ((Character) object).hit(strength);
            actions.removeObject(this); // Delete bullet on hit
        }
    }

    @Override
    public void logic(Actions actions) {
        if(getPosX() < 0 || getPosY() < 0 || getPosX() > actions.getWorld().getCamera().getDimension() || getPosY() > actions.getWorld().getCamera().getDimension()) actions.removeObject(this);
        addPos(velocityX, velocityY, actions.getWorld());
    }

    @Override
    public void renderOptional(Camera camera) {

    }
}
