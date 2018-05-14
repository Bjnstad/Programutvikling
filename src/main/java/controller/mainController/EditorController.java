package main.java.controller.mainController;

import javafx.scene.image.Image;
import main.java.model.Camera;
import main.java.model.filehandler.ExportMap;
import main.java.model.filehandler.HacParser;
import main.java.model.inputs.EditorInputs;
import main.java.model.object.GameObject;
import main.java.model.object.MapObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import main.java.model.editor.*;
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
public class EditorController extends Controller {




    public EditorController() {
        super(GameState.EDITOR);
    }

    @Override
    public void initiate() {
        this.camera = new Camera(getParent().getWidth(), graphics);

        // TODO: Trenger du imageList og exportMap her?
        setInputs(new EditorInputs(15.1, camera, imageList, exportMap));
    }





    // TODO: clean bellow












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
        getParent().setState(GameState.MAIN_MENU);
    }


    /**
     * Initiates imageList (fileHandler)
     *
    @Override
    public void initiate () {

        this.camera = new Camera(mainController.getWidth(), graphics);
        this.exportMap = new ExportMap();
        GameMap gameMap = new GameMap(30, 30, new SpriteSheet("background"));
        this.world = new World();
        world.setGameMap(gameMap);
        gameMap.render(camera);
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

    }*/

    /**
     * This method closes the state of the game.
     * @param event allows us to access the properties of ActionEvent.
     */
    @FXML
    private void close(ActionEvent event) {
        getParent().setState(GameState.MAIN_MENU);
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
        exportMap.createFile(new File("assets/TESTMAP.mhac"), content);
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
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("mhac files", "*.mhac");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            HacParser hacParser = new HacParser();
            this.world = hacParser.parseFile(file);
            for(GameObject gameObject : world.getGameObjects()){
                camera.render(gameObject);
            }


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
            editorSpriteInput.popUp(image, fileName, imageList, imageList.getResult()).show();


        }

    }

    /**
     * Is being used to decorate a method that is being called when a session is closing.
     */
    @Override
    public void onClose () {

    }

}
