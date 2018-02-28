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
        System.out.println("-----");
        System.out.println("startx: "+startX);
        System.out.println("starty: "+startY);
        System.out.println("endx: "+endX);
        System.out.println("endy: "+endY);

        x = startX;
        y = startY;



        speedX = 7;
        visible = true;
        double angle = Math.atan2(endX - startX, endY - startY);
        velocityY = (speed) * Math.cos(angle) / 100;
        velocityX = (speed) * Math.sin(angle) / 100;


    }

    public void update(){
        x += velocityX;
        y += velocityY;
        //@TODO: fine tune constraint x and y
        if (x > 800 || x < -300 ) {
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
