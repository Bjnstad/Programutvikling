package game.controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable, Controller {

    private MainController mainController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Starting game...");
    }

    public void init(MainController mainController) {
        this.mainController = mainController;
    }


    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
