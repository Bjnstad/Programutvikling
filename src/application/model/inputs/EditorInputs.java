package application.model.inputs;

import hac.model.object.MapObject;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import hac.model.Camera;
import application.model.editor.ImageList;
import hac.model.filehandler.ExportMap;

/**
 * Handles inout for editor.
 */
public class EditorInputs implements Inputs {

    private double speed;
    private Camera camera;
    private final ImageList imageList;
    private final ExportMap exportMap;

    public EditorInputs(double speed, Camera camera, ImageList imageList, ExportMap exportMap, Slider zoomMap) {
        this.speed = speed;
        this.camera = camera;
        this.imageList = imageList;
        this.exportMap = exportMap;
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
            imageList.getMapObject().setPosX((int)((event.getX()-camera.getTranslateX())/camera.getScale()));
            imageList.getMapObject().setPosY((int)((event.getY()-camera.getTranslateY())/camera.getScale()));
            MapObject mapObject = new MapObject(imageList.getMapObject().getAvatar(), (int)imageList.getMapObject().getPosY(), (int)imageList.getMapObject().getPosX());

            camera.render(imageList.getMapObject());
            exportMap.addElement(mapObject, imageList.getImageItem());
        });
    }


}
