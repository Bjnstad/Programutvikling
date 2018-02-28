package HAC;

import HAC.world.GameMap;
import com.sun.glass.ui.Size;
import javafx.scene.canvas.Canvas;

public class Camera {
    public static final int ZOOM = 11; // How many frames to show

    private double offsetX; // Offset X padding to make HAC view squared
    private double offsetY; // Offset Y padding to make HAC view squared
    private double POX; // Player Offset X, offet for player movement x direction
    private double POY; // Player Offset Y, offet for player movement y direction
    private double scale; // Multiplier for correctly display gameboard and sizes
    private Canvas canvas;

    public Camera(Canvas canvas) {
        this.canvas = canvas;
        calcOffset();
    }



    /**
     * Sets offset X and Y with with purpose to display to make a squared window
     * Sets scale to correctly display sizes based on zoom
     */
    private void calcOffset() {
        // Get width and height from canvas
        double width = canvas.getWidth();
        double heigth = canvas.getHeight();


        if(width < heigth) {
            scale = width / ZOOM;
            offsetY = (heigth - width) /2;
        } else {
            scale = width / ZOOM;
            offsetX = (width - heigth) /2;
        }
    }

    public void move(double x, double y){
        POX += x;
        POY += y;
    }

    public void setPOX(double x) {
        this.POX = x;
    }

    public void setPOY(double y) {
        this.POY = y;
    }

    public double getPlayerPosition(int size, boolean getX) {
        if(getX) return (double)(ZOOM) / 2 - POX/scale;
        return (double)(ZOOM) / 2 - POY/scale;
    }


    public double getCenterX(int size) {
        return offsetX + ZOOM * scale / 2 - size * scale / 2;
    }

    public double getCenterY(int size) {
        return offsetY + ZOOM * scale / 2 - size * scale / 2;
    }

    public double scaleX(double x) {
        return x * scale + offsetX + POX;
    }

    public double scaleY(double y) {
        return y * scale + offsetY + POY;
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
