package main.java.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import main.java.model.Camera;
import main.java.model.filehandler.ExportGame;
import main.java.model.world.World;


/**
 * GameController is linking javafx to main game logic
 * @author Axel Bjørnstad - s315322
 */
public class GameController implements Controller {

    private final static double FPS = 60;

    private MainController mainController; // Parent controller
    private World world;
    private boolean isRunning = false;
    private Timeline timeline;

    private Camera camera;

    @FXML
    private Canvas graphics;

    /**
     * Initiate on start of state.
     */
    @Override
    public void initiate() {
        this.camera = new Camera(mainController.getWidth(), graphics);
    }

    /**
     * On state close.
     */
    @Override
    public void onClose() {
        // TODO: autosave?
        save();
    }

    /**
     * Continue the game.
     */
    public void resume() {
        mainController.toMainView();
        play();
    }

    /**
     * Exit the game.
     */
    public void exit() {
        mainController.setState(State.MAIN_MENU);
    }

    /**
     * Load the game.
     * @deprecated
     */
    public void load() {
        //File file = new File("assets/maps/newMap.txt");
        //ImportGame ig =  new ImportGame(file);


    }

    /**
     * Gets the EventHandler.
     * @return EventHandler to add support for user input.
     */
    @Override
    public EventHandler<KeyEvent> getEventHandler() {

        // TODO: add acceleration in own controller class
        double speed = 0.4;
        return (event -> {
            switch (event.getCode()) {
                case W:
                    move(0, speed);
                    break;
                case A:
                    move(-speed, 0);
                    break;
                case S:
                    move(0, -speed);
                    break;
                case D:
                    move(speed, 0);
                    break;
                case ESCAPE:
                    pause();
                    mainController.addSubState(SubState.PAUSE_MENU);
                    break;

            }
        });
    }

    public EventHandler<MouseEvent> getMouseEventHandler(){
        return (event -> {
            if(!isRunning) return;
            double mX = event.getX() - camera.getTranslateX();
            double mY = event.getY() - camera.getTranslateY();
            world.shoot(mX, mY, camera);
        });
    }


    private void gameloop() {
        world.gameLogic();
        world.render(camera);
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
     * Save the game.
     */
    public void save() {
        ExportGame save = new ExportGame(world.getGameMap(),getCamera(), world.getEnemies(), world.getPlayer());
    }


    /**
     * Starting gameloop running accordingly to FPS.
     */
    public void play() {
        if(timeline == null) initTimeline();

        world.loadMap(camera);
        timeline.play(); // Start timeline
        isRunning = true;
    }

    /**
     * Pauses the game.
     */
    public void pause() {
        timeline.pause();
        isRunning = false;
    }

    public void setWorld(World world) {
        world.setGameController(this);
        this.world = world;

        mainController.toMainView();
        play();
    }




    /**
     * Makes the character move vertical or horizontal.
     * @param x is horizontal.
     * @param y is vertical.
     * @return the position to character.
     */
    public boolean move(double x, double y) {
        if(!isRunning) return false; // Dont move if game not running
        return world.move(x, y, camera);
    }


    /**
     * Called when character dies.
     */
    public void die() {
        timeline.stop();
        isRunning = false;
        mainController.addSubState(SubState.DIE);
    }

    /**
     * This method initiate a timeline.
     */
    private void initTimeline() {
        KeyFrame frame = new KeyFrame(Duration.seconds(1/FPS), event -> gameloop());
        timeline = new Timeline();
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }



    public World getWorld() {
        return world;
    }


    /**
     * Shows the state when we run the game.
     * @return the running state of the game.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Gets the camera to gameboard.
     * @return the visual that camera shows us.
     */
    public Camera getCamera() {
        return camera;
    }
}