package main.java.model.sprite;

import javafx.scene.image.Image;
import main.java.model.character.Direction;
import main.java.model.filehandler.SpriteSheet;

/**
 * // TODO: Enemies moves to fast, better interval increment
 * This class handles animation for a character find what sprite to return respectly.
 * @author Axel Bjørnstad - s315322
 */
public class Sprite {

    private String fileName;
    private Animation animation;
    private boolean directional;

    public Sprite(SpriteSheet spriteSheet) {
        this.fileName = spriteSheet.getFilename();
        this.directional = spriteSheet.isDirectional();
        animation = directional ? new MultiAnimation(spriteSheet) : new SingleAnimation(spriteSheet);
    }

    public void setDirection(Direction direction) {
        if(!directional) throw new IllegalStateException("Spirte is not directional, cannot set direction.");
        MultiAnimation cAnimation = (MultiAnimation) animation; // Cast animation to MultiAnimation
        cAnimation.setDirection(direction);
    }

    private void loadAssets(SpriteSheet spriteSheet, boolean directional) {
        if(directional) {
        } else {

        }
    }

    public Image getSprite() {
        return animation.getImage();
    }

    public String getSpriteFileName(){
        return fileName;
    }

    public Direction getDirection() {
        if(!directional) throw new IllegalStateException("Spirte is not directional, cannot get direction.");
        MultiAnimation cAnimation = (MultiAnimation) animation; // Cast animation to MultiAnimation
        return cAnimation.getDirection();
    }


    public boolean isDirectional() {
        return directional;
    }
}
