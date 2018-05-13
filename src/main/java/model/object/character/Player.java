package main.java.model.object.character;

import main.java.model.Camera;
import main.java.model.object.Bullet;
import main.java.model.object.GameObject;
import main.java.model.render.Actions;
import main.java.model.world.World;

import java.util.ArrayList;

/**
 * This class represents the player in the game.
 * Player extends the qualities from character.
 * @author
 */
public class Player extends Character {

    /**
     * This method contains the player in the game.
     * It extends its characteristics from character, and it´s coordinates.
     */
    public Player(String spriteName, int sizeX, int sizeY, double posX, double posY) {
        super(spriteName, sizeX, sizeY);
        setPosX(posX);
        setPosY(posY);
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
