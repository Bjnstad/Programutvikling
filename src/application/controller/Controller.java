package application.controller;

import game.State;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public interface Controller {
    State state = null;
    void initiate();
    void render();
    void onClose();
    EventHandler<KeyEvent> getEventHandler();

    void setMainController(MainController mainController);
}
