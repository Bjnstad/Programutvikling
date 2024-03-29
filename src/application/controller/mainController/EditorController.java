package application.controller.mainController;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import application.model.editor.ImageList;
import application.model.inputs.EditorInputs;
import hac.model.Camera;
import hac.model.object.GameObject;



/**
 * Implements the EditorController to Controller.
 * @author
 */
public class EditorController extends Controller {

    private ImageList imageList;
    private Camera camera;
    private EditorInputs editorInputs;


    @FXML
    private Canvas graphics;

    @FXML
    private ListView listView;

    @FXML
    private ListView listViewBottom;

    @FXML
    private Slider zoomMap;


    public EditorController() {
        super(GameState.EDITOR);
    }

    @Override
    public void initiate() {
        this.camera = new Camera(getParent().getWidth(), graphics);
        this.imageList = new ImageList(listViewBottom, listView);
        //this.world = new World();
        editorInputs = new EditorInputs(15.1, camera, imageList);
        editorInputs.handleMapSize();
        zoomMap.setMin(8);
        zoomMap.setMax(35);
        setInputs(editorInputs);

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
        editorInputs.getExportMap().handleSaveMapName(true);
    }

    /**
     * This method handles zoom for editor.
     * @param event
     */
    @FXML
    private void zoomSlider(MouseEvent event){
        camera.setZoom((int)zoomMap.getValue());
        editorInputs.getWorld().getGameMap().render(camera);
        for(GameObject gameObject : editorInputs.getWorld().getGameObjects()){
            camera.render(gameObject);
        }
    }

    /**
     * This method makes it possible to import a file.
     * @param event allows us to access the properties of ActionEvent.
     */
    @FXML
    private void Import(ActionEvent event){
        editorInputs.handleImport();
    }

    @FXML
    private void ImportSprite(ActionEvent event){
        editorInputs.handleImportSprite();
    }

    @FXML
    private void onAdd(ActionEvent event){
        editorInputs.setAddRemoveState(true);

    }

    @FXML
    private void onRemove(ActionEvent event){
        editorInputs.setAddRemoveState(false);

    }

    @FXML
    private void onChangeMap(ActionEvent event){
        editorInputs.handleMapSize();
    }

    @FXML
    private void onInstructions(ActionEvent event){
        editorInputs.handleInstructions();
    }



    /**
     * Is being used to decorate a method that is being called when a session is closing.
     */
    @Override
    public void onClose () {

    }

}
