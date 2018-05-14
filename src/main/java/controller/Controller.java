package main.java.controller;

import javafx.scene.Scene;
import main.java.model.inputs.Inputs;

/**
 * Interface that represents the controller of the game.
 * @author Axel Bjørnstad - s315322
 */
public abstract class Controller {

    protected MainController mainController;
    protected Inputs inputs;
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
    void setEvents(Scene scene) {
        if(inputs == null) return; // No inputs added.
        scene.setOnKeyPressed(inputs.getEventHandler());
        scene.setOnKeyReleased(inputs.getOnRealeasedEventHandler());
        scene.setOnMouseClicked(inputs.getMouseEventHandler());
    }

    protected void setInputs(Inputs inputs) {
        if (inputs == null) throw new IllegalStateException("Inputs cannot be null.");
        this.inputs = inputs;
    }

    /**
     * This method sets the main controller of the game.
     * @param mainController shows what on the screen of the game.
     */
    void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
