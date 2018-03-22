package HAC;

import HAC.character.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Camera is used to calculate differences between gameboard and the real canvas.
 */
public class Camera {

    private int zoom = 5; // How many frames to show

    private double offsetX; // Offset X padding to make HAC view squared
    private double offsetY; // Offset Y padding to make HAC view squared
    private double POX; // Player Offset X, offet for player movement x direction
    private double POY; // Player Offset Y, offet for player movement y direction
    private double scale; // Multiplier for correctly display gameboard and sizes
    private Canvas canvas;
    private double width;
    private double height;


    /**
     * Camera used for calculation of offset and scaling
     * @param canvas shown canvas used to calculate offset and scaling
     */
    public Camera(Canvas canvas, double width, double height) {
        // TODO: ADD PLAYER ? SHOULD HAVE AN RELATION OR ADDED AS PARAMETER

        this.width = width;
        this.height = height;
        this.canvas = canvas;
        calcOffset();

        // Set default position to middle of the zoom
        //setPlayerPosition(0, 0);
    }

    /**
     * Sets offset X and Y with with purpose to display to make a squared window
     * Sets scale to correctly display sizes based on zoom
     */
    public void calcOffset() {
        // Get width and height from canvas

        if(width < height) {
            scale = width / zoom;
            //offsetY = (height - width) /2;
        } else {
            scale = height / zoom;
            //offsetX = (width - height) /2;
        }
    }

    /**
     * Render marker of player center position as an cross
     */
    public void renderPlayerMarker(Player player) {
        System.out.println("called");
        double markerSize = scale/20;
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.RED);

        // Horizontal line
        gc.fillRect(getCenterX() - scale/2 - player.getPosX(), getCenterY() - markerSize/2 - player.getPosY(), scale, markerSize);

        // Vertical line
        gc.fillRect(getCenterX() - markerSize/2 - player.getPosX(), getCenterY() - scale/2 - player.getPosY(), markerSize, scale);
    }

    /**
     * Render information to canvas
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
     * Increase or decrease player position
     * @param x horizontal movement
     * @param y vertical movement
     */
    public void move(double x, double y){
        POX += x;
        POY += y;
    }

    /**
     * Set player position to tiles
     */
    public void setPlayerPosition(int x, int y) {
        double cx = (((double)x + .5) - (double) zoom /2) * scale;
        double cy = (((double)y + .5) - (double) zoom /2) * scale;

        setPOX(-cx);
        setPOY(-cy);
    }

    /**
     * Set player position to give double
     * @param x horizontal position
     */
    public void setPOX(double x) {
        this.POX = x;
    }

    /**
     * Set player vertical position to given double
     * @param y vertical position
     */
    public void setPOY(double y) {
        this.POY = y;
    }


    /**
     * Gets the player of x
     * @return
     */
    public double getPlayerX() {
        return (double) zoom / 2 - POX;
    }

    /**
     * Gets the player of y
     * @return
     */
    public double getPlayerY() {
        return (double) zoom / 2 - POY;
    }

    /**
     * Returns the X coordinate for center of the screen in real pixels
     * @return
     */
    public double getCenterX() {
        return offsetX + (double) zoom / 2 * scale;
    }

    /**
     * Returns the Y coordinate for center of the screen in real pixels
     * @return
     */
    public double getCenterY() {
        return offsetY + (double) zoom / 2 * scale;
    }

    /**
     * Scale up the given gameboard relative values to according pixels on screen
     * @param x
     * @return
     */
    public double scaleX(double x) {
        return (x + POX) * scale + offsetX;
    }

    /**
     * Scale up the given gameboard relative values to according pixels on screen
     * @param y
     * @return
     */
    public double scaleY(double y) {
        return (y + POY) * scale + offsetY;
    }

    /**
     *
     * @return
     */
    public double getOffsetX() {
        return offsetX;
    }

    /**
     *
     * @return offsett y
     */
    public double getOffsetY() {
        return offsetY;
    }

    /**
     * Gets the scale
     * @return scale
     */
    public double getScale() {
        return scale;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }
}
