package main.java.controller;

import javafx.scene.image.Image;
import main.java.model.Camera;
import main.java.model.filehandler.ExportMap;
import main.java.model.filehandler.HacParser;
import main.java.model.filehandler.SpriteSheet;
import main.java.model.world.GameMap;
import main.java.model.object.MapObject;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import main.java.model.editor.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.model.world.World;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Implements the EditorController to Controller.
 * @author
 */
public class EditorController implements Controller {

    private MainController mainController;
    private ImageList imageList;
    private World world;
    //private HACEditor map;
    private Camera camera;
    private boolean isRunning = false;
    private MapObject mapObject;
    private ExportMap exportMap;
    private ArrayList<String> fileNames = new ArrayList<>();



    @FXML
    Canvas graphics;

    @FXML
    ListView listView;

    @FXML
    ListView listViewBottom;

    /**
     * This method creates a new file.
     * @param event allows us to access the properties of ActionEvent
     */
    @FXML
    public void newFile(ActionEvent event){
        mainController.setState(State.MAIN_MENU);
    }

    /**
     * This method sets the mainController.
     * @param mainController shows what on the screen of the game.
     */
    @Override
    public void setMainController (MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Initiates imageList (fileHandler)
     */
    @Override
    public void initiate () {
        this.camera = new Camera(mainController.getWidth(), graphics);
        this.exportMap = new ExportMap();
        //GameMap gameMap = new GameMap(30, 30, new SpriteSheet("background", 32));
        this.world = new World();
        //world.setGameMap(gameMap);
        //gameMap.render(camera);
        imageList = new ImageList(listViewBottom, listView);

        //listView.setItems(imageList.openEditorSave(imageList.getResult()));
        listViewBottom.setItems(imageList.openSpriteEditorSave(imageList.getSpriteBottom()));
        listViewBottom.setCellFactory(param -> new ListCell<ImageItem>() {
            @Override
            protected void updateItem(ImageItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setGraphic(item.getImageView());
                }
            }
        });


        //listView.setItems(imageList.getAllNames());

        //listView.setCellFactory(param -> imageList.getAllAssets());
        imageList.handleSpriteListView();
        imageList.handleAssetsListView(graphics);

    }

    /**
     * This method closes the state of the game.
     * @param event allows us to access the properties of ActionEvent.
     */
    @FXML
    private void close(ActionEvent event) {
        mainController.setState(State.MAIN_MENU);
    }

    /**
     * This method makes it possible to save a file.
     * @param event allows us to access the properties of ActionEvent.
     */
    @FXML
    private void save(ActionEvent event){

        StringBuilder sb = new StringBuilder();
        for(String content : exportMap.getElements()){
            sb.append(content);
        }
        String content = sb.toString();
        exportMap.createFile(new File("assets/TESTMAP.mHac"), content);
        //map.saveFile();
        //exportHac.createFile();

    }

    /**
     * This method makes it possible to import a file.
     * @param event allows us to access the properties of ActionEvent.
     */
    @FXML
    private void Import(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("mHac files", "*.mhac");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            HacParser hacParser = new HacParser();
            hacParser.parseFile(file);
        }
    }

    @FXML
    private void ImportSprite(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            EditorSpriteInput editorSpriteInput = new EditorSpriteInput();

            Image image = new Image(file.toURI().toString());
            Path path = file.toPath();
            String[] fileNameA = String.valueOf(path.getFileName()).split("\\.");
            String fileName = fileNameA[0];
            System.out.println("Height: " + image.getHeight() + "Width: " + image.getWidth());
            System.out.println("Bits: " + image.getWidth()/editorSpriteInput.getColumns());
            editorSpriteInput.popUp(image, fileName, imageList, imageList.getResult()).show();


        }

    }

    /**
     * Is being used to decorate a method that is being called when a session is closing.
     */
    @Override
    public void onClose () {

    }

    /**
     * This method gets the event handler.
     * @return the current event.
     */
    @Override
    public EventHandler<KeyEvent> getEventHandler() {

        // TODO: add acceleration in own controller class
        double speed = 15.4;
        return (event -> {
            /*if(event.getCode() == KeyCode.W)move(0, speed);
            if(event.getCode() == KeyCode.A)move(-speed, 0);
            if(event.getCode() == KeyCode.S)move(0, -speed);
            if(event.getCode() == KeyCode.D)move(speed, 0);*/

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
            });
    }

    @Override
    public EventHandler<KeyEvent> getOnRealeasedEventHandler() {
        return null;
    }

    public EventHandler<MouseEvent> getMouseEventHandler(){
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
