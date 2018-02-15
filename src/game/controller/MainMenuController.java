package game.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
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

    public void play(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("layout/Main.fxml"));

        try {
            Parent root = loader.load();
            gifController = loader.getController();

            Stage gifStage = new Stage();
            gifStage.setScene(new Scene(root));

            //gifController.initialize(gifStage, canvasController.getGol());
            gifStage.setTitle("Create GIF");

            gifStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the gif (s305061) FXML document, IO expectedException");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load the gif (s305061) FXML document");
        }

    }
}
