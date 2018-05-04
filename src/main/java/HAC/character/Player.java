package main.java.HAC.character;


import javafx.scene.image.Image;

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
    public Player() {
        super(new CharacterAvatar("player_animations_walking", 64), 1, 1);
    }

    /**
     * This is a boolean method for the enemy if it collides with the avatar.
     * @param enemy contains the position to the enemy-character
     * @return the position to enemy in height and width.
     */
    public boolean willCollide(Enemy enemy) {
        double a = enemy.getPosX() - enemy.getSizeX()/2;
        double b = enemy.getPosX() + enemy.getSizeX()/2;
        double c = getPosX();
        boolean xval = b > a ? c > a && c < b : c > b && c < a;

        a = enemy.getPosY() - enemy.getSizeY()/2;
        b = enemy.getPosY() + enemy.getSizeY()/2;
        c = getPosY();
        boolean yval = b > a ? c > a && c < b : c > b && c < a;

        return xval && yval;

    }
}
