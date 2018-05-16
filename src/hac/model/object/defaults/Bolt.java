package hac.model.object.defaults;

import hac.model.object.Bullet;
import hac.model.object.GameObject;

public class Bolt extends Bullet {
    /**
     * The coordinates to the bullets from start to end, vertical and horizontal.
     *
     * @param parent
     * @param endX   coordinate of the cell horizontal.
     * @param endY   coordinate of the cell vertical.
     */
    public Bolt(GameObject parent, double endX, double endY) {
        super(parent, endX, endY, 0);
        setScale(3.5);
    }
}
