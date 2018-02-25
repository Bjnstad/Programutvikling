package application.controller;

import game.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

public class MainMenuController implements Controller {

    private MainController mainController = null;


    @Override
    public void initiate() {

    }

    @Override
    public void render() {

    }

    @Override
    public void onClose() {

    }

    @Override
    public EventHandler<KeyEvent> getEventHandler() {
        return null;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }



    @FXML
    Button options;

    @FXML
    Button hightscore;

    @FXML
    public void editor(ActionEvent event) {
        mainController.setState(State.EDITOR);
    }

    @FXML
    public void play(ActionEvent event) {
        mainController.setState(State.GAME);
    }
}
