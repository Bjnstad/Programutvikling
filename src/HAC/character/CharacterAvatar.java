package HAC.character;


import HAC.sprite.Sprite;
import HAC.sprite.SpriteAnimation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by henrytran1 on 04/03/2018.
 */
public class CharacterAvatar {


    private SpriteAnimation walkingUp;
    private SpriteAnimation walkingDown;
    private SpriteAnimation walkLeft;
    private SpriteAnimation walkRight;
    private SpriteAnimation standing;
    private Sprite sprite;
    private SpriteAnimation animation;



    public CharacterAvatar(String fileName){
        sprite = new Sprite(fileName);


        BufferedImage[] walkingLeftAnimation = {sprite.getSprite(0, 1), sprite.getSprite(1, 1), sprite.getSprite(2,1),sprite.getSprite(3,1), sprite.getSprite(4,1), sprite.getSprite(5,1), sprite.getSprite(6,1), sprite.getSprite(7,1), sprite.getSprite(8,1)};
        BufferedImage[] walkingRightAnimation = {sprite.getSprite(0, 3), sprite.getSprite(1, 3), sprite.getSprite(2,3),sprite.getSprite(3,3), sprite.getSprite(4,3), sprite.getSprite(5,3), sprite.getSprite(6,3), sprite.getSprite(7,3), sprite.getSprite(8,3)};
        BufferedImage[] walkingUpAnimation = {sprite.getSprite(0, 0), sprite.getSprite(1, 0), sprite.getSprite(2,0),sprite.getSprite(3,0), sprite.getSprite(4,0), sprite.getSprite(5,0), sprite.getSprite(6,0), sprite.getSprite(7,0), sprite.getSprite(8,0)};
        BufferedImage[] walkingDownAnimation = {sprite.getSprite(0, 2), sprite.getSprite(1, 2), sprite.getSprite(2,2),sprite.getSprite(3,2), sprite.getSprite(4,2), sprite.getSprite(5,2), sprite.getSprite(6,2), sprite.getSprite(7,2), sprite.getSprite(8,2)};
        BufferedImage[] standingAnimation = {sprite.getSprite(3, 0)};

        walkingUp = new SpriteAnimation(walkingUpAnimation, 1);
        walkingDown = new SpriteAnimation(walkingDownAnimation, 1);
        walkLeft = new SpriteAnimation(walkingLeftAnimation, 1);
        walkRight = new SpriteAnimation(walkingRightAnimation, 1);
        animation = standing = new SpriteAnimation(standingAnimation, 1);

    }


    public void startAnimation() {
        animation.start();
    }
    public void updateAnimation(){
        animation.update();
    }

    public SpriteAnimation getAnimation() {
        return animation;
    }

    public void setWalkingUp() {
        animation = this.walkingUp;
    }

    public void setWalkingDown() {
        animation = this.walkingDown;
    }

    public void setWalkLeft() {
        animation = this.walkLeft;
    }

    public void setWalkRight() {
        animation = this.walkRight;
    }

    public void setStanding() {
        animation = this.standing;
    }






}
