package hac.model.object.predefined.character;

/**
 * This class represents the player in the game.
 * Player extends the qualities from character.
 * @author s315322 - Axel Bjørnstad
 */
public abstract class Player extends Character {

    /**
     * This method contains the player in the game.
     * It extends its characteristics from character, and it´s coordinates.
     */
    public Player(String spriteName, double posX, double posY, int frames) {
        super(spriteName, posX, posY, 9);
    }
}
