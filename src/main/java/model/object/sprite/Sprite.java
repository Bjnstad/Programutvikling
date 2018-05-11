package main.java.model.object.sprite;

import javafx.scene.image.Image;

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
        if(spriteSheet == null) {
            directional = false;
            return; // TODO: catch null?
        }
        this.fileName = spriteSheet.getFilename();
        this.directional = spriteSheet.isDirectional();
        animation = directional ? new MultiAnimation(spriteSheet) : new SingleAnimation(spriteSheet);
    }

    public void setDirection(Direction direction) {
        if(!directional) return;
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
        if(!directional) return null;
        MultiAnimation cAnimation = (MultiAnimation) animation; // Cast animation to MultiAnimation
        return cAnimation.getDirection();
    }


    public boolean isDirectional() {
        return directional;
    }
}
