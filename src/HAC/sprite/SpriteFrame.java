package HAC.sprite;

import java.awt.image.BufferedImage;

/**
 * This class represents the spriteframe.
 * @author ceciliethoresen
 */
public class SpriteFrame {

    private BufferedImage spriteFrame;
    private double duration;

    /**
     * This method insert the Spriteframe to the game.
     * @param spriteFrame is the frame we can upload from sprite.
     * @param duration is a time-based amount of time of the frame in the game.
     * @author ceciliethoresen
     */
    public SpriteFrame(BufferedImage spriteFrame, double duration){
        this.spriteFrame = spriteFrame;
        this.duration = duration;
    }

    /**
     * Here we gets the frame from sprite.
     * @return the current spriteframe to the gameboard.
     * @author ceciliethoresen
     */
    public BufferedImage getSpriteFrame(){
        return spriteFrame;
    }
}
