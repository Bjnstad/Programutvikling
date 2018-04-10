package application.controller;

import HAC.HAC;
import HAC.sprite.Sprite;
import HAC.world.GameMap;
import HAC.world.GameObject;
import application.State;
import application.SubState;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * GameController implements Controller.
 */
public class GameController implements Controller {

    @FXML
    Canvas graphics;

    private MainController mainController; // Parent controller
    private HAC game; // Instance of game

    public GameController(GameMap gameMap) {
        GameMap gm = gameMap;
        if(gameMap == null) gm = createSimpleMap();
        game = new HAC(gm, graphics, mainController.getWidth(), mainController.getHeight());
    }

    /**
     * On start of state.
     */
    @Override
    public void initiate() {
    }

     /**
     * On state close
     */
    @Override
    public void onClose() {
        save();
    }


    public void save() {

    }

    public void resume() {
        mainController.toMainView();
        game.play();
    }

    public void exit() {
        mainController.setState(State.MAIN_MENU);
    }

    public void load() {

    }


    /**
     * Return EventHandler to add support for user input
     * @return Eventhandler
     */
    @Override
    public EventHandler<KeyEvent> getEventHandler() {

        // TODO: add acceleration in own controller class
        double speed = 10;
        return (event -> {
            switch (event.getCode()) {
                case W:
                    game.move(0, speed);
                    break;
                case A:
                    game.move(speed, 0);
                    break;
                case S:
                    game.move(0, -speed);
                    break;
                case D:
                    game.move(-speed, 0);
                    break;

                case ESCAPE:
                    game.pause();
                    mainController.addSubState(SubState.PAUSE_MENU);
                    break;

            }
        });
    }

    /**
     * Sets parent
     * @param mainController
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    /**
     * Creates a simple map for testing, should be replaced by map loader or randomized based on pref width/height
     * @return a simple map with some objects 100 x 100 in size
     */
    private GameMap createSimpleMap() {
        GameMap map = new GameMap(20, 20, new Sprite("background", 32));

        map.addGameObject(new GameObject(null, 7,5, 2, 2));
        map.addGameObject(new GameObject(null, 12, 12,5, 5));
        return map;
    }
}
