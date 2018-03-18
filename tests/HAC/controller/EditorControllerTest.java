package HAC.controller;

import application.controller.EditorController;
import application.controller.MainController;

import org.testng.annotations.Test;

/**
 * Created by henrytran1 on 19/02/2018.
 */
class EditorControllerTest {
    private MainController mainController;

    /**
     * Sets the main controller
     */
    @Test
    void setMainController() {
        EditorController editorController = new EditorController();
        editorController.setMainController(mainController);
    }

    /**
     * ....
     */
    @Test
    void initiate() {
    }

    /**
     * ....
     */
    @Test
    void render() {
    }

    /**
     * ....
     */
    @Test
    void onClose() {
    }

    /**
     * Gets the event handler
     */
    @Test
    void getEventHandler() {
    }
}