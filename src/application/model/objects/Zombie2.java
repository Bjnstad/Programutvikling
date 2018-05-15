package application.model.objects;

import hac.model.object.character.Enemy;

public class Zombie2 extends Enemy {
    /**
     * This method represents position, size and qualities to the enemy in the game.
     * @param posX           is the position to the enemy in width(x).
     * @param posY           is the position to the enemy in heigth(y).
     */
    public Zombie2(double posX, double posY) {
        super("zombie2", posX, posY, 9);
    }
}
