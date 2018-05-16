package application.controller.subController;

import hac.model.filehandler.ImportMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import application.controller.mainController.GameController;
import application.controller.mainController.Controller;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class LoadGameController implements SubController {

    @FXML
    ListView games;

    static final File dir = new File("assets/savegame");
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
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("mhac files", "*.mhac");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(new Stage());


    }

    /**
     * This method parses a file in the game.
     * It is the parser who identifies the error.
     * @param event allows us to access the properties of ActionEvent.
     */
    @FXML
    public void play(ActionEvent event) {
        File file = new File("assets/savegame/" + games.getSelectionModel().getSelectedItem().toString());
        ImportMap im = new ImportMap();

        //gameController.getCamera().translate(ig.getTranslateX(), ig.getTranslateY());
        //gameController.setWorld(ig.getWorld());


        /*for(Enemy enemy : ig.getEnemies()) world.addGameObject(enemy);
        world.addGameObject(ig.getPlayer());
        world.setGameMap(ig.getMap());
        //world.getGameMap().addGameObject();*/


        gameController.setWorld(im.parseFileTest(file, gameController.getCamera()));

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
        games.setItems(getAllNames());
    }
}
