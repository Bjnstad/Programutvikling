package main.java.model;

import javafx.scene.input.KeyEvent;
import main.java.controller.HACEditor;
import main.java.controller.MainController;
import main.java.controller.State;

/**
 * Created by henrytran1 on 22/03/2018.
 * This class handels the editor.
 */
public class EditorHandler{

    private MainController mainController;
    private HACEditor map;
    public EditorHandler(MainController mainController, HACEditor hacEditor){
        this.mainController = mainController;
        this.map = hacEditor;
    }

    /**
     * Gets the event handler when a key is pressed
     * @param event allows us to access the properties of KeyEvent.
     * @author henrytran1
     */
    public void getEventHandler(KeyEvent event) {
        double speed = 6;

            switch (event.getCode()) {
                case ESCAPE:
                    mainController.setState(State.MAIN_MENU);
                    break;
                case W:
                    map.move(0, -speed);
                    map.render();
                    break;
                case A:
                    map.move(-speed, 0);
                    map.render();
                    break;
                case S:
                    map.move(0, speed);
                    map.render();
                    break;
                case D:
                    map.move(speed, 0);
                    map.render();
                    break;
            }
        }
}
