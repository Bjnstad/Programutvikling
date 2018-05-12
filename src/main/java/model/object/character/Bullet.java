package main.java.model.object.character;


import main.java.model.Camera;
import main.java.model.object.GameObject;
import main.java.model.object.sprite.SpriteSheet;
import main.java.model.render.Actions;
import main.java.model.world.World;

/**
 * The bullet that is used to shoot in the game, and it contains the speed, position and visibility.
 * @author
 */
public class Bullet extends GameObject {
    private final double speed = 10;
    private double velocityX, velocityY;
    private boolean visible;

    /**
     * The coordinates to the bullets from start to end, vertical and horizontal.
     * @param startX coordinate of the cell horizontal.
     * @param startY coordinate of the cell vertical.
     * @param endX coordinate of the cell horizontal.
     * @param endY coordinate of the cell vertical.
     */
    public Bullet(double scale, double startX, double startY, double endX, double endY){
        super((int)(startX/scale), (int)(startY/scale), 1, 1, new SpriteSheet("arrow", 64, 9, true));

        visible = true;
        double angle = Math.atan2(endX - startX, endY - startY);
        velocityY = (speed) * Math.cos(angle) / 100;
        velocityX = (speed) * Math.sin(angle) / 100;

        System.out.println("------");
        System.out.println(velocityX);
        System.out.println(velocityY);
    }

    /**
     * Updates the game.
     */
    public void update(){
        addPos(velocityX, velocityY);

        //@TODO: fine tune constraint x and y
        if (getPosX() > 8000 || getPosX() < -300 ) {
            visible = false;
        }
    }

    /**
     * Makes the bullets visible.
     * @return visibility of the bullets in the game.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets the state to visible.
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void onCollide(GameObject object, Actions actions) {

    }

    @Override
    public void logic(World world) {

    }

    @Override
    public void renderOptional(Camera camera) {

    }
}
