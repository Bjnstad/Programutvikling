package main.java.controller.mainController;

import javafx.scene.image.Image;
import main.java.model.Camera;
import main.java.model.filehandler.ExportMap;
import main.java.model.filehandler.ImportMap;
import main.java.model.filehandler.SpriteSheet;
import main.java.model.inputs.EditorInputs;
import main.java.model.object.GameObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import main.java.model.editor.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.model.world.GameMap;
import main.java.model.world.World;
import java.io.File;
import java.nio.file.Path;


/**
 * Implements the EditorController to Controller.
 * @author
 */
public class EditorController extends Controller {

    private ImageList imageList;
    private World world;
    private Camera camera;
    private ExportMap exportMap;

    @FXML
    Canvas graphics;

    @FXML
    ListView listView;

    @FXML
    ListView listViewBottom;


    public EditorController() {
        super(GameState.EDITOR);
    }

    @Override
    public void initiate() {
        this.camera = new Camera(getParent().getWidth(), graphics);
        this.imageList = new ImageList(listViewBottom, listView);
        this.world = new World();
        this.exportMap = new ExportMap();
        GameMap gameMap = new GameMap(30, 30, new SpriteSheet("background"));
        world.setGameMap(gameMap);
        gameMap.render(camera);

        setInputs(new EditorInputs(15.1, camera, imageList, exportMap));
    }


    /**
     * This method creates a new file.
     * @param event allows us to access the properties of ActionEvent
     */
    @FXML
    public void newFile(ActionEvent event){
        getParent().setState(GameState.MAIN_MENU);
    }


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
            ImportMap importMap = new ImportMap();
            this.world = importMap.parseFile(file);
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
