package application.controller;

import HAC.HAC;
import HAC.sprite.Sprite;
import HAC.world.GameMap;
import HAC.world.GameObject;
import application.State;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * GameController implements Controller
 */
public class GameController implements Controller {

    @FXML
    Canvas graphics;

    private MainController mainController; // Parent controller
    private HAC game;

    /**
     * On start of state
     */
    @Override
    public void initiate() {
        game = new HAC(createSimpleMap(), graphics, mainController.getWidth(), mainController.getHeight());
        game.setDevMode(true);



        // TODO: #1 Move to own controller class
        graphics.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>(){
                    /**
                     * Description
                     * @param event X, Y
                     */
                    @Override
                    public void handle(MouseEvent event) {
                        game.shoot(event.getX(), event.getY());
                    }
                });


        // ReadOnlyDoubleProperty widthProperty = mainController.getMainView().widthProperty();
        //ReadOnlyDoubleProperty heightProperty = mainController.getMainView().heightProperty();

        //  graphics.widthProperty().bind(widthProperty);
        //  graphics.heightProperty().bind(heightProperty);

        // graphics.widthProperty().addListener(event -> game.resize());
        // graphics.heightProperty().addListener(event -> game.resize());


        //game.setDevMode(true);
        //game.setGrid(true);

    }

    /**
     * Render canvas, called with time interval from @MainController
     */

     /**
     * On state close
     */
    @Override
    public void onClose() {
    }

    /**
     * Return EventHandler to add support for user input
     * @return Eventhandler
     */
    @Override
    public EventHandler<KeyEvent> getEventHandler() {

        // TODO: add acceleration in own controller class
        double speed = 1;
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
                    mainController.setState(State.MAIN_MENU);
                    break;
            }
        });
    }



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

        map.setGameObject(new GameObject(null, 2, 2), 7, 5);
        map.setGameObject(new GameObject(null, 5, 5), 12, 14);
        return map;
    }
}
