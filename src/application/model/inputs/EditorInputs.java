package application.model.inputs;

import application.model.editor.EditorSpriteInput;
import hac.controller.World;
import hac.model.filehandler.ImportMap;
import hac.model.filehandler.SpriteSheet;
import hac.model.GameMap;
import hac.model.object.GameObject;
import hac.model.object.predefined.MapObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
import java.nio.file.Path;

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


            if(addObj == true) {
                if (imageList.getMapObject() == null) return;

                MapObject mapObject = new MapObject(imageList.getMapObject().getAvatar(), imageList.getMapObject().getPosY(), imageList.getMapObject().getPosX());
                mapObject.setPosX(posX);
                mapObject.setPosY(posY);

                world.addGameObject(mapObject);
                exportMap.addElement(mapObject, imageList.getImageItem());
            }else if(addObj == false){
                world.removeObject(world.getGameObject(posX, posY));
            }


            //Render map and objects after adding or removing object
            try {
                world.getGameMap().render(camera);
                for(GameObject gameObject : world.getGameObjects()) camera.render(gameObject);

            }catch (Exception e){
                handleMapSize();
            }


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
        inputMapSizeX.setPromptText("20");

        Label mapSizeY = new Label("Map size Y: ");
        TextField inputMapSizeY = new TextField ();
        inputMapSizeY.setPromptText("20");

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
            world = importMap.parseFile(file, camera);
            world.getGameMap().render(camera);
            for(GameObject gameObject : world.getGameObjects()){
                camera.render(gameObject);
            }
        }
    }

    public void handleImportSprite(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("PNG files", "*.png");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            EditorSpriteInput editorSpriteInput = new EditorSpriteInput();

            Image image = new Image(file.toURI().toString());
            Path path = file.toPath();
            String[] fileNameA = String.valueOf(path.getFileName()).split("\\.");
            String fileName = fileNameA[0];
            editorSpriteInput.popUp(image, fileName, imageList, imageList.getResult()).show();

        }
    }

    public void handleInstructions(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Editor instructions");
        alert.setHeaderText("Instructions for editor");
        alert.setContentText("It is only static objects that can be added to the map, such as walls etc.\n\nChoose a spritesheet below, then select the desired asset, listed on the right side. Afterwards you can click anywhere on the map to place the object.\n\nYou can also import custom spritesheets, under file->import spritesheets. Make sure each sub image is of size 16x16, 32x32, 64x64 or 128x128.\n\nIt is also possible to import already made gamemaps, under file->import GameMap");

        alert.showAndWait();
    }

    public World getWorld() {
        return world;
    }

    public void setAddRemoveState(Boolean state){
        addObj = state;
    }


    public ExportMap getExportMap() {
        return exportMap;
    }
}
