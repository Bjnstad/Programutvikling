package main.java.model.object;


import main.java.model.Camera;
import main.java.model.object.character.Character;
import main.java.model.filehandler.SpriteSheet;
import main.java.model.render.Actions;
import main.java.model.world.World;

/**
 * The bullet that is used to shoot in the game, and it contains the speed, position and visibility.
 * @author
 */

// TODO: MAKE CHARACTER MOVABLE
public class Bullet extends Character {
    private final GameObject parent;
    private final double speed = 10;
    private final double strength = 20;
    private double velocityX, velocityY;

    /**
     * The coordinates to the bullets from start to end, vertical and horizontal.
     * @param startX coordinate of the cell horizontal.
     * @param startY coordinate of the cell vertical.
     * @param endX coordinate of the cell horizontal.
     * @param endY coordinate of the cell vertical.
     */
    // TODO: fix size
    public Bullet(GameObject parent, double startX, double startY, double endX, double endY){
        super("WEAPON_arrow" ,(int)(startX), (int)(startY));
        this.parent = parent;

        setNoneCollideable();

        double angle = Math.atan2(endX - startX, endY - startY);
        velocityY = (speed) * Math.cos(angle) / 100;
        velocityX = (speed) * Math.sin(angle) / 100;
    }

    @Override
    public void onCollide(GameObject object, Actions actions) {
        if(object instanceof Character && object != parent) {
            ((Character) object).hit(strength);
        }

        actions.removeObject(this); // Delete bullet on hit
    }

    @Override
    public void logic(World world, Actions actions) {
        if(getPosX() < 0 || getPosY() < 0 || getPosX() > world.getCamera().getDimension() || getPosY() > world.getCamera().getDimension()) actions.removeObject(this);
        addPos(velocityX, velocityY, world);
    }

    @Override
    public void renderOptional(Camera camera) {

    }
}
