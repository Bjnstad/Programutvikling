package hac.model.object.defaults;

import hac.model.object.predefined.character.Enemy;

public class Skeleton extends Enemy {

    /**
     * This method represents position, size and qualities to the enemy in the game.
     *
     * @param posX           is the position to the enemy in width(x).
     * @param posY           is the position to the enemy in heigth(y).
     */
    public Skeleton(double posX, double posY) {
        super("default_enemy", posX, posY, 9);
    }
}