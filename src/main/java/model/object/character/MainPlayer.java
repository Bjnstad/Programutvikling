package main.java.model.object.character;

import main.java.model.Camera;
import main.java.model.world.World;


/**
 * MainPlayer is the player in focus, the move method will move the camera for the game.
 */
public class MainPlayer extends Player {

    public MainPlayer(String spriteName, int sizeX, int sizeY, double posX, double posY) {
        super(spriteName, sizeX, sizeY, posX, posY);
    }

    public void move(double x, double y, World world) {
        Camera camera = world.getCamera();

        int rX = (int) (getPosX() + x);
        int rY = (int) (getPosY() - y);

        double translateX = 0;
        double translateY = 0;
        if(rX >= ((Math.signum(x) == -1) ? -1 : 0) + camera.getZoom()/2) translateX -= camera.scale(x);
        if(rY >= ((Math.signum(y) == -1) ? 0 : -1) + camera.getZoom()/2) translateY += camera.scale(y);
        if(camera.getDimension() - camera.getZoom()/2 < rX) translateX = 0;
        if(camera.getDimension() - camera.getZoom()/2 < rY) translateY = 0;
        camera.translate(translateX, translateY);

        addPos(x, -y, world);
    }
}
