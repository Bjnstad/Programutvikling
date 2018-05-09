package main.java.model.character;

/**
 * The bullet that is used to shoot in the game, and it contains the speed, position and visibility.
 * @author
 */
public class Bullet {
    private final double speed = .000000000000000000000000000000001;
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
    public Bullet(double scale, double startX, double startY, double endX, double endY){
        x = startX/scale;
        y = startY/scale;

        speedX = speed;
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
        x += velocityX;
        y += velocityY;
        //@TODO: fine tune constraint x and y
        if (x > 8000 || x < -300 ) {
            visible = false;
        }
    }

    /**
     * Gets the state of x.
     * @return the state to x in width.
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the state of x.
     * @param x is the state horizontal.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the state of y.
     * @return the state to y in height.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the state of y.
     * @param y is the state vertical.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the state of speed.
     * @return speed.
     */
    public double getSpeedX() {
        return speedX;
    }

    /**
     * Sets the state of speed.
     * @param speedX vertical.
     */
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
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
