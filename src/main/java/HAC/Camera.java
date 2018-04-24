package main.java.HAC;

import main.java.HAC.character.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Camera is used to calculate differences between game content and the real pixel values for canvas.
 * @author
 */
public class Camera {
    private int zoom = 20; // How many frames to show

    private Canvas canvas;
    private double width; // Width of window
    private double height; // Height of window
    private double translateX; // Movement offset
    private double translateY; // Movement offset

    /**
     * Camera used for calculation of offset and scaling.
     * @param canvas shown canvas used to calculate offset and scaling.
     * @author
     */
    public Camera(Canvas canvas, double width, double height) {
        this.width = width;
        this.height = height;
        this.canvas = canvas;
        calcOffset();
    }

    /**
     * Sets offset X and Y with with purpose to display to make a squared window
     * Sets scale to correctly display sizes based on zoom
     * @author
     */
    public void calcOffset() {
        width < height ? 
        if(width < height) {
            scale = width / zoom;
        } else {
            scale = height / zoom;
        }
    }

    /**
     * This method gets the Canvas vertical.
     * @return the current view of Canvas(height).
     * @author ceciliethoresen
     */
    public double getTranslateX() {
        return translateX;
    }

    /**
     * Here we get the Canvas horizontal.
     * @return the current view of Canvas(width).
     * @author ceciliethoresen
     */
    public double getTranslateY() {
        return translateY;
    }

    /**
     * This method is used to draw into Canvas.
     * @return the current canvas.
     * @author ceciliethoresen
     */
    public GraphicsContext getGraphicsContext() {
        return canvas.getGraphicsContext2D();
    }

    /**
     * This method is moving the canvas up or to the side with x and y.
     * @param x moving canvas left or right.
     * @param y moving canvas up or down.
     * @author ceciliethoresen
     */
    public void translate(double x, double y) {
        translateX += x;
        translateY += y;
        canvas.setTranslateX(translateX);
        canvas.setTranslateY(translateY);
    }



    public void setWidth(double width) {
        this.width = width;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }


    private double offsetX; // Offset X padding to make main.java.HAC view squared
    private double offsetY; // Offset Y padding to make main.java.HAC view squared
    private double scale; // Multiplier for correctly display gameboard and sizes


    /**
     * Render marker of player center position as an cross.
     * @param player in the game.
     * @author
     */
    public void renderPlayerMarker(Player player) {
        double markerSize = scale/20;
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.RED);

        // Horizontal line
        gc.fillRect(getCenterX() - scale/2 - player.getPosX(), getCenterY() - markerSize/2 - player.getPosY(), scale, markerSize);

        // Vertical line
        gc.fillRect(getCenterX() - markerSize/2 - player.getPosX(), getCenterY() - scale/2 - player.getPosY(), markerSize, scale);
    }

    /**
     * Here we render information to canvas.
     * @param player in the game.
     * @author ceciliethoresen
     */
    public void renderPlayerInfo(Player player) {
        int width = 120;
        int height = 70;

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(canvas.getWidth() - width, 0, width, height);

        gc.setFill(Color.WHITE);
        gc.fillText("Player position", canvas.getWidth() - width + 10, 20);
        gc.fillText("X: " + getPlayerX(),  canvas.getWidth() - width + 10, 35);
        gc.fillText("Y: " + getPlayerY(),  canvas.getWidth() - width + 10, 50);
    }

    /**
     * Here we set player position to tiles.
     * @param x is the position horizontal.
     * @param y is the position vertical.
     * @author ceciliethoresen
     */
    public void setPlayerPosition(int x, int y) {
        double cx = (((double)x + .5) - (double) zoom /2) * scale;
        double cy = (((double)y + .5) - (double) zoom /2) * scale;

        //setPOX(-cx);
        //setPOY(-cy);
    }

    /**
     * This method gets the player of x(horizontal).
     * @return the position to player.
     * @author ceciliethoresen
     */
    public double getPlayerX() {
        return (double) zoom / 2 - translateX/scale;
    }

    /**
     * This method gets the player of y(vertical).
     * @return the position to player.
     * @author
     */
    public double getPlayerY() {
        return (double) zoom / 2 - translateY/scale;
    }

    /**
     * Here we gets the center of the screen(x-coordinate).
     * @return Returns the X coordinate for center of the screen in real pixels.
     * @author
     */
    public double getCenterX() {
        return offsetX + (double) zoom / 2 * scale;
    }

    /**
     * Here we gets the center of the screen(y-coordinate).
     * @return Returns the Y coordinate for center of the screen in real pixels.
     * @author
     */
    public double getCenterY() {
        return offsetY + (double) zoom / 2 * scale;
    }

    /**
     * Scale up the given gameboard relative values to according pixels on screen.
     * @param x is the position horizontal.
     * @return the position to x.
     * @author ceciliethoresen
     */
    public double scaleX(double x) {
        return x * scale;
    }

    /**
     * Scale up the given gameboard relative values to according pixels on screen.
     * @param y is the position vertical.
     * @return the position to y.
     * @author ceciliethoresen
     */
    public double scaleY(double y) {

        return y * scale;
    }


    /**
     * This method gets the timezone offset for x.
     * @return the offset to x.
     * @author ceciliethoresen
     */
    public double getOffsetX() {
        return offsetX;
    }

    /**
     * Here we get the timezone offset for y.
     * @return the offset to y.
     * @author ceciliethoresen
     */
    public double getOffsetY() {
        return offsetY;
    }

    /**
     * This method gets the scale either up or out.
     * @return the current scale to the board.
     * @author ceciliethoresen
     */
    public double getScale() {
        return scale;
    }

    /**
     * Here we gets the zoom to canvas.
     * @return the zoomed view of canvas.
     * @author ceciliethoresen
     */
    public int getZoom() {
        return zoom;
    }

    /**
     * And here we sets the zoom to canvas.
     * @param zoom
     * @author ceciliethoresen
     */
    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    /**
     * We get the width of canvas.
     * @return width of canvas.
     * @author ceciliethoresen
     */
    public double getWidth() {
        return width;
    }

    /**
     * And here we get the height to canvas.
     * @return height of canvas.
     * @author ceciliethoresen
     */
    public double getHeight() {
        return height;
    }

}
