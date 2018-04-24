package main.java.HAC.editor;

import main.java.HAC.world.GameObject;
import javafx.scene.image.Image;

/**
 * In this class the file extends the properties from GameObject.
 * @author ceciliethoresen
 */
public class HacFile extends GameObject{

    /**
     * This method contents the size and position of the file.
     * We use super to take arguments that we want to pass in from subclass(GameObject).
     * @param image is the visually picture of the file.
     * @param sizeX is the size of the file in width.
     * @param sizeY is the size of the file in height.
     * @param posX is the position to x.(width)
     * @param posY is the position to y.(height)
     */
    public HacFile(Image image, int sizeX, int sizeY, int posX, int posY) {
        super(image, posX, posY, sizeX, sizeY);
    }

}
