package application.controller.subcontroller;

import HAC.editor.HacParser;
import HAC.sprite.Sprite;
import HAC.world.GameMap;
import application.controller.Controller;
import application.controller.GameController;
import application.controller.MainMenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.File;

/**
 * This class contain
 * @author ceciliethoresen
 */
public class ChooseMapController implements SubController {

    @FXML
    ListView maps;

    static final File dir = new File("assets/maps");
    private MainMenuController mainMenuController;

    /**
     *
     * Gets all the names to the observable list.
     * @return the
     * @author ceciliethoresen
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
     * @param file
     * @return filename?
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
     * @param event allows us to access the properties of the ActionEvent.
     * @author ceciliethoresen
     */
    @FXML
    public void importMap(ActionEvent event) {
    }

    /**
     *
     * @param event allows us to access the properties of the ActionEvent.
     * @author ceciliethoresen
     */
    @FXML
    public void play(ActionEvent event) {
        HacParser hacParser = new HacParser();
        GameMap gameMap = hacParser.parseFile(new File("assets/maps/" + maps.getSelectionModel().getSelectedItem().toString()));
        mainMenuController.loadMap(gameMap);
    }

    /**
     * Sets sub controller. //Hva gjør controller
     * @param controller .......
     * @author ceciliethoresen
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
     * Here we get all names to the map.
     * @author ceciliethoresen
     */
    @Override
    public void init() {
        maps.setItems(getAllNames());
    }
}
