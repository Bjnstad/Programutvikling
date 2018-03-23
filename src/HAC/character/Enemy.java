package HAC.character;

import javafx.scene.image.Image;

public class Enemy extends Character {

    private float speed = 3;


    public Enemy(String spriteFileName, int sizeX, int sizeY, int posX, int posY) {
        super(new CharacterAvatar(spriteFileName, 64), sizeX, sizeY);
        super.setPosX(posX);
        super.setPosY(posY);
    }

    public void calculateMove(Player player) {
        double angle = Math.atan2(player.getPosX() - getPosX(), player.getPosY() - getPosY());
        addPosY(speed * Math.cos(angle) / 100);
        addPosX(speed * Math.sin(angle) / 100);
    }
}