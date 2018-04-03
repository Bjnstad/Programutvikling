package application.controller;

import application.State;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
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
     * Description
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setState(State.MAIN_MENU);
    }


    /**
     * Change view of the mainframe 
     * @param state what state of application should be shown in the main frame
     */
    public void setState(State state) {
        if(controller != null) controller.onClose(); // Call close for the previous controller.

        String filepath = ""; // Path of the current file
        switch (state) {
            case MAIN_MENU:
                filepath = "MainMenu";
                controller = new MainMenuController();
                break;
            case EDITOR:
                filepath = "Editor";
                controller = new EditorController();
                break;
            case GAME:
                filepath = "Game";
                controller = new GameController();
                break;
        }

        FXMLLoader loader;
        Pane pane;

        try {
            loader = new FXMLLoader(getClass().getResource("/application/layout/" + filepath + ".fxml"));
            controller.setMainController(this); // Set ref to main controller
            loader.setController(controller); // Set controller to view
            pane = loader.load();
        } catch (IOException e) {
            System.err.println("FXML file for " + filepath + " could not be found.");
            System.exit(1);
            return;
        }

        mainView.getChildren().clear(); // Clear old view
        mainView.getChildren().add(pane); // Change anchorpane to view

        Scene scene = mainView.getScene();
        if(scene != null) scene.setOnKeyPressed(controller.getEventHandler());

        controller.initiate(); // Call initiate for new controller
    }

    /**
     * @return the width of the window in pixels
     */
    public double getWidth() {
        return mainView.getWidth();
    }

    /**
     * @return the height of the window in pixels
     */
    public double getHeight() {
        return mainView.getHeight();
    }
}
