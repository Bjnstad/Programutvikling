package test;


import main.java.HAC.Camera;
import javafx.scene.canvas.Canvas;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CameraTest {
    public static final int MARGIN_OF_ERROR = 5;

    /**
     * Position to the player
     */
    @Test
    public void playerPosition() {
        Canvas canvas = new Canvas();

        canvas.setWidth(234);
        canvas.setHeight(456);
        Camera camera = new Camera(canvas, 200, 200);

        //camera.setPlayerPosition(0, 0);

        Assert.assertTrue(testValues(camera.getPlayerX(), .5));
        Assert.assertTrue(testValues(camera.getPlayerY(), .5));

        camera.setPlayerPosition(1, 1);

        Assert.assertTrue(testValues(camera.getPlayerX(), 1.5));
        Assert.assertTrue(testValues(camera.getPlayerY(), 1.5));

        camera.setPlayerPosition(234, 22);

        Assert.assertTrue(testValues(camera.getPlayerX(), 234.5));
        Assert.assertTrue(testValues(camera.getPlayerY(), 22.5));
    }

    /**
     * The center values to the game
     */
    @Test
    public void centerValues() {
        Camera camera = new Camera(new Canvas(), 200, 200);

        Assert.assertTrue(testValues(camera.getCenterX(), 200/2));
        Assert.assertTrue(testValues(camera.getCenterY(), 200/2));

        camera.calcOffset();
        Assert.assertTrue(testValues(camera.getCenterX(), 234/2));
        Assert.assertTrue(testValues(camera.getCenterY(),456/2));
    }

    /**
     * The test values
     * @param x
     * @param y
     * @return
     */
    private boolean testValues(double x, double y) {
        System.out.println("Before math: " + x);
        System.out.println("Before math: " + y);

        x = round(x);
        y = round(y);

        return x == y;
    }

    /**
     *
     * @param value
     * @return
     */
    private double round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(MARGIN_OF_ERROR, RoundingMode.DOWN);
        return bd.doubleValue();
    }
}