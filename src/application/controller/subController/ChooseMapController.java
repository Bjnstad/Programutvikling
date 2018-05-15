package application.controller.subController;

import application.model.objects.Zombie1;
import application.model.objects.Zombie2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import application.controller.mainController.Controller;
import application.controller.mainController.GameController;
import hac.controller.World;
import hac.model.object.defaults.MainPlayer;
import hac.model.filehandler.ImportMap;

import java.io.File;

/**
 * This class contain
 * @author
 */
public class ChooseMapController implements SubController {

    @FXML
    ListView maps;

    static final File dir = new File("assets/maps");
    private GameController gameController;

    /**
     * Gets all the names to the observable list.
     * @return res?
     */
    private ObservableList<String> getAllNames() {
        if (!dir.isDirectory()) return null;
        ObservableList<String> res = FXCollections.observableArrayList();

        for (final File f : dir.listFiles()) {
            if(getFileExtension(f).equals("mhac")) {
                res.add(f.getName());
            }
        }

        return res;
    }

    /**
     * Here we gets the file extension.
     * @param file name.
     * @return the name of the file.
     */
    private String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * This method imports the map.
     * @param event allows us to access the properties of ActionEvent.
     */
    @FXML
    public void importMap(ActionEvent event) {
    }

    /**
     * This method parses a file in the game.
     * It is the parser who identifies the error.
     * @param event allows us to access the properties of ActionEvent.
     */
    @FXML
    public void play(ActionEvent event) {


        ImportMap importMap = new ImportMap();
        World w = importMap.parseFile(new File("assets/maps/" + maps.getSelectionModel().getSelectedItem().toString()));
        w.getGameMap().setMapFileName(maps.getSelectionModel().getSelectedItem().toString());


        //World w = new World();

        w.addGameObject(new MainPlayer("default_player", 1, 1));
       // w.setEnemies(new Enemy[0]);

        w.addEnemyType(new Zombie1(0,0));
        w.addEnemyType(new Zombie2(0,0));


        // TODO: CREATE NEW WORLD
        gameController.setWorld(w);
        /** --- --*/
    }

    /**
     * Sets sub controller
     * @param controller can´t be null.
     */
    @Override
    public void setSubController(Controller controller) {
        gameController = (GameController) controller;
    }

    /**
     * Here we sets the items and get all names to the map.
     */
    @Override
    public void init() {
        maps.setItems(getAllNames());
    }
}
