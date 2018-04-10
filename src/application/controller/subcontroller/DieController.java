package application.controller.subcontroller;

import application.controller.Controller;
import application.controller.GameController;
import javafx.fxml.FXML;

import java.awt.event.ActionEvent;

public class DieController implements SubController {

    GameController gameController;

    @Override
    public void setSubController(Controller controller) {
        if (controller == null) throw new NullPointerException("Controller cant be null");
        if (!(controller instanceof GameController)) throw new IllegalStateException("Unexpected class, should be GameController");
        this.gameController = (GameController) controller; // Cast to GameController.
    }

    @FXML
    public void retry(ActionEvent event) {
        //gameController.new
    }

    @FXML
    public void exit(ActionEvent event) {
        gameController.exit();
    }
}
