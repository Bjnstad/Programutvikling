package main.java.HAC.sprite;

import java.awt.image.BufferedImage;

/**
 * This class represents the frame from sprite.
 * Contains image and the duration.
 * @author
 */
public class SpriteFrame {

    private BufferedImage spriteFrame;
    private double duration;

    /**
     * This method insert the SpriteFrame to the game.
     * @param spriteFrame is the frame we can upload from sprite.
     * @param duration is a time-based amount of time of the frame in the game.
     */
    public SpriteFrame(BufferedImage spriteFrame, double duration){
        this.spriteFrame = spriteFrame;
        this.duration = duration;
    }

    /**
     * Gets the frame from sprite.
     * @return the current spriteFrame to the game.
     */
    public BufferedImage getSpriteFrame(){
        return spriteFrame;
    }
}
