package main.java.controller.subController;

import main.java.controller.Controller;
import main.java.controller.mainController.GameController;
import javafx.fxml.FXML;

import java.awt.event.ActionEvent;

/**
 * This class simulates rolling a die of controller.
 * @author
 */
public class DieController implements SubController {

    GameController gameController;

    /**
     * Sets the subController.
     * @param controller can´ be null.
     */
    @Override
    public void setSubController(Controller controller) {
        if (controller == null) throw new NullPointerException("Controller cant be null");
        if (!(controller instanceof GameController)) throw new IllegalStateException("Unexpected class, should be GameController");
        this.gameController = (GameController) controller; // Cast to GameController.
    }

    /**
     * Initialization done in constructor.
     */
    @Override
    public void init() {
    }

    /**
     * retry??
     * @param event allows us to access the properties of the ActionEvent.
     */
    @FXML
    public void retry(ActionEvent event) {
        //gameController.new
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
