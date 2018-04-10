package application.controller.subcontroller;


import application.controller.Controller;
import application.controller.GameController;
import application.controller.subcontroller.SubController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PauseMenuController implements SubController {

    GameController gameController;

    @Override
    public void setSubController(Controller controller) {
        if(controller == null) throw new NullPointerException("Controller cannot be null");
        if(!(controller instanceof GameController)) throw new IllegalStateException("Unexpected class, should be GameController");
        gameController = (GameController)controller; // Cast controller to GameController
    }


    @FXML
    public void resume(javafx.event.ActionEvent event) {
        gameController.resume();
    }

    @FXML
    public void save(ActionEvent event) {
        gameController.save();
    }

    @FXML
    public void load(ActionEvent event) {
        gameController.load();
    }

    @FXML
    public void exit(ActionEvent event) {
        gameController.exit();
    }


}
