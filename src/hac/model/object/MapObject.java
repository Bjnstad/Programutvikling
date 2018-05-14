package hac.model.object;

import javafx.scene.image.Image;
import hac.model.Camera;
import hac.model.object.sprite.Avatar;
import hac.model.render.Actions;
import hac.controller.World;

/**
 * This class defines the gameObject.
 * @author Axel Bjørnstad - s315322
 */
public class MapObject extends GameObject {
    private Image image;

    /**
     * Visual, position and size to gameObject. TODO: javadoc
     * @param avatar represents the gameObject.
     * @param posY  position to gameObject in height.
     * @param posX position to gameObject in width.
     */
    public MapObject(Avatar avatar, int posY, int posX) {
        super(avatar, posX, posY);
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
    public void logic(World world, Actions actions) {

    }

    @Override
    public void renderOptional(Camera camera) {

    }
}




