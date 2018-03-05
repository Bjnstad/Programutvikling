package HAC.character;

import javafx.scene.image.Image;

public class Enemy extends Character {

    public Enemy(CharacterAvatar avatar, int sizeX, int sizeY, int posX, int posY) {
        super(new CharacterAvatar("player_shooting_animation"), sizeX, sizeY);
        super.setPosX(posX);
        super.setPosY(posY);
    }
}