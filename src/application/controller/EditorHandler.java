package application.controller;

import HAC.HACEditor;
import application.State;
import javafx.scene.input.KeyEvent;

/**
 * Created by henrytran1 on 22/03/2018.
 */
public class EditorHandler{

    private MainController mainController;
    private HACEditor map;
    public EditorHandler(MainController mainController, HACEditor hacEditor){
        this.mainController = mainController;
        this.map = hacEditor;

    }


    public void getEventHandler(KeyEvent event) {
        double speed = 6;

            switch (event.getCode()) {
                case ESCAPE:
                    mainController.setState(State.MAIN_MENU);
                    break;
                case W:
                    map.move(0, speed);
                    map.render();
                    break;
                case A:
                    map.move(speed, 0);
                    map.render();
                    break;
                case S:
                    map.move(0, -speed);
                    map.render();
                    break;
                case D:
                    map.move(-speed, 0);
                    map.render();
                    break;
            }
        }

}
