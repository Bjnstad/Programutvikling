package game.character;

import javafx.scene.image.Image;

public class Player extends Character {

    private Image avatar; // TODO: Change to sprite

    public Player(Image avatar) {
        this.avatar = avatar;
    }

    public Image getAvatar() {
        return avatar;
    }

}
