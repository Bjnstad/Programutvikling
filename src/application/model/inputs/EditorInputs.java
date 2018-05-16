package application.model.inputs;

import hac.controller.World;
import hac.model.filehandler.ImportMap;
import hac.model.filehandler.SpriteSheet;
import hac.model.object.GameMap;
import hac.model.object.GameObject;
import hac.model.object.MapObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import hac.model.Camera;
import application.model.editor.ImageList;
import hac.model.filehandler.ExportMap;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

/**
 * Handles inout for editor.
 */
public class EditorInputs implements Inputs {

    private double speed;
    private Camera camera;
    private final ImageList imageList;
    private ExportMap exportMap;
    private World world;
    private boolean addObj = true;
    private boolean snapObj = false;


    public EditorInputs(double speed, Camera camera, ImageList imageList) {
        this.speed = speed;
        this.camera = camera;
        this.imageList = imageList;
        this.world = new World();
        this.exportMap = new ExportMap(world);
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

    /**
     * Gets object from Listview, creates new MapObject with new posX and posY based on mouseinput.
     * @return
     */
    @Override
    public EventHandler<MouseEvent> getMouseEventHandler() {
        return (event -> {
            double posX = (event.getX() - camera.getTranslateX()) / camera.getScale() - .5;
            double posY = (event.getY() - camera.getTranslateY()) / camera.getScale() - 1;


            if(addObj == true && snapObj == false) {
                if (imageList.getMapObject() == null) return;

                MapObject mapObject = new MapObject(imageList.getMapObject().getAvatar(), imageList.getMapObject().getPosY(), imageList.getMapObject().getPosX());
                mapObject.setPosX(posX);
                mapObject.setPosY(posY);

                world.addGameObject(mapObject);
                exportMap.addElement(mapObject, imageList.getImageItem());
            }else if(addObj == false && snapObj == false){
                world.removeObject(world.getGameObject(posX, posY));
            }

            if(snapObj == true){
                int nTileX = (int)(posX / camera.getZoom() / camera.getScale());
                int nTileY = (int)(posY / camera.getZoom() / camera.getScale());

            }

            //Render map and objects after adding or removing object
            world.getGameMap().render(camera);
            for(GameObject gameObject : world.getGameObjects()) camera.render(gameObject);


        });
    }


    public void handleMapSize(){
        final Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        BorderPane root = new BorderPane();

        primaryStage.setTitle("Map Size");
        primaryStage.setScene(new Scene(root));

        Button submit = new Button("Submit");

        Label mapSizeX = new Label("Map size X: ");
        TextField inputMapSizeX = new TextField ();
        Label mapSizeY = new Label("Map size Y: ");
        TextField inputMapSizeY = new TextField ();

        // force the field to be numeric only X
        inputMapSizeX.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    inputMapSizeX.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        // force the field to be numeric only Y
        inputMapSizeY.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    inputMapSizeY.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        VBox Vertikalboks = new VBox(mapSizeX, inputMapSizeX, mapSizeY, inputMapSizeY);

        root.setLeft(Vertikalboks);
        root.setBottom(submit);
        primaryStage.show();

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                world.setGameMap(new GameMap(Integer.parseInt(inputMapSizeX.getText()), Integer.parseInt(inputMapSizeY.getText()), new SpriteSheet("default_background")));
                int gameMapX = Integer.parseInt(inputMapSizeX.getText());
                int gameMapY = Integer.parseInt(inputMapSizeY.getText());
                exportMap.addMapSize();
                world.getGameMap().render(camera);
                primaryStage.close();

            }
        });
    }


    public void handleImport(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("mhac files", "*.mhac");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            ImportMap importMap = new ImportMap();
            world = importMap.parseFileTest(file, camera);
            world.getGameMap().render(camera);
            for(GameObject gameObject : world.getGameObjects()){
                camera.render(gameObject);
            }
        }
    }

    public World getWorld() {
        return world;
    }

    public void setAddRemoveState(Boolean state){
        addObj = state;
    }

    public void setSnapState(Boolean state){
        snapObj = state;
    }

    public ExportMap getExportMap() {
        return exportMap;
    }
}
