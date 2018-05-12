package main.java.model.object;

import javafx.scene.image.Image;
import main.java.model.Camera;
import main.java.model.object.sprite.SpriteSheet;
import main.java.model.object.GameObject;
import main.java.model.render.Actions;
import main.java.model.world.World;

/**
 * This class defines the gameObject.
 * @author Axel Bjørnstad - s315322
 */
public class MapObject extends GameObject {
    private Image image;

    /**
     * Visual, position and size to gameObject. TODO: javadoc
     * @param spritesheet represents the gameObject.
     * @param posY  position to gameObject in height.
     * @param posX position to gameObject in width.
     * @param sizeX size to gameObject in width.
     * @param sizeY size to gameObject in height.
     */
    public MapObject(SpriteSheet spritesheet, int posY, int posX, int sizeX, int sizeY) {
        super(posX, posY, sizeX, sizeY, spritesheet);
        this.image = image;
    }

    /**
     * Gets the asset.
     * @return asset.
     */
    public Image getAsset() {
        return this.image;
    }

    @Override
    public void onCollide(GameObject object, Actions actions) {

    }

    @Override
    public void logic(World world) {

    }

    @Override
    public void renderOptional(Camera camera) {

    }
}




