package main.java.hac.model.object.character;

import main.java.hac.model.object.GameObject;
import main.java.hac.model.object.sprite.Avatar;
import main.java.hac.model.object.sprite.Direction;
import main.java.hac.model.object.sprite.animation.MultiAnimation;
import main.java.hac.controller.World;

/**
 * This class contains the position and size to the character in the game.
 * @author Axel Bjørnstad - s315322
 */
public abstract class Character extends GameObject {

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
     * In this method we add the position to the character in width.
     * If the speed to the character is less then 0, then it walks left.
     * If not, it walks right.
     //* @param speed this is the speed to the character walking right or left.
     */
    public boolean addPos(double x, double y, World world) {
        if(getPosX() + x < 0 || getPosY() + y < 0 || getPosX() + x > world.getCamera().getDimension() || getPosY() + y > world.getCamera().getDimension()) return false;


        for(GameObject object : world.getGameObjects()) {
            if(willCollide(object,(int)(getPosX() + x), (int)(getPosY() + y))  && object.isCollideable()) return false;
        }

        if(Math.abs(x) > Math.abs(y)) {
            ((MultiAnimation) avatar.getAnimation()).setDirection(x < 0 ? Direction.LEFT : Direction.RIGHT);
        } else {
            ((MultiAnimation) avatar.getAnimation()).setDirection(y < 0 ? Direction.UP : Direction.DOWN);
        }

        setPosX(getPosX() + x);
        setPosY(getPosY() + y);
        return true;
    }

    /**
     * This method represents the character, and it´s size.
     * @param filename assets file.
     * @param sizeX this is the size to x in width.
     * @param sizeY this is the size to y in height.
     */
    // TODO: add frames
    public Character(String filename, int sizeX, int sizeY) {
        super(new Avatar(filename, new MultiAnimation(9)),0,0, sizeX, sizeY);
    }
}
