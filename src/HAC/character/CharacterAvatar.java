package HAC.character;

import HAC.sprite.Sprite;
import HAC.sprite.SpriteAnimation;

import java.awt.image.BufferedImage;

/**
 * This class describes the character in the game.
 */
public class CharacterAvatar {
    private SpriteAnimation walkingUp;
    private SpriteAnimation walkingDown;
    private SpriteAnimation walkLeft;
    private SpriteAnimation walkRight;
    private SpriteAnimation standing;
    private Sprite sprite;
    private SpriteAnimation animation;

    /**
     * This method gives us the size to Avatar.
     * @param fileName of the file we can save.
     * @param size The size to the Avatar.
     * @author Cecilie Thoresen
     */
    public CharacterAvatar(String fileName, int size){
        sprite = new Sprite(fileName, size);

        BufferedImage[] walkingLeftAnimation = {sprite.getSprite(0, 1), sprite.getSprite(1, 1), sprite.getSprite(2,1),sprite.getSprite(3,1), sprite.getSprite(4,1), sprite.getSprite(5,1), sprite.getSprite(6,1), sprite.getSprite(7,1), sprite.getSprite(8,1)};
        BufferedImage[] walkingRightAnimation = {sprite.getSprite(0, 3), sprite.getSprite(1, 3), sprite.getSprite(2,3),sprite.getSprite(3,3), sprite.getSprite(4,3), sprite.getSprite(5,3), sprite.getSprite(6,3), sprite.getSprite(7,3), sprite.getSprite(8,3)};
        BufferedImage[] walkingUpAnimation = {sprite.getSprite(0, 0), sprite.getSprite(1, 0), sprite.getSprite(2,0),sprite.getSprite(3,0), sprite.getSprite(4,0), sprite.getSprite(5,0), sprite.getSprite(6,0), sprite.getSprite(7,0), sprite.getSprite(8,0)};
        BufferedImage[] walkingDownAnimation = {sprite.getSprite(0, 2), sprite.getSprite(1, 2), sprite.getSprite(2,2),sprite.getSprite(3,2), sprite.getSprite(4,2), sprite.getSprite(5,2), sprite.getSprite(6,2), sprite.getSprite(7,2), sprite.getSprite(8,2)};
        BufferedImage[] standingAnimation = {sprite.getSprite(3, 0)};

        walkingUp = new SpriteAnimation(walkingUpAnimation, 2);
        walkingDown = new SpriteAnimation(walkingDownAnimation, 2);
        walkLeft = new SpriteAnimation(walkingLeftAnimation, 2);
        walkRight = new SpriteAnimation(walkingRightAnimation, 2);
        animation = standing = new SpriteAnimation(standingAnimation, 2);
    }

    /**
     * In this method the Avatar start.
     * @author Cecilie Thoresen
     */
    public void startAnimation() {
        animation.start();
    }

    /**
     * Here the avatar updates in the game,
     */
    public void updateAnimation(){
        animation.update();
    }

    /**
     * In this method we use Sprite to get the animation in the board game.
     * @return Then it returns the animation to the game.
     * @author Henry Tran
     */
    public SpriteAnimation getAnimation() {
        return animation;
    }

    /**
     * Sets the walking up to the avatar.
     * @author Axel Bjørnstad
     */
    public void setWalkingUp() {
        animation = this.walkingUp;
    }

    /**
     * Sets the walking down to the avatar.
     * @author Henry Tran
     */
    public void setWalkingDown() {
        animation = this.walkingDown;
    }

    /**
     * Sets the walking left to the avatar.
     * @author Cecilie Thoresen
     */
    public void setWalkLeft() {
        animation = this.walkLeft;
    }

    /**
     * Sets the walking right to the avatar.
     */
    public void setWalkRight() {
        animation = this.walkRight;
    }

    /**
     * Sets the standing to the avatar.
     */
    public void setStanding() {
        animation = this.standing;
    }

}
