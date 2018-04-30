package main.java.HAC.sprite;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * This class represents the frames of the animation in the game.
 * @author
 */
public class Animation {
    private int frameCount;
    private int frameDelay;
    private int currentFrame;
    private int animationDirection;
    private int totalFrames;

    private boolean stopped;
    private ArrayList<Frame> frames = new ArrayList<Frame>(); // Arraylist of frames.

    /**
     * This method contains the frame of the Animation.
     * @param frames includes that the moves to the animation has to be inside the criteria in the for-loop.
     *               Animation starts in a end(0), and then moves to another end.
     *               But not longer then the length of the frame.
     * @param frameDelay As long as the criteria in the for-loop is true, then it adds a frame.
     */
    public Animation(BufferedImage[] frames, int frameDelay) {
        this.frameDelay = frameDelay;
        this.stopped = true;

        for (int i = 0; i < frames.length; i++) {
            addFrame(frames[i], frameDelay);
        }

        this.frameCount = 0;
        this.frameDelay = frameDelay;
        this.currentFrame = 0;
        this.animationDirection = 1;
        this.totalFrames = this.frames.size();
    }

    /**
     * This method starts the animation in the game.
     */
    public void start() {
        if (!stopped) {
            return;
        }

        if (frames.size() == 0) {
            return;
        }

        stopped = false;
    }

    /**
     * This method stops the animation in the game.
     */
    public void stop() {
        if (frames.size() == 0) {
            return;
        }

        stopped = true;
    }

    /**
     * This method restart the animation in the game.
     */
    public void restart() {
        if (frames.size() == 0) {
            return;
        }
        stopped = false;
        currentFrame = 0;
    }

    /**
     * This method reset the animation in the game.
     */
    public void reset() {
        this.stopped = true;
        this.frameCount = 0;
        this.currentFrame = 0;
    }

    /**
     * Adds a frame to the gameboard.
     * @param frame in the game.
     * @param duration has to be less or equal 0.
     *                 If else, it prints out invalid duration.
     */
    private void addFrame(BufferedImage frame, int duration) {
        if (duration <= 0) {
            System.err.println("Invalid duration: " + duration);
            throw new RuntimeException("Invalid duration: " + duration);
        }

        frames.add(new Frame(frame, duration));
        currentFrame = 0;
    }

    /**
     * Gets buffered image to the game from sprite.
     * @return frames of the game.
     */
    public BufferedImage getSprite() {
        return frames.get(currentFrame).getFrame();
    }

    /**
     * Updates the game.
     */
    public void update() {
        if (!stopped) {
            frameCount++;

            if (frameCount > frameDelay) {
                frameCount = 0;
                currentFrame += animationDirection;

                if (currentFrame > totalFrames - 1) {
                    currentFrame = 0;
                } else if (currentFrame < 0) {
                    currentFrame = totalFrames - 1;
                }
            }
        }
    }
}
