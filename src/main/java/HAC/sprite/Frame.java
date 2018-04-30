package main.java.HAC.sprite;

import java.awt.image.BufferedImage;

/**
 * This class represents the frame of the game.
 * Contains the visual frame and the duration in a time-based amount of time to the frame.
 * @author
 */
public class Frame {

    private BufferedImage frame;
    private int duration;

    /**
     * This method represents the frame to the the game.
     * @param frame sets the edges of the game from BufferedImage.
     * @param duration is a time-based amount of time of the frame in the game.
     */
    public Frame(BufferedImage frame, int duration) {
        this.frame = frame;
        this.duration = duration;
    }

    /**
     * Gets the frame from bufferedImage.
     * @return frame from BufferedImage that describes an image with an accessible buffer of image data.
     */
    public BufferedImage getFrame() {
        return frame;
    }

    /**
     * Sets the frame from BufferedImage.
     * @param frame sets the edges of the game from BufferedImage.
     */
    public void setFrame(BufferedImage frame) {
        this.frame = frame;
    }

    /**
     * Gets the duration to the game.
     * @return duration that is a time-based amount of time of the frame in the game.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration in the game.
     * @param duration that is a time-based amount of time of the frame in the game.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
}