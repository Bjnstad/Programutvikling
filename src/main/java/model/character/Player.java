package main.java.model.character;


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
        super("player_animations_walking", 1, 1);
        setPosX(1);
        setPosY(1);
    }
}
