package hac.model.object;


import hac.model.Camera;
import hac.model.object.character.Character;
import hac.model.render.Actions;
import hac.controller.World;

/**
 * The bullet that is used to shoot in the game, and it contains the speed, position and visibility.
 * @author Axel Bjørnstad - s315322
 */
public abstract class Bullet extends MoveableObject {
    private final GameObject parent;
    private final double speed = 40;
    private final double strength = 20;
    private double velocityX, velocityY;
    private double rotationX, rotationY = -1;

    /**
     * The coordinates to the bullets from start to end, vertical and horizontal.
     *
     * @param endX coordinate of the cell horizontal.
     * @param endY coordinate of the cell vertical.
     */
    public Bullet(GameObject parent, double endX, double endY, int frames) {
        super("default_bullet", parent.getPosX() + .5, parent.getPosY() + .5, frames);
        this.parent = parent;
        setNoneCollideable(); // Moveable objects shouldn't collide
        double angle = Math.atan2(endX - getPosX() + .5, endY - getPosY() + .5);
        velocityY = (speed) * Math.cos(angle) / 100;
        velocityX = (speed) * Math.sin(angle) / 100;
    }

    public void setRotation(double rotationX, double rotationY) {
        this.rotationX = rotationX;
        this.rotationY = rotationY;
    }

    @Override
    public void onCollide(GameObject object, Actions actions) {
        if (object instanceof Character && object != parent) {
            ((Character) object).hit(strength);
            actions.removeObject(this); // Delete bullet on hit
        }
    }

    @Override
    public void logic(World world, Actions actions) {
        if (getPosX() < 0 || getPosY() < 0 || getPosX() > world.getCamera().getDimension() || getPosY() > world.getCamera().getDimension())
            actions.removeObject(this);
        addPos(velocityX, velocityY, world);
    }

    @Override
    public void renderOptional(Camera camera) {

    }
}
