package hac.model.object.character;

import hac.model.object.GameObject;
import hac.model.object.MoveableObject;
import hac.model.object.sprite.Avatar;
import hac.model.object.sprite.Direction;
import hac.model.object.sprite.animation.MultiAnimation;
import hac.controller.World;

/**
 * This class contains the position and size to the character in the game.
 * @author Axel Bjørnstad - s315322
 */
public abstract class Character extends MoveableObject {

    protected double health = 100;
    protected double threshold = .2;

    public boolean isDead() {
        return health < 0;
    }

    public void hit(double strength) {
        health -= strength * threshold;
    }

    public void heal(double heal) {
        health += heal;
        if(health > 100) health = 100;
    }

    /**
     * This method represents the character, and it´s size.
     * @param filename assets file.
     * @param sizeX this is the size to x in width.
     * @param sizeY this is the size to y in height.
     */
    // TODO: add frames
    public Character(String filename, int sizeX, int sizeY) {
        super(filename, sizeX, sizeY);
    }
}
