package hac.model.render;

import hac.model.Camera;
import hac.model.object.GameObject;
import hac.model.object.GameMap;
import hac.controller.World;

import java.util.ArrayList;

public class Actions {
    private ArrayList<Runnable> checkTask = new ArrayList<>();

    private ArrayList<GameObject> remove = new ArrayList<>();
    private ArrayList<GameObject> clean = new ArrayList<>();
    private ArrayList<GameObject> render = new ArrayList<>();


    public void checkGamelogic(ArrayList<GameObject> gameObjects, int start, int end, World world) {
        checkTask.add(new CheckObjects(start, end, this, gameObjects, world));
    }

    public void renderObject(GameObject object) {
        render.add(object);
    }

    public void cleanObject(GameObject object) {
        clean.add(object);
    }

    public void removeObject(GameObject object) {
        remove.add(object);
    }


    public void start(ArrayList<GameObject> gameObjects, GameMap gameMap, Camera camera) {

        // Check objects
        runTask(checkTask);

        // Remove objects
        for(GameObject object : remove) gameObjects.remove(object);
        remove.clear();

        // Clean objects
        for(GameObject object : clean) {
            if(clean == null) continue;
            gameMap.renderArea(camera, (int)object.getPosX() -1, (int)object.getPosY() -1, object.getPosX() + 2, object.getPosY() +2);
        }
        clean.clear();

        // Render objects
        for(GameObject object : render) {
            if(object == null) continue;
            camera.render(object);
            object.renderOptional(camera);
        }
        render.clear();
    }

    private void runTask(ArrayList<Runnable> tasks) {
        for(Runnable task : tasks) task.run();
        tasks.clear();
    }
}
