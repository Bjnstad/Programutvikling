package HAC;


import javafx.scene.canvas.Canvas;
import static org.junit.Assert.*;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CameraTest {
    public static final int MARGIN_OF_ERROR = 5;

    @Test
    public void playerPosition() {
        Canvas canvas = new Canvas();

        canvas.setWidth(234);
        canvas.setHeight(456);
        Camera camera = new Camera(canvas);


        camera.setPlayerPosition(0, 0);

        assertTrue(testValues(camera.getPlayerX(1), .5));
        assertTrue(testValues(camera.getPlayerY(1), .5));

        camera.setPlayerPosition(1, 1);

        assertTrue(testValues(camera.getPlayerX(1), 1.5));
        assertTrue(testValues(camera.getPlayerY(1), 1.5));
        assertTrue(testValues(camera.getPlayerX(1), 1.5));
        assertTrue(testValues(camera.getPlayerY(1), 1.5));
        assertTrue(testValues(camera.getPlayerX(1), 1.5));
        assertTrue(testValues(camera.getPlayerY(1), 1.5));
        assertTrue(testValues(camera.getPlayerX(1), 1.5));
        assertTrue(testValues(camera.getPlayerY(1), 1.5));
        assertTrue(testValues(camera.getPlayerX(1), 1.5));
        assertTrue(testValues(camera.getPlayerY(1), 1.5));
        assertTrue(testValues(camera.getPlayerX(1), 1.5));
        assertTrue(testValues(camera.getPlayerY(1), 1.5));
        assertTrue(testValues(camera.getPlayerX(1), 1.5));
        assertTrue(testValues(camera.getPlayerY(1), 1.5));


        camera.setPlayerPosition(234, 22);

        assertTrue(testValues(camera.getPlayerX(1), 234.5));
        assertTrue(testValues(camera.getPlayerY(1), 22.5));
    }


    @Test
    public void centerValues() {
        Canvas canvas = new Canvas();
        canvas.setHeight(400);
        canvas.setWidth(800);

        Camera camera = new Camera(canvas);


        assertTrue(testValues(camera.getCenterX(), 800/2));
        assertTrue(testValues(camera.getCenterY(), 400/2));

        canvas.setWidth(234);
        canvas.setHeight(456);
        camera.calcOffset();

        assertTrue(testValues(camera.getCenterX(), 234/2));
        assertTrue(testValues(camera.getCenterY(),456/2));

    }


    private boolean testValues(double x, double y) {
        x = round(x);
        y = round(y);

        System.out.println("Is: " + x);
        System.out.println("Expected: " + y);
        return x == y;
    }

    private double round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(MARGIN_OF_ERROR, RoundingMode.DOWN);
        return bd.doubleValue();
    }
}