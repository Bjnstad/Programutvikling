package application.controller.mainController;

import application.controller.subController.SubState;
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
        getParent().setState(GameState.EDITOR);
    }

    /**
     * Load new game from choose map
     */
    @FXML
    public void newGame(ActionEvent event) {
        getParent().addSubState(SubState.CHOOSE_MAP);
    }

    /**
     * Load from saved game
     */
    @FXML
    public void loadGame(ActionEvent event) {
        getParent().addSubState(SubState.LOAD_MAP);
    }
}