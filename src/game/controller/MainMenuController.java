package game.controller;

import application.Main;
import game.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Controller {

    private MainController mainController = null;



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
