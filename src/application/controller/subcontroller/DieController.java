package application.controller.subcontroller;

import application.controller.Controller;
import application.controller.GameController;
import javafx.fxml.FXML;

import java.awt.event.ActionEvent;

/**
 * This class .....
 */
public class DieController implements SubController {

    GameController gameController;

    /**
     * Sets the sub controller.
     * @param controller ??
     * @author ceciliethoresen
     */
    @Override
    public void setSubController(Controller controller) {
        if (controller == null) throw new NullPointerException("Controller cant be null");
        if (!(controller instanceof GameController)) throw new IllegalStateException("Unexpected class, should be GameController");
        this.gameController = (GameController) controller; // Cast to GameController.
    }

    /**
     * init??
     * @author
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
     *
     * @param event allows us to access the properties of the ActionEvent.
     */
    @FXML
    public void exit(ActionEvent event) {
        gameController.exit();
    }
}
