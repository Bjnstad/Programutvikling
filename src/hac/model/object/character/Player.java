package hac.model.object.character;

import hac.model.Camera;
import hac.model.object.GameObject;
import hac.model.render.Actions;
import hac.controller.World;

/**
 * This class represents the player in the game.
 * Player extends the qualities from character.
 * @author
 */
public abstract class Player extends Character {

    /**
     * This method contains the player in the game.
     * It extends its characteristics from character, and it´s coordinates.
     */
    public Player(String spriteName, double posX, double posY, int frames) {
        super(spriteName, posX, posY, 9);
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
