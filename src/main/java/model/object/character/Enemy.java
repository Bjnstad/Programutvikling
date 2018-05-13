package main.java.model.object.character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.java.model.Camera;
import main.java.model.object.GameObject;
import main.java.model.render.Actions;
import main.java.model.world.World;

/**
 * In this class Enemy extends the qualities from character.
 * @author
 */
public class Enemy extends Character {

    private float speed = 1;
    private double threshold= 0.1;

    /**
     * This method represents position, size and qualities to the enemy in the game.
     * @param spriteFileName is the name of the file that can be downloaded.
     * @param sizeX is the size of the enemy in width(x).
     * @param sizeY is the size of the enemy in height(y).
     * @param posX is the position to the enemy in width(x).
     * @param posY is the position to the enemy in heigth(y).
     */
    public Enemy(String spriteFileName, int sizeX, int sizeY, double posX, double posY) {
        super(spriteFileName, sizeX, sizeY);
        super.setPosX(posX);
        super.setPosY(posY);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public void hit(double strength) {
        health -= strength * threshold;
    }

    /**
     * Render health
     */
    public void renderOptional(Camera camera) {
        GraphicsContext gc = camera.getGraphicsContext();

        // Draw bar
        gc.setFill(Color.BLACK);
        gc.fillRect(camera.scale(getPosX()), camera.scale(getPosY()) - camera.getScale()/7,getSizeX()*camera.getScale(), camera.getScale()/5 * getSizeY());

        // Draw health
        gc.setFill(Color.GREEN);
        gc.fillRect(camera.scale(getPosX()), camera.scale(getPosY()) - camera.getScale()/7,getSizeX()*camera.getScale() - (100 - health)  * camera.getScale() / 100, camera.getScale()/5 * getSizeY());

    }


    public boolean isDead() {
        return health < 0;
    }

    public float getSpeed() {
        return speed;
    }

    @Override
    public void onCollide(GameObject object, Actions actions) {

    }

    @Override
    public void logic(World world) {


        Player player = world.getPlayer();

        double angle = Math.atan2(player.getPosX() - getPosX(), player.getPosY() - getPosY());
        double rx = speed * Math.sin(angle) / 100;
        double ry = speed * Math.cos(angle) / 100;

        for(GameObject object : world.getGameObjects()) {
            if(willCollide(object,(int)(getPosX() + rx), (int)(getPosY() + ry))) return;
        }

        addPos(rx, ry);

        if (willCollide(player)) world.die(); // Player dies
    }
}