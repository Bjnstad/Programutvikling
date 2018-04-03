package HAC.character;

/**
 * The bullet that is used to shoot in the game.
 */
public class Bullet {
    private final double speed = 7;
    private double velocityX, velocityY;

    private double x, y, speedX;
    private boolean visible;

    /**
     * The coordinates to the bullets.
     * @param startX coordinate of the cell
     * @param startY coordinate of the cell
     * @param endX coordinate of the cell
     * @param endY coordinate of the cell
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
     * Updates the game
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
     * @return the state to x.
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the state of x.
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the state of y.
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the state of y.
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the state of speed
     * @return speed
     */
    public double getSpeedX() {
        return speedX;
    }

    /**
     * Sets the state of speed
     * @param speedX
     */
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    /**
     * ..
     * @return visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets the state to visible
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
