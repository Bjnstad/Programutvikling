package game.controller;

import game.GameState;
import game.State;

public interface Controller {
    State state = null;
    void initiate();
    void render();
    void onClose();

    void setMainController(MainController mainController);
}
