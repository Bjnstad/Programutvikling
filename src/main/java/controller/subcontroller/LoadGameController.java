package main.java.controller.subcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import main.java.controller.Controller;
import main.java.controller.MainMenuController;
import main.java.model.filehandler.HacParser;
import main.java.model.world.GameMap;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import main.java.controller.Controller;
import main.java.controller.MainMenuController;
import main.java.model.filehandler.HacParser;
import main.java.model.world.GameMap;

import java.io.File;
import java.io.File;

public class LoadGameController implements SubController {

    @FXML
    ListView games;

    static final File dir = new File("assets/savegame");
    private MainMenuController mainMenuController;

    /**
     * Gets all the names to the observable list.
     * @return res?
     */
    private ObservableList<String> getAllNames() {
        if (!dir.isDirectory()) return null;
        ObservableList<String> res = FXCollections.observableArrayList();

        for (final File f : dir.listFiles()) {
            if(getFileExtension(f).equals("       ")) {
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
        GameMap gameMap = hacParser.parseFile(new File("assets/maps/" + games.getSelectionModel().getSelectedItem().toString()));
        mainMenuController.loadMap(gameMap);
    }

    /**
     * Sets sub controller
     * @param controller can´t be null.
     */
    @Override
    public void setSubController(Controller controller) {
        mainMenuController = (MainMenuController)controller;
    }

    /**
     * Here we sets the items and get all names to the map.
     */
    @Override
    public void init() {
        games.setItems(getAllNames());
    }
}
