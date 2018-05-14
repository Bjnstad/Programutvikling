package main.java.controller.subController;


import main.java.controller.mainController.Controller;
import main.java.controller.mainController.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * This class represents the possibility to press pause in the game.
 * @author
 */
public class PauseMenuController implements SubController {

    GameController gameController;

    /**
     * Sets the sub controller.
     * @param controller can´ be null.
     */
    @Override
    public void setSubController(Controller controller) {
        if(controller == null) throw new NullPointerException("Controller cannot be null");
        if(!(controller instanceof GameController)) throw new IllegalStateException("Unexpected class, should be GameController");
        gameController = (GameController)controller; // Cast controller to GameController
    }

    /**
     * Initialization done in constructor.
     */
    @Override
    public void init() {

    }

    /**
     * To continue the game.
     * @param event allows us to access the properties of the ActionEvent.
     */
    @FXML
    public void resume(javafx.event.ActionEvent event) {
        gameController.resume();
    }

    /**
     * Makes it possible to save (a file) the game?
     * @param event allows us to access the properties of the ActionEvent.
     */
    @FXML
    public void save(ActionEvent event) {
        gameController.save();
    }

    /**
     * This method terminates the currently running program.
     * @param event allows us to access the properties of the ActionEvent.
     */
    @FXML
    public void exit(ActionEvent event) {
        gameController.exit();
    }


}
