package main.java.model.character;


import main.java.model.object.GameObject;

/**
 * The bullet that is used to shoot in the game, and it contains the speed, position and visibility.
 * @author
 */
public class Bullet extends GameObject {
    private final double speed = 0.1;
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
        super((int)(startX/scale), (int)(startY/scale), 1, 1, null);

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
}
