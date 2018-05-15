package hac.model.object.defaults;

import hac.model.Camera;
import hac.controller.World;
import hac.model.object.character.Player;


/**
 * MainPlayer is the player in focus, the move method will move the camera for the game.
 */
public class MainPlayer extends Player {

    public MainPlayer(String spriteName, double posX, double posY) {
        super(spriteName, posX, posY, 9);
    }

    @Override
    public boolean addPos(double x, double y, World world) {
        Camera camera = world.getCamera();

        int rX = (int) (getPosX() + x);
        int rY = (int) (getPosY() + y);

        double translateX = 0;
        double translateY = 0;
        if(rX >= ((Math.signum(x) == -1) ? -1 : 0) + camera.getZoom()/2) translateX -= camera.scale(x);
        if(rY >= ((Math.signum(y) == -1) ? 0 : -1) + camera.getZoom()/2) translateY += camera.scale(y);

        if (super.addPos(x, -y, world)) {
            camera.translate(translateX, translateY);
            return true;
        }

        return false;
    }
}
