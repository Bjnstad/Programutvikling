package application.controller;

import application.State;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Interface that represents the controller of the game.
 * @author ceciliethoresen
 */
public interface Controller {
    State state = null; //There is differents states in the game.

    void initiate();

    void onClose();

    //TODO: need rework
    EventHandler<KeyEvent> getEventHandler();

    /**
     * This method sets the main controller of the game.
     * @param mainController shows what on the screen of the game.
     */
    void setMainController(MainController mainController);
}
