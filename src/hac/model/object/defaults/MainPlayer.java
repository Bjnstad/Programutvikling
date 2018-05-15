package hac.model.object.defaults;

import hac.model.Camera;
import hac.controller.World;
import hac.model.object.GameObject;
import hac.model.object.character.Player;
import hac.model.render.Actions;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/**
 * MainPlayer is the player in focus, the move method will move the camera for the game.
 */
public class MainPlayer extends Player {

    public MainPlayer(String spriteName, double posX, double posY) {
        super(spriteName, posX, posY, 8);
    }

    @Override
    public boolean addPos(double x, double y, World world) {
        Camera camera = world.getCamera();
        double rX = getPosX() + x;
        double rY = getPosY() + y;

        double translateX = camera.scale(-x);
        double translateY = camera.scale(y);
        //if(rX >= ((Math.signum(x) == -1) ? -1 : 0) + camera.getZoom()/2) translateX -= camera.scale(x);
        //if(rY >= ((Math.signum(y) == -1) ? 0 : -1) + camera.getZoom()/2) translateY += camera.scale(y);


        if(rX <= camera.getZoom()/2 - .5) translateX = 0;
        if(rY <= camera.getZoom()/2 - world.getBarHeight() - .5) translateY = 0;
        if(rX > world.getGameMap().getWidth() - camera.getZoom()/2 +1) translateX = 0;
        if(rY > world.getGameMap().getHeight() - camera.getZoom()/2 + 1 + world.getBarHeight()) translateY = 0;



        if (super.addPos(x, -y, world)) {
            camera.translate(translateX, translateY);
            //camera.translate(translateX, translateY);
            return true;
        }

        return false;
    }

    @Override
    public void onCollide(GameObject object, Actions actions) {

    }

    @Override
    public void logic(World world, Actions actions) {

    }

    @Override
    public void renderOptional(Camera camera) {
     }
}
