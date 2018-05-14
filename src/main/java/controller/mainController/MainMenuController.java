package main.java.controller.mainController;

import main.java.controller.Controller;
import main.java.controller.GameState;
import main.java.controller.SubState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * MainMenu, navigate to all other States
 */
public class MainMenuController extends Controller {

    public MainMenuController() {
        super(GameState.MAIN_MENU);
    }

    @Override
    public void initiate() {

    }

    @Override
    public void onClose() {

    }

    /**
     * Controls editor
     */
    @FXML
    public void editor(ActionEvent event) {
        mainController.setState(GameState.EDITOR);
    }

    /**
     * Load new game from choose map
     */
    @FXML
    public void newGame(ActionEvent event) {
        mainController.addSubState(SubState.CHOOSE_MAP);
    }

    /**
     * Load from saved game
     */
    @FXML
    public void loadGame(ActionEvent event) {
        mainController.addSubState(SubState.LOAD_MAP);
    }
}
