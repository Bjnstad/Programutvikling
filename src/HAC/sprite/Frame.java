package HAC.sprite;

import java.awt.image.BufferedImage;

public class Frame {

    private BufferedImage frame;
    private int duration;

    /**
     * Frame of the game
     * @param frame
     * @param duration
     */
    public Frame(BufferedImage frame, int duration) {
        this.frame = frame;
        this.duration = duration;
    }

    /**
     * Gets the frame to the game
     * @return frame
     */
    public BufferedImage getFrame() {
        return frame;
    }

    /**
     * Sets the frame to the game
     * @param frame
     */
    public void setFrame(BufferedImage frame) {
        this.frame = frame;
    }

    /**
     * Gets the duration
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

}