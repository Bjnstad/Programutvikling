package application.controller;

import application.State;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public interface Controller {
    State state = null;
    void initiate();
    void onClose();
    EventHandler<KeyEvent> getEventHandler();

    void setMainController(MainController mainController);
}
