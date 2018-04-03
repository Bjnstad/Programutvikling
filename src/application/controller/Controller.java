package application.controller;

import application.State;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Interface that represents the (logic)? for Game of Life.
 */
public interface Controller {
    State state = null;

    /**
     * Description
     */
    void initiate();

    /**
     * Description
     */
    void onClose();

    //TODO: need rework
    EventHandler<KeyEvent> getEventHandler();

    /**
     * Sets the main controller of the game
     * @param mainController
     * @return main controller
     */
    void setMainController(MainController mainController);
}
