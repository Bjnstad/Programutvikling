package main.java.controller.subcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import main.java.HAC.editor.HacParser;
import main.java.HAC.world.GameMap;
import main.java.controller.Controller;
import main.java.controller.MainMenuController;

import java.io.File;

/**
 * This class contain
 * @author
 */
public class ChooseMapController implements SubController {

    @FXML
    ListView maps;

    static final File dir = new File("assets/maps");
    private MainMenuController mainMenuController;

    /**
     * Gets all the names to the observable list.
     * @return res?
     */
    private ObservableList<String> getAllNames() {
        if (!dir.isDirectory()) return null;
        ObservableList<String> res = FXCollections.observableArrayList();

            for (final File f : dir.listFiles()) {
                System.out.println();
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
        GameMap gameMap = hacParser.parseFile(new File("assets/maps/" + maps.getSelectionModel().getSelectedItem().toString()));
        mainMenuController.loadMap(gameMap);
    }

    /**
     * Sets sub controller
     * @param controller can´t be null.
     */
    @Override
    public void setSubController(Controller controller) {
        if (controller == null) throw new NullPointerException("Controller cant be null");
        if (!(controller instanceof MainMenuController))
            throw new IllegalStateException("Unexpected class, should be MainMenuController");
        this.mainMenuController = (MainMenuController) controller; // Cast to MainMenuController.

        System.out.println(maps);
        //maps.setItems(getAllNames());
    }

    /**
     * Here we sets the items and get all names to the map.
     */
    @Override
    public void init() {
        maps.setItems(getAllNames());
    }
}
