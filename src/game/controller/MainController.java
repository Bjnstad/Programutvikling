package game.controller;

import game.State;
import game.utils.InputHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    AnchorPane mainView;

    private Timeline timeline;
    private Controller controller;
    private InputHandler inputHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setState(State.MAIN_MENU);

        KeyFrame frame = new KeyFrame(Duration.seconds(0.1), event -> gameloop());
        timeline = new Timeline();
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void gameloop() {
        controller.render();
    }

    public void setState(State state) {
        String source;
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
                timeline.play();
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

            Scene scene = mainView.getScene();

            if(scene != null) {
                scene.setOnKeyPressed(controller.getEventHandler());
            }
            controller.initiate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getWidth() {
        return mainView.getWidth();
    }

    public double getHeight() {
        return mainView.getHeight();
    }
}
