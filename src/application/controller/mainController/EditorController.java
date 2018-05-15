package application.controller.mainController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import application.model.editor.EditorSpriteInput;
import application.model.editor.ImageList;
import application.model.inputs.EditorInputs;
import hac.controller.World;
import hac.model.Camera;
import hac.model.filehandler.ExportMap;
import hac.model.filehandler.SpriteSheet;
import hac.model.object.GameMap;
import hac.model.object.GameObject;
import hac.model.filehandler.ImportMap;

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

    @FXML
    Slider zoomMap;

    public EditorController() {
        super(GameState.EDITOR);
    }

    @Override
    public void initiate() {
        this.camera = new Camera(getParent().getWidth(), graphics);
        this.imageList = new ImageList(listViewBottom, listView);
        this.world = new World();
        this.exportMap = new ExportMap();
        exportMap.handleMapSize(world, camera);
        zoomMap.setMin(8);
        zoomMap.setMax(35);


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
        exportMap.handleSaveMapName();


    }


    @FXML
    private void onDelete(){

    }
    @FXML
    private void zoomSlider(MouseEvent event){
        camera.setZoom((int)zoomMap.getValue());
        exportMap.getGameMap().render(camera);



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
