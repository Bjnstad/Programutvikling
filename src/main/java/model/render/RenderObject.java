package main.java.model.render;

import javafx.scene.canvas.GraphicsContext;
import main.java.model.Camera;
import main.java.model.object.character.Enemy;
import main.java.model.object.GameObject;

public class RenderObject implements Runnable {

    private final Camera camera;
    private final GameObject object;

    public RenderObject(Camera camera, GameObject object) {
        this.camera = camera;
        this.object = object;
    }


    @Override
    public void run() {
        GraphicsContext gc = camera.getGraphicsContext();
        gc.drawImage(object.getSprite(), camera.scale(object.getPosX()), camera.scale(object.getPosY()),camera.getScale() * object.getSizeX(), camera.getScale() * object.getSizeY()) ;
        if(object instanceof Enemy) ((Enemy) object).renderOptional(camera);
    }

}

