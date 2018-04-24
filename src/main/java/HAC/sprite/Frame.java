package main.java.HAC.sprite;

import java.awt.image.BufferedImage;

/**
 * This class represents the frame of the game.
 * @author ceciliethoresen
 */
public class Frame {

    private BufferedImage frame;
    private int duration;

    /**
     * This method represents the frame to the the game.
     * @param frame sets the edges of the game from BufferedImage.
     * @param duration is a time-based amount of time of the frame in the game.
     * @author ceciliethoresen
     */
    public Frame(BufferedImage frame, int duration) {
        this.frame = frame;
        this.duration = duration;
    }

    /**
     * This method gets the frame from bufferedImage.
     * @return frame from BufferedImage that describes an image with an accessible buffer of image data.
     * @author ceciliethoresen
     */
    public BufferedImage getFrame() {
        return frame;
    }

    /**
     * Here we sets the frame from BufferedImage.
     * @param frame sets the edges of the game from BufferedImage.
     * @author ceciliethoresen
     */
    public void setFrame(BufferedImage frame) {
        this.frame = frame;
    }

    /**
     * This method gets the duration to the game.
     * @return duration that is a time-based amount of time of the frame in the game.
     * @author cecilie thoresen
     */
    public int getDuration() {
        return duration;
    }

    /**
     * This method sets the duration in the game.
     * @param duration that is a time-based amount of time of the frame in the game.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
}