package game.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    AnchorPane mainView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("/game/layout/MainMenu.fxml"));
            mainView.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
