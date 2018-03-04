package HAC;

import HAC.character.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.swing.plaf.synth.ColorType;

public class Camera {
    public final static int ZOOM = 11; // How many frames to show

    private double offsetX; // Offset X padding to make HAC view squared
    private double offsetY; // Offset Y padding to make HAC view squared
    private double POX; // Player Offset X, offet for player movement x direction
    private double POY; // Player Offset Y, offet for player movement y direction
    private double scale; // Multiplier for correctly display gameboard and sizes
    private Canvas canvas;

    /**
     * Camera used for calculation of offset and scaling
     * @param canvas shown canvas used to calculate offset and scaling
     */
    public Camera(Canvas canvas) {
        this.canvas = canvas;
        calcOffset();


        // TODO: ADD PLAYER ? SHOULD HAVE AN RELATION OR ADDED AS PARAMETER
    }

    /**
     * Sets offset X and Y with with purpose to display to make a squared window
     * Sets scale to correctly display sizes based on zoom
     */
    public void calcOffset() {
        // Get width and height from canvas
        double width = canvas.getWidth();
        double height = canvas.getHeight();


        if(width < height) {
            scale = width / ZOOM;
            offsetY = (height - width) /2;
        } else {
            scale = height / ZOOM;
            offsetX = (width - height) /2;
        }
    }

    /**
     * Render marker of player center position as an cross
     */
    public void renderPlayerMarker(Player player) {
        double markerSize = scale/10;
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.RED);

        // Horizontal line
        gc.fillRect(getCenterX() - scale/2, getCenterY() - markerSize/2, scale, markerSize);

        // Vertical line
        gc.fillRect(getCenterX() - markerSize/2, getCenterY() - scale/2, markerSize, scale);
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
        gc.fillText("X: "+getPlayerPosition(player.getSizeX(), true),  canvas.getWidth() - width + 10, 35);
        gc.fillText("Y: "+getPlayerPosition(player.getSizeY(), false),  canvas.getWidth() - width + 10, 50);
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
     *
     * @param size
     * @param getX
     * @return
     */
    public double getPlayerPosition(int size, boolean getX) {
        if(getX) return (double)(ZOOM) / 2 - POX/scale + offsetX;
        return (double)(ZOOM) / 2 - POY/scale + offsetY;
    }


    public double getPlayerX(int size) {
        return offsetX + (double)ZOOM / 2;
    }


    public double getCenterX() {
        return offsetX + (double)ZOOM / 2 * scale;
    }

    public double getCenterY() {
        return offsetY + (double)ZOOM / 2 * scale;
    }

    public double scaleX(double x) {
        return x * scale + POX;
    }

    public double scaleY(double y) {
        return y * scale + POY;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public double getScale() {
        return scale;
    }
}
