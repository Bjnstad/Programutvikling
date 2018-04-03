package HAC.sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteAnimation {
    private int frameCount;
    private double frameDelay;
    private int currentFrame;
    private int totalFrames;
    private boolean stopped;
    private ArrayList<SpriteFrame> spriteFrames = new ArrayList<>();
    private int animationDirection;

    /**
     * The spriteanimation to the game
     * @param spriteFrames
     * @param frameDelay
     */
    public SpriteAnimation(BufferedImage[] spriteFrames, double frameDelay){
        this.stopped = true;
        this.frameDelay = frameDelay;

        //Loops through spriteFrames array, and adds SpriteFrame to ArrayList.
        for (int i = 0; i < spriteFrames.length; i++) {
            addSpriteFrame(spriteFrames[i], frameDelay);
        }

        //Set initial frame settings
        this.frameCount = 0;
        this.currentFrame = 0;
        this.totalFrames = this.spriteFrames.size();
        this.animationDirection = 1;
    }

    /**
     * Starts the spriteanimation
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
     * Adds the sprite frame
     * @param frame
     * @param duration
     */
    private void addSpriteFrame(BufferedImage frame, double duration){
        spriteFrames.add(new SpriteFrame(frame, duration));
    }

    /**
     * Gets the sprite bufferedimage
     * @return spriteframes
     */
    public BufferedImage getSprite(){
        return spriteFrames.get(currentFrame).getSpriteFrame();
    }

    /**
     * Update the spriteanimation
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
