package hac.model.object.defaults;

import hac.model.object.GameObject;
import hac.model.object.predefined.Bullet;

public class Arrow extends Bullet {
    /**
     * The coordinates to the bullets from start to end, vertical and horizontal.
     *
     * @param parent
     * @param endX   coordinate of the cell horizontal.
     * @param endY   coordinate of the cell vertical.
     */
    public Arrow(GameObject parent, double endX, double endY, double speed, double strength) {
        super(parent, endX, endY, speed, strength);
    }
}
