package hac.model.object.predefined.character;

import hac.model.object.defaults.MainPlayer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import hac.model.Camera;
import hac.model.object.GameObject;
import hac.model.render.Actions;


public abstract class Enemy extends Character {

    private float speed = 1;
    /**
     * This method represents position, size and qualities to the enemy in the game.
     * @param spriteFileName is the name of the file that can be downloaded.
     * @param posX is the position to the enemy in width(x).
     * @param posY is the position to the enemy in heigth(y).
     */
    public Enemy(String spriteFileName, double posX, double posY, int frames) {
        super(spriteFileName, posX, posY, frames);
        super.setPosX(posX);
        super.setPosY(posY);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    @Override
    public void onCollide(GameObject object, Actions actions) {
        if(object instanceof MainPlayer) {
            ((MainPlayer) object).hit(30);
            double angle = Math.atan2(object.getPosX() - getPosX(), object.getPosY() - getPosY());
            double rx = 50 * Math.sin(angle) / 100;
            double ry = 50 * Math.cos(angle) / 100;
            ((MainPlayer) object).addPos(rx, ry, actions.getWorld());
        }
    }

    @Override
    public void logic(Actions actions) {
        MainPlayer player = actions.getWorld().getMainPlayer();
        if(player == null) throw new IllegalStateException("Player not set");
        double angle = Math.atan2(player.getPosX() - getPosX(), player.getPosY() - getPosY());
        double rx = speed * Math.sin(angle) / 100;
        double ry = speed * Math.cos(angle) / 100;
        addPos(rx, ry, actions.getWorld()); // Move towards player.
    }

    /**
     * Render health bar
     */
    @Override
    public void renderOptional(Camera camera) {
        GraphicsContext gc = camera.getGraphicsContext();

        // Draw bar
        gc.setFill(Color.BLACK);
        gc.fillRect(camera.scale(getPosX()), camera.scale(getPosY()) - camera.getScale()/7, camera.getScale(), camera.getScale()/5 );

        // Draw health
        gc.setFill(Color.GREEN);
        gc.fillRect(camera.scale(getPosX()), camera.scale(getPosY()) - camera.getScale()/7,camera.getScale() - (100 - health)  * camera.getScale() / 100, camera.getScale()/5);
    }
}