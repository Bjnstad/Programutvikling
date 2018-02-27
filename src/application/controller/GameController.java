package application.controller;

import game.State;
import game.world.GameMap;
import game.world.GameObject;
import game.world.GameWorld;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class GameController implements Controller {

    @FXML
    Canvas graphics;

    private MainController mainController;
    private GameWorld gameWorld;

    // TODO: Remove
    private Image tempImage = new Image("https://i.pinimg.com/originals/6f/6e/c3/6f6ec310eedfbcb45f300d24d0ea0cda.png");

    /**
     * On start of state
     */
    @Override
    public void initiate() {
        GameMap map = new GameMap(100, 100);

        // Adding items to map
        // TODO: Remove
        map.setGameObject(new GameObject(tempImage, 2, 2), 7, 5);
        map.setGameObject(new GameObject(tempImage, 5, 5), 12, 14);


        gameWorld = new GameWorld(map);
    }

    /**
     * Render canvas, called with time interval from @MainController
     */
    @Override
    public void render() {
        // Set width and height of window to canvas
        graphics.setWidth(mainController.getWidth());
        graphics.setHeight(mainController.getHeight());

        // Render GameWorld
        gameWorld.render(graphics);
    }

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
        double speed = 6.5d;
        return (event -> {
            switch (event.getCode()) {
                case W:
                    gameWorld.movePlayer(0, speed);
                    break;
                case A:
                    gameWorld.movePlayer(speed, 0);
                    break;
                case S:
                    gameWorld.movePlayer(0, -speed);
                    break;
                case D:
                    gameWorld.movePlayer(-speed, 0);
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
}
