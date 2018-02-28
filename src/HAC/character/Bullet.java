package HAC.character;

/**
 * Created by henrytran1 on 27/02/2018.
 */
public class Bullet {
    private final double speed = 7;
    private double velocityX, velocityY;

    private double x, y, speedX;
    private boolean visible;


    public Bullet(double startX, double startY, double endX, double endY){
        x = startX;
        y = startY;
        speedX = 7;
        visible = true;
        double angle = Math.atan2(endX - startX, endY - startY);
        velocityY = (speed) * Math.cos(angle);
        velocityX = (speed) * Math.sin(angle);

        System.out.println("End x: " +endX);
        System.out.println("End y: " +endY);
    }

    public void update(){
        x += velocityX;
        y += velocityY;
        if (x > 800) {
            visible = false;
        }
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
