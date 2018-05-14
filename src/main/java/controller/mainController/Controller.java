package main.java.controller.mainController;

import javafx.scene.Scene;
import main.java.controller.MainController;
import main.java.model.inputs.Inputs;

/**
 * Interface that represents the controller of the game.
 * @author Axel Bjørnstad - s315322
 */
public abstract class Controller {

    private MainController mainController;
    Inputs inputs;
    private GameState state;

    public Controller(GameState state) {
        this.state = state;
    }

    /**
     * Called when controller is to be set.
     */
    public abstract void initiate();

    /**
     * Called when controller is being replaced.
     */
    public abstract void onClose();

    /**
     * Set event listener to scene from controller.
     */
    public void setEvents(Scene scene) {
        if(inputs == null) return; // No inputs added.
        scene.setOnKeyPressed(inputs.getEventHandler());
        scene.setOnKeyReleased(inputs.getOnRealeasedEventHandler());
        scene.setOnMouseClicked(inputs.getMouseEventHandler());
    }

    void setInputs(Inputs inputs) {
        if (inputs == null) throw new IllegalStateException("Inputs cannot be null.");
        this.inputs = inputs;
    }

    /**
     * This method sets the main controller of the game.
     * @param mainController shows what on the screen of the game.
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    MainController getParent() {
        return mainController;
    }
}
