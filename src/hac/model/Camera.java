package hac.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import hac.model.object.GameObject;
import hac.model.object.sprite.ImageHandler;

// TODO: Idea: Render all object to screen

/**
 * Camera is used to calculate differences between game content and the real pixel values for canvas.
 * @author Axel Bjørnstad - s315322
 */
public class Camera {

    private ImageHandler imageHandler = new ImageHandler();
    private int zoom = 12; // How many frames to show
    private Canvas canvas;
    private double translateX; // Movement offset
    private double translateY; // Movement offset
    private double scale; // Multiplier for correctly display gameboard and sizes
    private double dimension;

    public Camera(double dimension, Canvas canvas) {
        this.canvas = canvas;
        this.dimension = dimension;
        setDimension();
    }


    public void render(GameObject object) {
        GraphicsContext gc = getGraphicsContext();
        gc.drawImage(object.getImage(imageHandler), scale(object.getPosX()), scale(object.getPosY()), scale, scale) ;
    }

    /**
     * Sets width and height of the window, then calculates scale to make game content relate to real on screen pixels values.
     */
    public void setDimension() {
        scale = dimension / zoom;
    }

    public double getDimension() {
        return dimension;
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

    public void setTranslateX(double translateX) {
        this.translateX = translateX;
    }

    public void setTranslateY(double translateY) {
        this.translateY = translateY;
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
        return pixel*scale - translateX;
    }

    public double fixedY(double pixel) {
        return pixel*scale - translateY;
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