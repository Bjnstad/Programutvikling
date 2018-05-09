package main.java.controller;

import main.java.controller.subcontroller.*;
import main.java.model.world.GameMap;
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
 * MainController implements Initializable
 * @author Axel Bjørnstad - S315322
 */
public class MainController implements Initializable {

    @FXML
    AnchorPane mainView;

    private Controller controller; // Current controller
    private GameMap gameMap; // The current gamemap to play

    /**
     * Initialize location and resources to the game.
     * @param location in the game.
     * @param resources in the game.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setState(State.MAIN_MENU);
    }

    /**
     * Here we sets the state of application in mainframe.
     * @param state what state of application should be shown in the main frame.
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

                // TODO: Move map loader to subcontroller handler
                controller = new GameController(gameMap);
                break;
        }

        mainView.getChildren().clear(); // Clear old view
        mainView.getChildren().add(loadPane(filepath)); // Change anchorpane to view

        Scene scene = mainView.getScene();
        if(scene != null){
            scene.setOnKeyPressed(controller.getEventHandler());
            scene.setOnMouseClicked(controller.getMouseEventHandler());
        }
        controller.initiate(); // Call initiate for new controller
    }

    private Pane loadPane(String filepath) {
        FXMLLoader loader;
        Pane pane;

        try {
            loader = new FXMLLoader(getClass().getResource("/main/resources/view/" + filepath + ".fxml"));
            controller.setMainController(this); // Set ref to main controller
            loader.setController(controller); // Set controller to view
            pane = loader.load();
        } catch (IOException e) {
            System.exit(-1);
            return null;
        }

        return pane;
    }

    /**
     * Sets the game map.
     * @param gameMap is a simple map for testing.
     */
    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    /**
     * A method that shows MainView.
     */
    public void toMainView() {
        if(mainView.getChildren().size() > 1) {
            mainView.getChildren().remove(1, mainView.getChildren().size());
        }
    }

    /**
     * This method adds the SubState in the game.
     * @param subState is
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
                subController = new ChooseMapController();
                break;

            case LOAD_MAP:
                filepath = "LoadGame";
                subController = new LoadGameController();
                break;

            default:
                return;

        }

        FXMLLoader loader;
        Pane pane;

        try {
            loader = new FXMLLoader(getClass().getResource("/main/resources/view/" + filepath + ".fxml"));
            subController.setSubController(controller); // Set ref to main controller
            loader.setController(subController); // Set controller to view
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("FXML file for " + filepath + " could not be found or is invalid.");
            System.exit(1);
            return;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            System.err.println("FXML file for " + filepath + " could not be found or is invalid.");
            System.exit(1);
            return;
        }

        mainView.getChildren().add(pane);
        subController.init();
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
     * @return the height of the window in pixels.
     */
    public double getHeight() {
        return mainView.getHeight();
    }
}
