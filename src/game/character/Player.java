package game.character;

import javafx.scene.paint.Color;

public class Player extends Character {

    private Color color; // TODO: Change to sprite

    public Player(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
