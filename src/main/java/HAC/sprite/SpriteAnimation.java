package main.java.HAC.sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * This class represents the animations we get from sprite.
 * @author
 */
public class SpriteAnimation {
    private int frameCount;
    private double frameDelay;
    private int currentFrame;
    private int totalFrames;
    private boolean stopped;
    private ArrayList<SpriteFrame> spriteFrames = new ArrayList<>();
    private int animationDirection;

    /**
     * Uploads the sprite animation to the game.
     * @param spriteFrames from bufferedImage that describes an image with an accessible buffer of image data.
     * @param frameDelay
     */
    public SpriteAnimation(BufferedImage[] spriteFrames, double frameDelay){
        this.stopped = true;
        this.frameDelay = frameDelay;

        for (int i = 0; i < spriteFrames.length; i++) { //Loops through spriteFrames array, and adds SpriteFrame to ArrayList.
            addSpriteFrame(spriteFrames[i], frameDelay);
        }

        //Set initial frame settings
        this.frameCount = 0;
        this.currentFrame = 0;
        this.totalFrames = this.spriteFrames.size();
        this.animationDirection = 1;
    }

    /**
     * Starts the spriteAnimation.
     */
    public void start(){
        if (!stopped){
            return;
        }
        if (spriteFrames.size() == 0){
            return;
        }
        stopped = false;
    }

    /**
     * Adds the spriteFrame.
     * @param frame from BufferedImage that describes an image with an accessible buffer of image data.
     * @param duration is a time-based amount of time of the frame in the game.
     */
    private void addSpriteFrame(BufferedImage frame, double duration){
        spriteFrames.add(new SpriteFrame(frame, duration));
    }

    /**
     * Gets the sprite from bufferedImage.
     * @return frames from sprite.
     */
    public BufferedImage getSprite(){
        return spriteFrames.get(currentFrame).getSpriteFrame();
    }

    /**
     * Updates the spriteAnimation.
     */
    public void update(){
        if(!stopped){
            frameCount++;
            if(frameCount > frameDelay){
                frameCount = 0;
                currentFrame += animationDirection;
                if(currentFrame > totalFrames -1){
                    currentFrame = 0;
                }else if(currentFrame < 0){
                    currentFrame = totalFrames -1;
                }
            }
        }
    }
}
