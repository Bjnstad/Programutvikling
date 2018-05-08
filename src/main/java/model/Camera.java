package main.java.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

// TODO: Idea: Render all object to screen

/**
 * Camera is used to calculate differences between game content and the real pixel values for canvas.
 * @author Axel Bjørnstad - s315322
 */
public class Camera {

    private int zoom = 12; // How many frames to show
    private Canvas canvas;
    private double translateX; // Movement offset
    private double translateY; // Movement offset
    private double scale; // Multiplier for correctly display gameboard and sizes

    public Camera(double dimension, Canvas canvas) {
        this.canvas = canvas;
        setDimension(dimension);
    }

    /**
     * Sets width and height of the window, then calculates scale to make game content relate to real on screen pixels values.
     * @param width width of game screen window.
     */
    public void setDimension(double width) {
        scale = width / zoom;
    }

    /**
     * Sets canvas to draw on.
     * @param canvas javafx canvas uses GraphicsContext to draw on.
     */
    public void setCanvas(Canvas canvas) {
        if(canvas == null) throw new NullPointerException("Canvas cannot be null.");
        this.canvas = canvas;
    }

    /**
     * Gets the Canvas vertical.
     * @return the current view of Canvas(height).
     */
    public double getTranslateX() {
        return translateX;
    }

    /**
     * Gets the Canvas horizontal.
     * @return the current view of Canvas(width).
     */
    public double getTranslateY() {
        return translateY;
    }

    /**
     * This method is used to draw into Canvas.
     * @return the current canvas.
     */
    public GraphicsContext getGraphicsContext() {
        return canvas.getGraphicsContext2D();
    }

    /**
     * Moves the canvas up or down, or to the side with x and y.
     * @param x moving canvas left or right.
     * @param y moving canvas up or down.
     */
    public void translate(double x, double y) {
        translateX += x;
        translateY += y;
        canvas.setTranslateX(translateX);
        canvas.setTranslateY(translateY);
    }

    /**
     * Sets player position to tiles.
     * @param x is the position horizontal.
     * @param y is the position vertical.
     */
    public void setPlayerPosition(int x, int y) {
        scale(x);
        scale(x);
        //double cx = (((double)x + .5) - (double) zoom /2) * scale;
        //double cy = (((double)y + .5) - (double) zoom /2) * scale;

        //setPOX(-cx);
        //setPOY(-cy);
    }

    public double fixedX(double pixel) {
        return pixel - translateX * 10000;
    }

    public double fixedY(double pixel) {
        return pixel - translateY * 10000;
    }

    /**
     * Gets the player of x(horizontal).
     * @return the position to player.
     */
    public double getPlayerX() {
        return (double) zoom / 2 - translateX/scale;
    }

    /**
     * Gets the player of y(vertical).
     * @return the position to player.
     */
    public double getPlayerY() {
        return (double) zoom / 2 - translateY/scale;
    }

    /**
     * Gets the center of the screen(x-coordinate).
     * @return Returns the X coordinate for center of the screen in real pixels.
     */
    public double getCenterX() {
        return (double) zoom / 2 * scale;
    }

    /**
     * Gets the center of the screen(y-coordinate).
     * @return Returns the Y coordinate for center of the screen in real pixels.
     */
    public double getCenterY() {
        return (double) zoom / 2 * scale;
    }

    /**
     * Scale up the given gameboard relative values to according pixels on screen.
     * @param value is the position horizontal.
     * @return the position to x.
     */
    public double scale(double value) {
        return value * scale;
    }

    /**
     * Gets the scale either up or out.
     * @return the current scale to the board.
     */
    public double getScale() {
        return scale;
    }

    public int getZoom() {
        return this.zoom;
    }
}