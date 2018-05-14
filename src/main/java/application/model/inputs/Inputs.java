package main.java.application.model.inputs;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public interface Inputs {
    EventHandler<KeyEvent> getEventHandler();
    EventHandler<KeyEvent> getOnRealeasedEventHandler();
    EventHandler<MouseEvent> getMouseEventHandler();
}
