package main.java.application.controller.subController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import main.java.application.controller.mainController.Controller;
import main.java.application.controller.mainController.GameController;
import main.java.hac.model.filehandler.SpriteSheet;
import main.java.hac.model.object.character.MainPlayer;
import main.java.hac.model.filehandler.HacParser;
import main.java.hac.model.object.GameMap;
import main.java.hac.controller.World;

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
        HacParser hacParser = new HacParser();
        World w = hacParser.parseFile(new File("assets/maps/" + maps.getSelectionModel().getSelectedItem().toString()));

        /** TODO: BAD CODE */

        //World w = new World();

        w.addGameObject(new MainPlayer("player_animations_walking", 1, 1, 1, 1));
        w.setGameMap(new GameMap(24, 24, new SpriteSheet("background")));
       // w.setEnemies(new Enemy[0]);



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