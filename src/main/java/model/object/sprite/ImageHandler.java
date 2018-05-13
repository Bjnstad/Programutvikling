package main.java.model.object.sprite;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageHandler {
    List<Sprite> sprites = new ArrayList<>();

    private Sprite loadSprite(String filename) {
        Sprite result = new Sprite(filename);
        sprites.add(result);
        return result;
    }

    private Sprite getSprite(String filename) {
        for (Sprite sprite : sprites) {
            if(sprite.getSpriteFileName().equals(filename)) return sprite;
        }

        return loadSprite(filename); // Sprite wasn't added.
    }

    public Image getImage(Avatar avatar) {
        Sprite sprite = getSprite(avatar.getFilename());
        return  sprite.getSprite(avatar.getX(), avatar.getY());
    }
}
