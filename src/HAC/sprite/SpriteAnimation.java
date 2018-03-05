package HAC.sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by henrytran1 on 04/03/2018.
 */
public class SpriteAnimation {

    private int frameCount;
    private double frameDelay;
    private int currentFrame;
    private int totalFrames;
    private boolean stopped;
    private ArrayList<SpriteFrame> spriteFrames = new ArrayList<>();
    private int animationDirection;

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

    public void start(){
        if (!stopped){
            return;
        }
        if (spriteFrames.size() == 0){
            return;
        }
        stopped = false;
    }

    private void addSpriteFrame(BufferedImage frame, double duration){
        spriteFrames.add(new SpriteFrame(frame, duration));
    }

    public BufferedImage getSprite(){
        return spriteFrames.get(currentFrame).getSpriteFrame();
    }

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
