package hac.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import hac.model.object.GameObject;
import hac.model.object.sprite.ImageHandler;
import javafx.scene.paint.Color;

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
    private double dimension; // Width or height of board

    /**
     * At this moment we do not support anything other than square dimension of the camera size.
     * @param dimension either height or widht of the main frame size
     * @param canvas Canvas to draw on
     */
    public Camera(double dimension, Canvas canvas) {
        this.canvas = canvas;
        this.dimension = dimension;
        canvas.setHeight(dimension);
        canvas.setWidth(dimension);
        setDimension();
    }

    /**
     * Draw method for object rendering to the screen.
     * @param object Object to draw on screen
     */
    public void render(GameObject object) {
        GraphicsContext gc = getGraphicsContext();
        gc.drawImage(object.getImage(imageHandler), scale(object.getPosX()), scale(object.getPosY()), scale, scale) ;
    }

    /**
     * @return GraphicsContext of Canvas
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
     * Scale up the given gameboard relative values to according pixels on screen.
     * @param value is the position horizontal.
     * @return the position to x.
     */
    public double scale(double value) {
        return value * scale;
    }

    /**
     * Clear the screen by making view black.
     * @param barHeight
     */
    public void clearView(double barHeight) {
        GraphicsContext gc = getGraphicsContext();
        gc.setFill(Color.BLACK);
        gc.fillRect(-getTranslateX(), -getTranslateY(), -getTranslateX() + dimension +scale, -getTranslateY() + dimension + barHeight*scale);
    }

    /**
     * Zoom is how many background tiles shown on screen simultaneously, 2 in zoom is 2 tiles on x axis and 2 on y axis, higher number for zoom out.
     * @param zoom how many tiles shown to screen
     */
    public void setZoom(int zoom) {
        this.zoom = zoom;
        setDimension();
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
     * @return current translate x value set to Canvas
     */
    public double getTranslateX() {
        return translateX;
    }

    /**
     * @return current translate y value set to Canvas
     */
    public double getTranslateY() {
        return translateY;
    }

    /**
     * @return Board values to pixels values, the length of 1 size in board
     */
    public double getScale() {
        return scale;
    }

    public int getZoom() {
        return this.zoom;
    }
}