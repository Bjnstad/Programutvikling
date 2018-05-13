package main.java.model.object.sprite;

import javafx.embed.swing.SwingFXUtils;
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
    private Image staticImage;
    private Image[][] images;

    public Sprite(SpriteSheet spriteSheet) {
        if(spriteSheet == null) throw new NullPointerException("spriteSheet cannot be null.");

        images = new Image[spriteSheet.][]











        if(spriteSheet.isStaticImage()) staticImage = SwingFXUtils.toFXImage(spriteSheet.getSprite(spriteSheet.getStaticX(), spriteSheet.getStaticY()), null);

        if(spriteSheet.isDirectional())
        this.fileName = spriteSheet.getFilename();
        this.directional = spriteSheet.isDirectional();
        animation = directional ? new MultiAnimation(spriteSheet) : new SingleAnimation(spriteSheet);
    }

    public Image getSprite() {
        if(staticImage != null) return staticImage;
        if(animation == null) return null;
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




    public void setDirection(Direction direction) {
        if(!directional) return;
        MultiAnimation cAnimation = (MultiAnimation) animation; // Cast animation to MultiAnimation
        cAnimation.setDirection(direction);
    }
    public boolean isDirectional() {
        return directional;
    }
}
