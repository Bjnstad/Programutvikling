package HAC.character;

import javafx.scene.image.Image;

public class Player extends Character {
    /**
     * Player in the game
     */
    public Player() {
        super(new CharacterAvatar("player_animations_walking", 64), 2, 2);
    }

}
