package application.model.inputs;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import application.controller.mainController.GameController;

public class GameInputs implements Inputs {

    private GameController parent;
    private double speed;

    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;

    public GameInputs (GameController parent, double speed) {
        this.parent = parent;
        this.speed = speed;
    }


    public void loop() {
        if(up&&left) speed /= 2;
        if(up&&right) speed /= 2;
        if(down&&left) speed /= 2;
        if(down&&right) speed /= 2;

        if (up) parent.move(0, speed);
        if (down) parent.move(0, -speed);
        if (left) parent.move(-speed, 0);
        if (right) parent.move(speed, 0);
    }

    @Override
    public EventHandler<KeyEvent> getEventHandler() {
        return (event -> {
            switch (event.getCode()) {
                case W:
                    up = true;
                    break;
                case A:
                    left = true;
                    break;
                case S:
                    down = true;
                    break;
                case D:
                    right = true;
                    break;
                case ESCAPE:
                    parent.pause();
                    break;

            }
        });    }

    @Override
    public EventHandler<KeyEvent> getOnRealeasedEventHandler() {
        return  (event ->{
            switch (event.getCode()){
                case W:
                    up = false;
                    break;
                case A:
                    left = false;
                    break;
                case S:
                    down = false;
                    break;
                case D:
                    right = false;
                    break;
            }

        });
    }

    @Override
    public EventHandler<MouseEvent> getMouseEventHandler() {
        return (event -> parent.shoot(event.getX(), event.getY()));
    }
}
