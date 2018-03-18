package HAC.sprite;

import java.awt.image.BufferedImage;

public class SpriteFrame {

    private BufferedImage spriteFrame;
    private double duration;

    /**
     * Spriteframe to the game
     * @param spriteFrame
     * @param duration
     */
    public SpriteFrame(BufferedImage spriteFrame, double duration){
        this.spriteFrame = spriteFrame;
        this.duration = duration;
    }

    /**
     * Gets the spriteframe
     * @return spriteframe
     */
    public BufferedImage getSpriteFrame(){
        return spriteFrame;
    }
}
