package hac.model.object.character;


import hac.model.object.MoveableObject;


/**
 * This class contains the position and size to the character in the game.
 * @author Axel Bjørnstad - s315322
 */
public abstract class Character extends MoveableObject {

    protected double health = 100;
    protected double threshold = .2;


    public Character(String filename, double posX, double posY, int frames) {
        super(filename, posX, posY, frames);
    }

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

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }
}
