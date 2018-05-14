package main.java.model.inputs;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import main.java.model.Camera;
import main.java.model.editor.ImageList;
import main.java.model.filehandler.ExportMap;

public class EditorInputs implements Inputs {

    private double speed;
    private Camera camera;
    private final ImageList imageList;
    private final ExportMap exportMap = new ExportMap();

    public EditorInputs(double speed, Camera camera, ImageList imageList) {
        this.speed = speed;
        this.camera = camera;
        this.imageList = imageList;
    }

    @Override
    public EventHandler<KeyEvent> getEventHandler() {
        return (event -> {
            switch (event.getCode()) {
                case W:
                    camera.translate(0,speed);
                    break;
                case A:
                    camera.translate(speed, 0);
                    break;
                case S:
                    camera.translate(0, -speed);
                    break;
                case D:
                    camera.translate(-speed, 0);
                    break;
                case ESCAPE:

                    break;

            }
        });    }

    @Override
    public EventHandler<KeyEvent> getOnRealeasedEventHandler() {
        return null;
    }

    @Override
    public EventHandler<MouseEvent> getMouseEventHandler() {
        return (event -> {
            if(imageList.getMapObject() == null) return;
            //graphics.setCursor(new ImageCursor(imageList.getImageItem().getImage()));
            System.out.println("Frames" + imageList.getImageItem().getFrames());
            imageList.getMapObject().setPosX((int)((event.getX()-camera.getTranslateX())/camera.getScale()));
            imageList.getMapObject().setPosY((int)((event.getY()-camera.getTranslateY())/camera.getScale()));
            camera.render(imageList.getMapObject());
            exportMap.addElement(imageList.getMapObject(), imageList.getImageItem());
        });
    }
}
