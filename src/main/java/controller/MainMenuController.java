package main.java.controller;

import main.java.model.world.GameMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

/**
 * MainMenu Controller implements Controller.
 */
public class MainMenuController implements Controller {

    private MainController mainController = null;

    /**
     * Called when controller is to be set.
     */
    @Override
    public void initiate() {

    }

    /**
     * Called when controller is being replaced.
     */
    @Override
    public void onClose() {

    }

    /**
     * Gets EventHandler.
     * @return null
     */
    @Override
    public EventHandler<KeyEvent> getEventHandler() {
        return null;
    }


    /**
     * Sets main controller of the game.
     * @param mainController shows what on the screen of the game.
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    @FXML
    Button options;

    @FXML
    Button hightscore;

    /**
     * Controls editor
     * @param event allows us to access the properties of ActionEvent
     */
    @FXML
    public void editor(ActionEvent event) {
        mainController.setState(State.EDITOR);
    }

    /**
     * Controls the game to play.
     * @param event allows us to access the properties of ActionEvent.
     */
    @FXML
    public void play(ActionEvent event) {
        mainController.addSubState(SubState.CHOOSE_MAP);
    }


    /**
     * Loads the map.
     * @param gameMap shows us map of game.
     */
    public void loadMap(GameMap gameMap) {
        mainController.setGameMap(gameMap);
        mainController.setState(State.GAME);
    }


}
