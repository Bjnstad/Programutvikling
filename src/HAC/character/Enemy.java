package HAC.character;

import javafx.scene.image.Image;

public class Enemy extends Character {

    public Enemy(Image avtar, int sizeX, int sizeY, int posX, int posY) {
        super(avtar, sizeX, sizeY);
        super.setPosX(posX);
        super.setPosY(posY);
    }
}