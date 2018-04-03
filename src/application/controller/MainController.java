package application.controller;

import application.State;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller implements Initializable
 */
public class MainController implements Initializable {

    @FXML
    AnchorPane mainView;
    private Controller controller; // Current controller

    /**
     * Initialize location and resources to the game
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setState(State.MAIN_MENU);
    }

    /**
     * Sets the state
     * If controller is not null, then controller closes
     * @param state in the game
     */
    public void setState(State state) {
        if(controller != null) controller.onClose();

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
                source = "Game";
                controller = new GameController();
                break;

            default:
                return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/layout/" + source + ".fxml"));
            controller.setMainController(this); // Set ref to main controller
            loader.setController(controller); // Set controller to view

            Pane pane = loader.load();

            mainView.getChildren().clear(); // Clear old view
            mainView.getChildren().add(pane); // Change anchorpane to view

            Scene scene = mainView.getScene();

            if(scene != null) scene.setOnKeyPressed(controller.getEventHandler());

            controller.initiate();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * In this method it gets the width to the gameboard.
     * @return the width of the window in pixels.
     */
    public double getWidth() {
        return mainView.getWidth();
    }

    /**
     * In this method it gets the height to the gameboard.
     * @return the height of the window in pixels
     */
    public double getHeight() {
        return mainView.getHeight();
    }
}
