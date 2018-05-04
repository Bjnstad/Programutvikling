package main.java.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Camera is used to calculate differences between game content and the real pixel values for canvas.
 * @author Axel Bjørnstad
 */
public class Camera {

    private int zoom = 20; // How many frames to show
    private Canvas canvas;
    private double translateX; // Movement offset
    private double translateY; // Movement offset
    private double scale; // Multiplier for correctly display gameboard and sizes

    /**
     * Sets width and height of the window, then calculates scale to make game content relate to real on screen pixels values.
     * @param width width of game screen window.
     * @param height hegiht of game screen window.
     */
    public void setDimension(double width, double height) {
        scale = width < height ? width / zoom : height / zoom;
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
        scaleX(x);
        scaleY(y);
        //double cx = (((double)x + .5) - (double) zoom /2) * scale;
        //double cy = (((double)y + .5) - (double) zoom /2) * scale;

        //setPOX(-cx);
        //setPOY(-cy);
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
     * @param x is the position horizontal.
     * @return the position to x.
     */
    public double scaleX(double x) {
        return x * scale;
    }

    /**
     * Scale up the given gameboard relative values to according pixels on screen.
     * @param y is the position vertical.
     * @return the position to y.
     */
    public double scaleY(double y) {

        return y * scale;
    }

    /**
     * Gets the scale either up or out.
     * @return the current scale to the board.
     */
    public double getScale() {
        return scale;
    }
}
