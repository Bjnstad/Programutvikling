package game.controller;

import game.State;
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
        setState(State.MAIN_MENU);
    }








    public void setState(State state) {
        String source;
        Controller controller;
        switch (state) {
            case MAIN_MENU:
                source = "MainMenu";
                controller = new MainMenuController();
                break;
            case EDITOR:
                source = "Editor";
                controller = new EditorController();
                break;
            case GAME:
                source = "Game";
                controller = new GameController();
                break;


            default:
                return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/game/layout/" + source + ".fxml"));
            controller.setMainController(this); // Set ref to main controller
            loader.setController(controller); // Set controller to view
            Pane pane = loader.load();
            mainView.getChildren().clear(); // Clear old view
            mainView.getChildren().add(pane); // Change anchorpane to view
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
