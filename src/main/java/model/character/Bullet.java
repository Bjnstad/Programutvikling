package main.java.model.character;

/**
 * The bullet that is used to shoot in the game, and it contains the speed, position and visibility.
 * @author ceciliethoresen
 */
public class Bullet {
    private final double speed = 7;
    private double velocityX, velocityY;

    private double x, y, speedX;
    private boolean visible;

    /**
     * The coordinates to the bullets from start to end, vertical and horizontal.
     * @param startX coordinate of the cell horizontal.
     * @param startY coordinate of the cell vertical.
     * @param endX coordinate of the cell horizontal.
     * @param endY coordinate of the cell vertical.
     */
    public Bullet(double startX, double startY, double endX, double endY){
        x = startX;
        y = startY;

        speedX = 7;
        visible = true;
        double angle = Math.atan2(endX - startX, endY - startY);
        velocityY = (speed) * Math.cos(angle) / 100;
        velocityX = (speed) * Math.sin(angle) / 100;
    }

    /**
     * This method updates the game.
     * @author ceciliethoresen
     */
    public void update(){
        x += velocityX;
        y += velocityY;
        //@TODO: fine tune constraint x and y
        if (x > 800 || x < -300 ) {
            visible = false;
        }
    }

    /**
     * Gets the state of x.
     * @return the state to x in width.
     * @author ceciliethoresen
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the state of x.
     * @param x is the state horizontal.
     * @author ceciliethoresen
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the state of y.
     * @return the state to y in height.
     * @author ceciliethoresen
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the state of y.
     * @param y is the state vertical.
     * @author ceciliethoresen
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the state of speed.
     * @return speed.
     * @author ceciliethoresen
     */
    public double getSpeedX() {
        return speedX;
    }

    /**
     * Sets the state of speed.
     * @param speedX vertical.
     * @author ceciliethoresen
     */
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    /**
     * Makes the bullets visible.
     * @return visibility of the bullets in the game.
     * @author ceciliethoresen
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets the state to visible.
     * @param visible
     * @author ceciliethoresen
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
