package application.controller.mainController;

import hac.model.object.defaults.Bolt;
import hac.model.filehandler.ExportMap;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;
import application.controller.subController.SubState;
import hac.model.Camera;
import application.model.inputs.GameInputs;
import hac.model.object.Bullet;
import hac.model.object.defaults.MainPlayer;
import hac.model.object.character.Player;
import hac.controller.World;

/**
 * GameController is linking javafx to main game logic
 * @author Axel Bjørnstad - s315322
 */
public class GameController extends Controller {

    private final static double FPS = 60;

    private World world;
    private boolean isRunning = false;
    private Timeline timeline;

    private Camera camera;


    @FXML
    private Canvas canvas;


    public GameController() {
        super(GameState.GAME);
    }

    /**
     * Initiate on start of state.
     */
    @Override
    public void initiate() {
        double dimension = (getParent().getWidth() > getParent().getHeight()) ? getParent().getHeight() : getParent().getWidth();
        this.camera = new Camera(dimension, canvas);
        setInputs(new GameInputs(this, 0.1));
    }

    /**
     * On state close.
     */
    @Override
    public void onClose() {
        if (isRunning) save();
    }

    /**
     * Starting gameloop running accordingly to FPS.
     */
    public void play() {
        if(timeline == null) initTimeline();

        world.renderMap(camera);
        timeline.play(); // Start timeline
        isRunning = true;
    }

    /**
     * Pauses the game.
     */
    public void pause() {
        if(!isRunning) return;
        timeline.pause();
        isRunning = false;
        getParent().addSubState(SubState.PAUSE_MENU);
    }

    /**
     * Continue the game.
     */
    public void resume() {
        getParent().toMainView();
        play();
    }

    /**
     * Exit the game.
     */
    public void exit() {
        getParent().setState(GameState.MAIN_MENU);
    }

    /**
     * Save the game.
     */
    public void save() {
        //ExportGame save = new ExportGame(world);
        //ExportGame save = new ExportGame(world.getGameMap(),getCamera(), world.getEnemies(), world.getPlayer());
        ExportMap exportMap = new ExportMap(world);
        exportMap.saveGame();

        //getParent().setState(GameState.MAIN_MENU);
    }

    public void shoot(double x, double y) {
        if(!isRunning) return;
        Player player = world.getMainPlayer();
        double mX = x - camera.getTranslateX();
        double mY = y - camera.getTranslateY();
        double pX = player.getPosX() + .5;
        double pY = player.getPosY() + .5;

        world.addGameObject(new Bolt(player, mX/camera.getScale(), mY/camera.getScale()));
    }

    private void gameloop() {
        ((GameInputs)inputs).loop();
        world.gameloop();
    }

    /**
     * Makes the character move vertical or horizontal.
     * @param x is horizontal.
     * @param y is vertical.
     * @return the position to character.
     */
    public boolean move(double x, double y) {
        if(!isRunning) return false; // Dont move if game not running
        MainPlayer mainPlayer = world.getMainPlayer();
        return mainPlayer.addPos(x, y, world);
    }


    /**
     * Called when character dies.
     */
    public void die() {
        timeline.stop();
        isRunning = false;
        getParent().addSubState(SubState.DIE);
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



    public void setWorld(World world) {
        world.setGameController(this);
        this.world = world;
        getParent().toMainView();
        play();
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