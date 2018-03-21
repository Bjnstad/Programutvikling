package HAC.editor;

import HAC.world.GameObject;
import javafx.scene.image.Image;

/**
 * Created by henrytran1 on 06/03/2018.
 */
public class HacFile extends GameObject{
    private int posX;
    private int posY;

    public HacFile(Image image, int sizeX, int sizeY, int posX, int posY) {
        super(image, sizeX, sizeY);
        this.posX = posX;
        this.posY = posY;
    }


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
