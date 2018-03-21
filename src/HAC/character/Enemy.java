package HAC.character;

import javafx.scene.image.Image;

public class Enemy extends Character {


    public Enemy(String spriteFileName, int sizeX, int sizeY, int posX, int posY) {
        super(new CharacterAvatar(spriteFileName, 64), sizeX, sizeY);
        super.setPosX(posX);
        super.setPosY(posY);
    }
}