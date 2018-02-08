package application.controller;

import game.FileHandler.FileHandler;
import game.assets.Object;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ObjectController implements Initializable {

    @FXML
    AnchorPane main;

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());

        Object object = new Object("My asset", file);

        FileHandler fh = new FileHandler();

        fh.saveAsset(object);
    }


}
