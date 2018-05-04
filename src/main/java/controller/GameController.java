package main.java.controller;

import main.java.HAC.filehandler.ExportGame;
import main.java.HAC.HAC;
import main.java.HAC.world.GameMap;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

/**
 * GameController is linking javafx to main gamelogic
 * @author Axel Bjørnstad - S315322
 */
public class GameController extends HAC implements Controller {

    @FXML
    private Canvas graphics;

    private MainController mainController; // Parent controller

    /**
     * This method controls gameMap.
     * If there´s nothing in gameMap, then it creates a simple map.
     * @param gameMap is the map in the game.
     */
    GameController(GameMap gameMap) {
        super(gameMap);
    }

    /**
     * Initiate on start of state.
     */
    @Override
    public void initiate() {
        getCamera().setDimension(mainController.getWidth(), mainController.getHeight());
        getCamera().setCanvas(graphics);
        play();
    }

     /**
     * On state close.
     */
    @Override
    public void onClose() {
        // TODO: autosave?
        save();
    }

    /**
     * Gets the EventHandler.
     * @return EventHandler to add support for user input.
     */
    @Override
    public EventHandler<KeyEvent> getEventHandler() {

        // TODO: add acceleration in own controller class
        double speed = 10;
        return (event -> {
            switch (event.getCode()) {
                case W:
                    move(0, speed);
                    break;
                case A:
                    move(speed, 0);
                    break;
                case S:
                    move(0, -speed);
                    break;
                case D:
                    move(-speed, 0);
                    break;

                case ESCAPE:
                    pause();
                    mainController.addSubState(SubState.PAUSE_MENU);
                    break;

            }
        });
    }

    /**
     * Sets mainController(parent).
     * @param mainController controls all the action.
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Save the game.
     */
    public void save() {
        ExportGame save = new ExportGame(getGameMap(),getCamera(), getEnemies(), getPlayer());
    }

    /**
     * Continue the game.
     */
    public void resume() {
        mainController.toMainView();
        play();
    }

    /**
     * Exit the game.
     */
    public void exit() {
        mainController.setState(State.MAIN_MENU);
    }

    /**
     * Load the game.
     */
    public void load() {

    }

}
