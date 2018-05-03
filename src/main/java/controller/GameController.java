package main.java.controller;

import main.java.HAC.filehandler.ExportGame;
import main.java.HAC.HAC;
import main.java.HAC.filehandler.ImportGame;
import main.java.HAC.sprite.Sprite;
import main.java.HAC.world.GameMap;
import main.java.HAC.world.GameObject;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.io.File;

/**
 * GameController implements Controller.
 * @author
 */
public class GameController implements Controller {

    @FXML
    Canvas graphics;

    private MainController mainController; // Parent controller
    private HAC game; // Instance of game

    /**
     * This method controls gameMap.
     * If there´s nothing in gameMap, then it creates a simple map.
     * @param gameMap is the map in the game.
     */
    public GameController(GameMap gameMap) {
        GameMap gm = gameMap;
       // if(gameMap == null) gm = createSimpleMap();
        game = new HAC(gm);
    }

    /**
     * Initiate on start of state.
     */
    @Override
    public void initiate() {
        game.getCamera().setDimension(mainController.getWidth(), mainController.getHeight());
        game.getCamera().setCanvas(graphics);

        game.getPlayer().setPosX(game.getCamera().getPlayerX());
        game.getPlayer().setPosY(game.getCamera().getPlayerY());


        System.out.println(game.getGameMap().getGameObjects().length);


        game.play();
    }

     /**
     * On state close.
     */
    @Override
    public void onClose() {
        save();
    }


    /**
     * Save the game.
     */
    public void save() {
        //Save save = new Save(game.getGameMap(),game.getCamera(),game.getEnemies(), game.getTimeline(), game.getPlayer());
        ExportGame save = new ExportGame(game.getGameMap(),game.getCamera(),game.getEnemies(), game.getPlayer());

    }

    /**
     * Continue the game.
     */
    public void resume() {
        mainController.toMainView();
        game.play();
    }

    /**
     * Exit the game.
     */
    public void exit() {
        mainController.setState(State.MAIN_MENU);
    }

    /**
     * Load the game.
     */
    public void load() {
        ImportGame importGame = new ImportGame();
        File file = new File("assets/maps/newMap.txt");
        importGame.parseFile(file);

    }

    /**
     * Gets the EventHandler.
     * @return Eventhandler to add support for user input.
     */
    @Override
    public EventHandler<KeyEvent> getEventHandler() {

        // TODO: add acceleration in own controller class
        double speed = 10;
        return (event -> {
            switch (event.getCode()) {
                case W:
                    System.out.println(game.move(0, speed));
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
     * Sets mainController(parent).
     * @param mainController controls all the action.
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    /**
     * Creates a simple map for testing, should be replaced by map loader or randomized based on pref width/height.
     * @return a simple map with some objects 100 x 100 in size.
     */
    private GameMap createSimpleMap() {
        GameMap map = new GameMap(20, 20, new Sprite("background", 32));

        map.addGameObject(new GameObject(null, 7,5, 2, 2));
        map.addGameObject(new GameObject(null, 12, 12,5, 5));
        return map;
    }
}
