package game.controller;

import application.controller.EditorController;
import application.controller.MainController;
import org.junit.jupiter.api.Test;

/**
 * Created by henrytran1 on 19/02/2018.
 */
class EditorControllerTest {
    private MainController mainController;

    @Test
    void setMainController() {
        EditorController editorController = new EditorController();
        editorController.setMainController(mainController);

    }

    @Test
    void initiate() {

    }

    @Test
    void render() {

    }

    @Test
    void onClose() {

    }

    @Test
    void getEventHandler() {

    }

}