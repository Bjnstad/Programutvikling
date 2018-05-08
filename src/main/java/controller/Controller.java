package main.java.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Interface that represents the controller of the game.
 * @author
 */
public interface Controller {
    State state = null; //There is differents states in the game.

    /**
     * Called when controller is to be set.
     */
    void initiate();

    /**
     * Called when controller is being replaced.
     */
    void onClose();

    //TODO: need rework
    EventHandler<KeyEvent> getEventHandler();
    EventHandler<MouseEvent> getMouseEventHandler();

    /**
     * This method sets the main controller of the game.
     * @param mainController shows what on the screen of the game.
     */
    void setMainController(MainController mainController);
}
