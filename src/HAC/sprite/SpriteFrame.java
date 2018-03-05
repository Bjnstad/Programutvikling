package HAC.sprite;

import java.awt.image.BufferedImage;

/**
 * Created by henrytran1 on 04/03/2018.
 */
public class SpriteFrame {

    private BufferedImage spriteFrame;
    private double duration;

    public SpriteFrame(BufferedImage spriteFrame, double duration){
        this.spriteFrame = spriteFrame;
        this.duration = duration;
    }

    public BufferedImage getSpriteFrame(){
        return spriteFrame;
    }
}
