package main.java.model.character;

import javafx.scene.canvas.GraphicsContext;
import main.java.model.Camera;
import main.java.model.filehandler.SpriteSheet;
import main.java.model.object.GameObject;

/**
 * This class contains the position and size to the character in the game.
 * @author Axel Bjørnstad - s315322
 */
public abstract class Character extends GameObject {

    protected float health = 100;

    /**
     * This method represents the character, and it´s size.
     * @param filename assets file.
     * @param sizeX this is the size to x in width.
     * @param sizeY this is the size to y in height.
     */
    public Character(String filename, int sizeX, int sizeY) {
        super(0,0, sizeX, sizeY, new SpriteSheet(filename, 64, 9, true));
    }


    /**
     * TODO: own render class?
     * This method draws the animations on the game board, that we gets from sprite.
     * @param camera We get graphicscontext from camera, and that makes it possible for us to draw in canvas.
     */
    public void render(Camera camera) {
        GraphicsContext gc = camera.getGraphicsContext();
        gc.drawImage(getSprite(), camera.scale(getPosX()),  camera.scale(getPosY()),  camera.getScale() * getSizeX(), camera.getScale() * getSizeY()) ;
        renderOptional(camera);
    }


    /**
     * This checks if this character crashes with other character.
     * @param character target to check against.
     * @return the position to enemy in height and width.
     */
    public boolean willCollide(Character character) {
        double a = character.getPosX() - character.getSizeX()/2;
        double b = character.getPosX() + character.getSizeX()/2;
        double c = getPosX();
        boolean xval = b > a ? c > a && c < b : c > b && c < a;

        a = character.getPosY() - character.getSizeY()/2;
        b = character.getPosY() + character.getSizeY()/2;
        c = getPosY();
        boolean yval = b > a ? c > a && c < b : c > b && c < a;

        return xval && yval;

    }

    /**
     * This checks if this character crashes with other character.
     * @param bullet target to check against.
     * @return the position to enemy in height and width.
     */
    public boolean willCollide(Bullet bullet) {
        double a = bullet.getX() - 10/2;
        double b = bullet.getX() + 10/2;
        double c = getPosX();
        boolean xval = b > a ? c > a && c < b : c > b && c < a;

        a = bullet.getY() - 5/2;
        b = bullet.getY() + 5/2;
        c = getPosY();
        boolean yval = b > a ? c > a && c < b : c > b && c < a;

        return xval && yval;

    }

    public abstract void renderOptional(Camera camera);
}
