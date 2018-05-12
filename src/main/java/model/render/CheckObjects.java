package main.java.model.render;

import main.java.model.Camera;
import main.java.model.object.GameObject;
import main.java.model.world.World;

import java.util.ArrayList;

public class CheckObjects implements Runnable {

    private final int start;
    private final int end;
    private byte[][] board;
    private ArrayList<GameObject> gameObjects;
    private final Actions actions;
    private final World world;

    public CheckObjects(final int start, final int end, final Actions actions, final ArrayList<GameObject> gameObjects, final World world) {
        this.start = start;
        this.end = end;
        this.actions = actions;
        this.gameObjects = gameObjects;
        this.world = world;
    }

    @Override
    public void run() {
        if(gameObjects == null)return;

        for (int i = start; i < end; i++) {
            GameObject gameObject = gameObjects.get(i);
            if(gameObject == null) continue;

            // Call object logic parts
            gameObject.logic(world);
            for (int j = i +1; j < gameObjects.size(); j++) {
                if(gameObject.willCollide(gameObjects.get(j))) gameObject.onCollide(gameObjects.get(j), actions);
            }

            // Render and clean if inside view of player
            Camera camera = world.getCamera();
            double startX = camera.getTranslateX() / camera.getScale();
            double startY = camera.getTranslateY() / camera.getScale();
            double endX = (camera.getTranslateX() + camera.getDimension())  / camera.getScale();
            double endY = (camera.getTranslateY() + camera.getDimension()) / camera.getScale();


            if(gameObject.getPosY() > startY && gameObject.getPosY() < endY) {
                if(gameObject.getPosX() > startX && gameObject.getPosX() < endX) {
                    actions.cleanObject(gameObject);
                    actions.renderObject(gameObject);
                }
            }
        }
    }
}
