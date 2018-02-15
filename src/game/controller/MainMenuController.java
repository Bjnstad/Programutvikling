package game.controller;

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

public class MainMenuController implements Initializable {

    @FXML
    Button options;

    @FXML
    Button hightscore;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void editor(ActionEvent event) {

    }

    @FXML
    public void play(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("layout/Game.fxml"));

        try {
            Parent root = loader.load();
            //gifController = loader.getController();

            Stage gifStage = new Stage();
            gifStage.setScene(new Scene(root));

            //gifController.initialize(gifStage, canvasController.getGol());
            gifStage.setTitle("Create GIF");

            gifStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
