package application.controller;

import application.controller.mainController.*;
import application.controller.subController.*;
import application.model.SoundHandler;
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
 * MainController loads fxml views with respectively controller class.
 * @author Axel Bjørnstad - S315322
 */
public class MainController implements Initializable {

    @FXML
    AnchorPane mainView; // Main frame for content
    private Controller controller; // Active controller

    private SoundHandler soundHandler = new SoundHandler();

    /**
     * We set our main view to Main Menu
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SoundHandler.playSound("Lobby.wav");

        setState(GameState.MAIN_MENU);
    }

    /**
     * Change out the controller and view for MainFrame, SubControllers will be removed.
     * @see GameState
     * @param state what state of application should be shown in the main frame.
     */
    public void setState(GameState state) {
        if(controller != null) controller.onClose(); // Call close for the previous controller.

        // Fetch filename for view
        String filename = "";
        switch (state) {
            case MAIN_MENU:
                filename = "MainMenu";
                controller = new MainMenuController();
                break;
            case EDITOR:
                filename = "Editor";
                controller = new EditorController();
                break;
            case GAME:
                filename = "Game";
                controller = new GameController();
                break;
        }


        mainView.getChildren().clear(); // Clear old view
        mainView.getChildren().add(loadPane(filename)); // Change MainFrame to new selected view

        Scene scene = mainView.getScene();
        controller.initiate(); // Call initiate for new controller
        if(scene != null) controller.setEvents(scene); // Set event listeners
    }

    private Pane loadPane(String filename) {
        FXMLLoader loader;
        Pane pane;

        try {
            loader = new FXMLLoader(getClass().getResource("/application/view/" + filename + ".fxml"));
            controller.setMainController(this); // Set ref to main controller
            loader.setController(controller); // Set controller to view
            pane = loader.load();
        } catch (IOException e) {
            throw new IllegalArgumentException(filename + " was not found.");
        }

        return pane;
    }

    /**
     * Removes all subviews and controller, leaving only the current GameState view/controller shown
     */
    public void toMainView() {
        if(mainView.getChildren().size() > 1) {
            mainView.getChildren().remove(1, mainView.getChildren().size());
        }
    }

    /**
     * This method add SubState on top of the already GameState, remove the view by toMainView().
     */
    public void addSubState(SubState subState) {
        SubController subController;

        String filepath = ""; // Path of the current file
        switch (subState) {
            case PAUSE_MENU:
                filepath = "PauseMenu";
                subController = new PauseMenuController();
                break;

            case DIE:
                filepath = "Die";
                subController = new DieController();
                break;

            case CHOOSE_MAP:
                filepath = "ChooseMap";
                setState(GameState.GAME);
                subController = new ChooseMapController();
                break;

            case LOAD_MAP:
                filepath = "LoadGame";
                setState(GameState.GAME);
                subController = new LoadGameController();
                break;

            default:
                return;

        }

        FXMLLoader loader;
        Pane pane;

        try {
            loader = new FXMLLoader(getClass().getResource("/application/view/" + filepath + ".fxml"));
            subController.setSubController(controller); // Set ref to main controller
            loader.setController(subController); // Set controller to view
            pane = loader.load();
        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
            System.err.println("FXML file for " + filepath + " could not be found or is invalid.");
            System.exit(1);
            return;
        }

        mainView.getChildren().add(pane);
        subController.init();
    }

    /**
     * @return the width of the window in pixels.
     */
    public double getWidth() {
        return mainView.getWidth();
    }

    /**
     * @return the height of the window in pixels.
     */
    public double getHeight() {
        return mainView.getHeight();
    }
}
