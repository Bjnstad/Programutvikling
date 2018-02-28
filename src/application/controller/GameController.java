package application.controller;

import HAC.HAC;
import HAC.world.GameMap;
import HAC.world.GameObject;
import application.State;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

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
        graphics.setWidth(mainController.getWidth());
        graphics.setHeight(mainController.getHeight());
        game = new HAC(createSimpleMap(), graphics);





        /*

        // TODO: #1 Move to own controller class
        graphics.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>(){


                    @Override
                    public void handle(MouseEvent event) {
                        double calculatedX = (event.getX() - gameWorld.getOffset().getOffsetX())/gameWorld.getOffset().getSize();
                        double calculatedY = (event.getY() - gameWorld.getOffset().getOffsetY())/gameWorld.getOffset().getSize();
                        gameWorld.shoot(event.getX(), event.getY());

                    }
                });
*/
        //Sets walking and shooting sprite
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
        double speed = 2;
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
        GameMap map = new GameMap(100, 100);

        map.setGameObject(new GameObject(null, 2, 2), 7, 5);
        map.setGameObject(new GameObject(null, 5, 5), 12, 14);
        return map;
    }
}
