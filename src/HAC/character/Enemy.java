package HAC.character;

import javafx.scene.image.Image;

public class Enemy extends Character {

    /**
     * Enemy character in the game
     * @param avatar
     * @param sizeX
     * @param sizeY
     * @param posX
     * @param posY
     */
    public Enemy(CharacterAvatar avatar, int sizeX, int sizeY, int posX, int posY) {
        super(new CharacterAvatar("player_shooting_animation", 64), sizeX, sizeY);
        super.setPosX(posX);
        super.setPosY(posY);
    }
}