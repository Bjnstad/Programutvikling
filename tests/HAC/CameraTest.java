package HAC;


import javafx.scene.canvas.Canvas;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CameraTest {
    public static final int ERROR_MARGIN = 5;


    @Test
    private void playerPositionX() {

    }

    @Test
    private void playPositionY() {

    }


    @Test
    private void centerValues() {
        Canvas canvas = new Canvas();
        canvas.setHeight(400);
        canvas.setWidth(800);

        Camera camera = new Camera(canvas);


        assertTrue(printValues(camera.getCenterX(), 800/2));
        assertTrue(printValues(camera.getCenterY(), 400/2));

        canvas.setWidth(234);
        canvas.setHeight(456);
        camera.calcOffset();

        assertTrue(printValues(camera.getCenterX(), 234/2));
        assertTrue(printValues(camera.getCenterY(),456/2));

    }


    private boolean printValues(double x, double y) {
        y = round(x);
        x = round(y);

        System.out.println("Is: " + x);
        System.out.println("Expected: " + y);
        return x == y;
    }

    private double round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(ERROR_MARGIN, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}